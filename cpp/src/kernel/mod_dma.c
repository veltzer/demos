//#define DEBUG
#include<linux/module.h> // for MODULE_*
#include<linux/slab.h> // for the kmalloc API

//#define DO_DEBUG
#include"kernel_helper.h" // our own helper

/*
 *	This example demos how to allocate and deallocate coherent memory for DMA
 *	When runnig this on intel/ubuntu 10.04 these are the conclusions:
 *	- dma_alloc_coherent limit is 2Mb.
 *	- kmalloc(GFP_DMA) limit is 2Mb.
 *	- kmalloc(GFP_KERNEL) limit is 4Mb (which is still contiguous but ok
 *		to use for most hardware that does DMA).
 */

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Demo module for testing");

// parameters for this module

// constants for this module

// our own functions

//static dma_addr_t device_addr;

// just one test (allocate and deallocate)
//#defie DO_ONE
// allocate and free in a loop
//#define DO_LOOP
// slowly increase the size of the memory we are allocating until we bust...
#define DO_INC

static int __init mod_init(void) {
	void* vptr;
	//unsigned int device_addr;
#ifdef DO_LOOP
	int i;
#endif // DO_LOOP
#ifdef DO_INC
	unsigned int size;
	unsigned int inc;
	bool stop;
#endif // DO_INC
#ifdef DO_ONE
	const unsigned int size = 1024 * 1024 * 24;
#endif // DO_ONE
	PR_DEBUG("start");
#ifdef DO_LOOP
	for (i = 0; i < 1000; i++) {
		vptr = dma_alloc_coherent(NULL, size, &device_addr, GFP_KERNEL | GFP_DMA);
		if(vptr==NULL) {
			printk("ERROR! could not allocate memory");
		} else {
			dma_free_coherent(NULL, size, vptr, device_addr);
		}
	}
#endif // DO_LOOP
#ifdef DO_ONE
	vptr = dma_alloc_coherent(NULL, size, &device_addr, GFP_KERNEL | GFP_DMA);
	if(vptr!=NULL) {
		printk("vptr is %p\n", vptr);
		printk("size is %d\n", size);
		printk("device_addr is %d\n", device_addr);
		dma_free_coherent(NULL, size, vptr, device_addr);
	} else {
		printk("ERROR! could not allocate memory");
	}
#endif // DO_ONE
#ifdef DO_INC
	inc=1024*512;
	size=1024*512; // half a meg
	stop=false;
	while(!stop) {
		//vptr = dma_alloc_coherent(NULL, size, &device_addr, GFP_DMA);
		//vptr = kmalloc(size,GFP_DMA);
		vptr = kmalloc(size,GFP_KERNEL);
		if(vptr!=NULL) {
			printk("vptr is %p\n", vptr);
			printk("size is %d\n", size);
			//printk("device_addr is %d\n", device_addr);
			//dma_free_coherent(NULL, size, vptr, device_addr);
			kfree(vptr);
		} else {
			printk("ERROR! could not allocate memory for size %d",size);
			stop=true;
		}
		size+=inc;
	}
#endif // DO_INC
	PR_DEBUG("end");
	return(0);
}

static void __exit mod_exit(void) {
	PR_DEBUG("start");
	PR_DEBUG("end");
}

// declaration of init/cleanup functions of this module

module_init(mod_init);
module_exit(mod_exit);
