//#define DEBUG
#include <linux/module.h> // for MODULE_*
#include <linux/fs.h> // for fops
#include <linux/device.h> // for struct device

#include <linux/slab.h> // for the kmalloc API
#include <linux/mman.h> // for remap_pfn_range
#include <linux/pagemap.h> // for vma structures

//#define DO_DEBUG
#include "kernel_helper.h" // our own helper

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Showing how to implement mmap");

/*
 * Mmap is way by which your module may map memory into the address space of the process
 * that is using it. This is an example of how to do it.
 */
static unsigned long addr;
static void* vaddr;
static unsigned int size;
static bool do_kmalloc = false;
static struct device* my_device;
static unsigned long addr;
static int ioctl_size;

// fops

/*
 * This is the ioctl implementation.
 */
static long kern_unlocked_ioctll(struct file *filp, unsigned int cmd, unsigned long arg) {
	//int i;
	char str[256];
	void* ptr;
	unsigned int order;

	unsigned long private;
	unsigned long adjusted;
	unsigned int diff;
	int ret;
	struct vm_area_struct *vma;
	struct mm_struct* mm;
	void* kernel_addr;
	unsigned long flags;
	PR_DEBUG("start");
	switch (cmd) {
	/*
	 *	Exploring VMA issues
	 */
	case 0:
		ptr = (void *)arg;
		PR_DEBUG("ptr is %p", ptr);
		vma = find_vma(current->mm, arg);
		PR_DEBUG("vma is %p", vma);
		diff = arg - vma->vm_start;
		PR_DEBUG("diff is %d", diff);
		private = (unsigned long)vma->vm_private_data;
		PR_DEBUG("private (ul) is %lu", private);
		PR_DEBUG("private (p) is %p", (void *)private);
		adjusted = private + diff;
		PR_DEBUG("adjusted (ul) is %lu", adjusted);
		PR_DEBUG("adjusted (p) is %p", (void *)adjusted);
		return 0;

	/*
	 *	This is asking the kernel to read the memory
	 */
	case 1:
		PR_DEBUG("starting to read");
		memcpy(str, vaddr, 256);
		str[255] = '\0';
		PR_DEBUG("data is %s", str);
		return 0;

	/*
	 *	This is asking the kernel to write the memory
	 */
	case 2:
		PR_DEBUG("starting to write");
		memset(vaddr, arg, size);
		return 0;

	/*
	 *	This demos how to take the user space pointer and turn it
	 *	into a kernel space pointer
	 */
	case 3:
		PR_DEBUG("starting to write using us pointer");
		ptr = (void *)arg;
		PR_DEBUG("ptr is %p", ptr);
		return 0;

	/*
	 *	mmap a region from an ioctl
	 */
	case 4:
		PR_DEBUG("trying to mmap");

		/*
		 * if(do_kmalloc) {
		 *	kaddr=kmalloc(ioctl_size,GFP_KERNEL);
		 * } else {
		 *	order=get_order(ioctl_size);
		 *	kaddr=(void*)__get_free_pages(GFP_KERNEL,order);
		 * }
		 */
		mm = current->mm;
		flags = MAP_POPULATE | MAP_SHARED;
		flags &= ~(MAP_EXECUTABLE | MAP_DENYWRITE);
		// must hold process memory map semaphore because next function will change memory
		// layout for the process. This also means that this code must be in a path that can
		// sleep.
		down_write(&mm->mmap_sem);
		addr = do_mmap_pgoff(
			filp,/* file pointer */
			0,/* recommended use space address */
			ioctl_size,/* size */
			PROT_READ | PROT_WRITE,/* protection */
			flags,/* flags */
			0/* pg offset */
		);
		// remmember to release the semaphore!
		up_write(&mm->mmap_sem);
		//PR_DEBUG("kaddr is (p) %p",kaddr);
		//PR_DEBUG("real size is (d) %d",ioctl_size);
		PR_DEBUG("addr for user space is (lu) %lu / (p) %p", addr, (void *)addr);
		return(addr);

	/*
	 *	unmap a region
	 */
	case 5:
		PR_DEBUG("trying to unmap");
		vma = find_vma(current->mm, addr);
		kernel_addr = vma->vm_private_data;
		size = vma->vm_end - vma->vm_start;
		PR_DEBUG("deduced kernel_addr is %p", kernel_addr);
		PR_DEBUG("deduced size is (d) %d", size);
		PR_DEBUG("real size is (d) %d", ioctl_size);
		PR_DEBUG("real kaddr is (p) %p", kaddr);
		ret = do_munmap(current->mm, addr, ioctl_size);
		if (do_kmalloc) {
			kfree(kernel_addr);
		} else {
			order = get_order(size);
			free_pages((unsigned long)kernel_addr, order);
		}
		return(ret);

	/*
	 *	The the size of the region
	 */
	case 6:
		PR_DEBUG("setting the size");
		ioctl_size = arg;
		PR_DEBUG("size is %d", ioctl_size);
		return 0;
	}
	return(-EINVAL);
}

/*
 *	VMA ops
 */
void kern_vma_open(struct vm_area_struct *vma) {
	PR_DEBUG("start");
}

void kern_vma_close(struct vm_area_struct *vma) {
	unsigned int size = vma->vm_end - vma->vm_start;
	unsigned int order;
	void* addr = vma->vm_private_data;

	PR_DEBUG("start");
	PR_DEBUG("pointer as long is %lu", vma->vm_start);
	PR_DEBUG("pointer as pointer is %p", (void *)(vma->vm_start));
	PR_DEBUG("addr is %p", addr);
	PR_DEBUG("size is %d", size);
	if (do_kmalloc) {
		kfree(addr);
	} else {
		order = get_order(size);
		free_pages((unsigned int)addr, order);
	}
}


static struct vm_operations_struct kern_remap_vm_ops = {
	.open=kern_vma_open,
	.close = kern_vma_close,
};

/*
 *	This is the most basic mmap implementation. It does NOT work because
 *	you don't really state WHAT memory kernel side you are mapping to user
 *	space...
 */
static int kern_mmap(struct file *filp, struct vm_area_struct *vma) {
	unsigned int size, order, pg_num;
	unsigned long addr, phys;
	void* kaddr;

	PR_DEBUG("start");
	size = vma->vm_end - vma->vm_start;
	order = get_order(size);
	addr = __get_free_pages(GFP_KERNEL, order);
	kaddr = (void *)addr;
	phys = virt_to_phys(vaddr);
	pg_num = phys >> PAGE_SHIFT;
	if(remap_pfn_range(
		vma,// vma
		vma->vm_start,// start
		pg_num,// how many pages
		size,// size (derived from the vma)
		vma->vm_page_prot// protection
	)) {
		PR_DEBUG("error path");
		return(-EAGAIN);
	}
	vma->vm_ops = &kern_remap_vm_ops;
	vma->vm_private_data = kaddr;
	kern_vma_open(vma);
	return(0);
}

/*
 * The file operations structure.
 */
static struct file_operations my_fops = {
	.owner=THIS_MODULE,
	.unlocked_ioctl=kern_unlocked_ioctll,
	.mmap=kern_mmap,
};

#include "device.inc"
