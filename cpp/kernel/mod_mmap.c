//#define DEBUG
#include <linux/module.h> // for MODULE_*
#include <linux/fs.h> // for fops
#include <linux/device.h> // for struct device

#include <linux/kernel.h>
#include <linux/pci.h>
#include <linux/init.h>
#include <linux/io.h>
#include <linux/interrupt.h>
#include <linux/cdev.h>
#include <linux/uaccess.h>
#include <linux/types.h>
#include <linux/mm.h>
#include <linux/mman.h>
#include <linux/pagemap.h>

//#define DO_DEBUG
#include "kernel_helper.h" // our own helper

#include "shared.h"

/*
 *      This is a driver that maps memory allocated by the kernel into user space.
 *      The method is the regular mmap system call.
 *
 *      This driver also shows you how to implement mmap via a regular ioctl call.
 *
 *      TODO:
 *              - show how to kfree my pointer when munmap is made
 *              - separate the part of the driver that does the mmap via the ioctl call.
 */

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Demo module for testing");

#define DO_FREE
#define DO_RESERVE

// static data
static void          *vaddr;
static unsigned int size;
static unsigned int pg_num;
static unsigned long phys;
static unsigned int pages;
static bool do_kmalloc = true;
static struct device* my_device;
static unsigned long addr;
static int ioctl_size;
static void* kaddr;

// fops

/*
 * This is the ioctl implementation.
 */
static long kern_unlocked_ioctll(struct file *filp, unsigned int cmd, unsigned long arg) {
	//int i;
	char str[256];
	void         *ptr;
	unsigned int order;

	unsigned long private;
	unsigned long adjusted;
	unsigned int diff;
	int ret;
	struct vm_area_struct *vma;
	struct mm_struct      *mm;
	void                  *kernel_addr;
	unsigned long flags;
	PR_DEBUG("start");
	switch (cmd) {
	/*
	 *      Exploring VMA issues
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
	 *      This is asking the kernel to read the memory
	 */
	case 1:
		PR_DEBUG("starting to read");
		memcpy(str, vaddr, 256);
		str[255] = '\0';
		PR_DEBUG("data is %s", str);
		return 0;

	/*
	 *      This is asking the kernel to write the memory
	 */
	case 2:
		PR_DEBUG("starting to write");
		memset(vaddr, arg, size);
		return 0;

	/*
	 *      This demos how to take the user space pointer and turn it
	 *      into a kernel space pointer
	 */
	case 3:
		PR_DEBUG("starting to write using us pointer");
		ptr = (void *)arg;
		PR_DEBUG("ptr is %p", ptr);
		return 0;

	/*
	 *      mmap a region
	 */
	case 4:
		PR_DEBUG("trying to mmap");
		if (do_kmalloc) {
			kaddr = kmalloc(ioctl_size, GFP_KERNEL);
		} else {
			order = get_order(ioctl_size);
			kaddr = (void *)__get_free_pages(GFP_KERNEL, order);
		}
		mm = current->mm;
		flags = MAP_POPULATE | MAP_SHARED | MAP_LOCKED;
		flags &= ~(MAP_EXECUTABLE | MAP_DENYWRITE);
		down_write(&mm->mmap_sem);
		addr = do_mmap_pgoff(
		        filp,                                                                                                                                                                           /* file pointer */
		        (unsigned long)kaddr,                                                                                                                                                           /* address - this is the buffer we kmalloc'ed */
		        ioctl_size,                                                                                                                                                                     /* size */
		        PROT_READ | PROT_WRITE,                                                                                                                                                         /* protection */
		        flags,                                                                                                                                                                          /* flags */
		        0                                                                                                                                                                               /* pg offset */
		        );
		up_write(&mm->mmap_sem);
		PR_DEBUG("kaddr is (p) %p", kaddr);
		PR_DEBUG("real size is (d) %d", ioctl_size);
		PR_DEBUG("addr for user space is (lu) %lu / (p) %p", addr, (void *)addr);
		return(addr);

	/*
	 *      unmap a region
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
	 *      The the size of the region
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
 *      VMA ops
 */
void kern_vma_open(struct vm_area_struct *vma) {
	PR_DEBUG("start");
}


void kern_vma_close(struct vm_area_struct *vma) {
#ifdef DO_FREE
	unsigned int order;
#endif // DO_FREE
	unsigned int size = vma->vm_end - vma->vm_start;
	void         *addr = vma->vm_private_data;
	PR_DEBUG("start");
	PR_DEBUG("pointer as long is %lu", vma->vm_start);
	PR_DEBUG("pointer as pointer is %p", (void *)(vma->vm_start));
	PR_DEBUG("addr is %p", addr);
	PR_DEBUG("size is %d", size);
#ifdef DO_FREE
	if (do_kmalloc) {
		kfree(addr);
	} else {
		order = get_order(size);
		free_pages((unsigned long)addr, order);
	}
#endif // DO_FREE
}

static struct vm_operations_struct kern_remap_vm_ops = {
	.open  = kern_vma_open,
	.close = kern_vma_close,
};

/*
 *      This is the most basic mmap implementation. It does NOT work because
 *      you don't really state WHAT memory kernel side you are mapping to user
 *      space...
 */

/*
 * static int kern_mmap_simple(struct file *filp, struct vm_area_struct *vma) {
 *      return remap_pfn_range(
 *              vma, // vma
 *              vma->vm_start, // start
 *              vma->vm_pgoff, // page number
 *              vma->vm_end-vma->vm_start, // size
 *              vma->vm_page_prot // protection
 *      );
 * }
 */

/*
 * The mmap implementation.
 */
static int kern_mmap(struct file *filp, struct vm_area_struct *vma) {
#ifdef DO_RESERVE
	// for the reserve loop
	int i;
#endif // DO_RESERVE
	// for return values
	int ret;
	// for __get_user_pages
	int order = 0;
	PR_DEBUG("start");
	size = vma->vm_end - vma->vm_start;
	PR_DEBUG("size is %d", size);

	/*
	 *      This code uses kmalloc
	 */
	if (do_kmalloc) {
		// calculate number of pages needed
		pages = size / PAGE_SIZE;
		if (size % PAGE_SIZE > 0) {
			pages++;
		}
		vaddr = kmalloc(size, GFP_KERNEL);
#ifdef DO_RESERVE
		// reserve the pages
		for (i = 0; i < pages * PAGE_SIZE; i += PAGE_SIZE) {
			SetPageReserved(virt_to_page(((unsigned long)vaddr) + i));
		}
#endif // DO_RESERVE
	} else {
		/*
		 *      This code used __get_free_pages
		 */
		order = get_order(size);
		addr = __get_free_pages(
		        GFP_KERNEL,
		        order
		        );
		vaddr = (void *)addr;
		PR_DEBUG("addr is %lx", addr);
		PR_DEBUG("order is %d", order);
	}
	PR_DEBUG("vaddr is %p", vaddr);
	//memset(vaddr,'a',size);
	phys = virt_to_phys(vaddr);
	pg_num = phys >> PAGE_SHIFT;
	PR_DEBUG("phys is %lx", phys);
	PR_DEBUG("pg_num is %d", pg_num);
	//PR_DEBUG("pp is %p",pp);
	//PR_DEBUG("start_addr is %p",start_addr);
	PR_DEBUG("vm_start is %lx", vma->vm_start);
	PR_DEBUG("vm_end is %lx", vma->vm_end);
	PR_DEBUG("vm_pgoff is %lx", vma->vm_pgoff);
	ret = remap_pfn_range(
	        vma,
	        vma->vm_start,
	        pg_num,
	        size,
	        vma->vm_page_prot
	        );
	if (ret) {
		PR_DEBUG("in error path");
		if (do_kmalloc) {
			kfree(vaddr);
		} else {
			free_pages((unsigned long)addr, order);
		}
		return(ret);
	}
	//memset(vaddr,'b',size);
	vma->vm_private_data = vaddr;
	vma->vm_ops = &kern_remap_vm_ops;
	kern_vma_open(vma);
	return(0);
}


/*
 * The file operations structure.
 */
static struct file_operations my_fops = {
	.owner   = THIS_MODULE,
	.unlocked_ioctl   = kern_unlocked_ioctll,
	.mmap    = kern_mmap,
//	.mmap=kern_mmap_simple,
};

#include "device.inc"
