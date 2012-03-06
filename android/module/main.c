#include <linux/module.h>  // for MODULE_*, module_*

/*
 *	This is a minimal Hello, World! driver doing just init and cleanup
 */

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Hello Android module");

// our own functions
static int __init hello_init(void)
{
	printk(KERN_ALERT "hello android module\n");
	return (0);
}


static void __exit hello_exit(void)
{
	printk(KERN_ALERT "goodbye android module\n");
}

module_init(hello_init);
module_exit(hello_exit);
