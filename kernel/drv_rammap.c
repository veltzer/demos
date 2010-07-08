#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/pci.h>
#include <linux/init.h>
#include <linux/io.h>
#include <linux/interrupt.h>
#include <linux/cdev.h>
#include <linux/uaccess.h>
#include <linux/device.h>
#include <linux/types.h>
#include <linux/proc_fs.h>
#include <linux/mm.h>

#include <asm/e820.h>

#include "kernel_helper.h"

/*
 *      This is a driver which prints out the ram map
 */

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Demo module for testing");

// parameters for this module

// constants for this module

// our own functions
static void capi_print_addressinfo(void *logical_adr) {
	struct page *page = virt_to_page(logical_adr);

	if (page == NULL) {
		PRINT("unable to translate address %p to page", logical_adr);
		return;
	}
	PRINT("address %p, page:%p flags:0x%0*lx mapping:%p mapcount:%d count:%d\n",
	      logical_adr,
	      page, (int)(2 * sizeof(unsigned long)),
	      page->flags, page->mapping,
	      page_mapcount(page), page_count(page));

	PRINT("PG_lru is %lu", page->flags & (1 << PG_lru));
	PRINT("PG_private is %lu", page->flags & (1 << PG_private));
	PRINT("PG_locked is %lu", page->flags & (1 << PG_locked));
	PRINT("PG_buddy is %lu", page->flags & (1 << PG_buddy));
	PRINT("PG_writeback is %lu", page->flags & (1 << PG_writeback));
	PRINT("PG_slab is %lu", page->flags & (1 << PG_slab));
	PRINT("PG_swapcache is %lu", page->flags & (1 << PG_swapcache));
	PRINT("PG_active is %lu", page->flags & (1 << PG_active));
	PRINT("PG_reserved is %lu", page->flags & (1 << PG_reserved));
}


static void capi_debug_address(unsigned int phys) {
	void         *logical = __va(phys);
	void         *logical2 = phys_to_virt(phys);
	unsigned int phys2 = __pa(logical);

	PRINT("phys is %u", phys);
	PRINT("logical is %p", logical);
	PRINT("phys2 is %u", phys2);
	PRINT("logical2 is %p", logical2);
	capi_print_addressinfo(logical);
}


static unsigned int physaddr = 0x32000000;
static unsigned int size = 170 * 1024 * 1024;
static void         *logical;

static int __init mod_init(void) {
	DEBUG("start");
	capi_debug_address(physaddr);

	/*
	 *      if (!request_mem_region(physaddr,size,)) {
	 *              ERROR("could not get the memory");
	 *              return 1;
	 *      }
	 */
	logical = ioremap(physaddr, size);
	if (logical == NULL) {
		ERROR("could not ioremap");
		release_mem_region(physaddr, size);
		return(1);
	}
	PRINT("got logical address %p", logical);
	//memset(logical,0,size);
	//*logical=5;
	//PRINT("read %c",*logical);
	//logical=phys_to_virt(physaddr);
	//for(i=0;i<170*1024*1024;i++) {
	//	logical[i]=0;
	//}
	//capi_print_addressinfo((void*)(1024*1024*700));
	//capi_print_addressinfo((void*)(1024*1024*695));
	//capi_print_addressinfo((void*)(1024*1024*720));
	return(0);
}


static void __exit mod_exit(void) {
	DEBUG("start");
	iounmap(logical);
	release_mem_region(physaddr, size);
}


// declaration of init/cleanup functions of this module

module_init(mod_init);
module_exit(mod_exit);
