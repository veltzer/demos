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
#include <linux/delay.h> // for ssleep()

#include "kernel_helper.h" // our own helper

/*
 *      This driver explores the race condition between ioctl and close
 */

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Demo module for testing");

// parameters for this module

static int chrdev_alloc_dynamic = 1;
module_param(chrdev_alloc_dynamic, bool, 0444);
MODULE_PARM_DESC(chrdev_alloc_dynamic, "Allocate the device number dynamically?");

static int first_minor = 0;
module_param(first_minor, int, 0444);
MODULE_PARM_DESC(first_minor, "first minor to allocate in dynamic mode (usually best to keep at 0)");

static int kern_major = 253;
module_param(kern_major, int, 0444);
MODULE_PARM_DESC(kern_major, "major to allocate in static mode");

static int kern_minor = 0;
module_param(kern_minor, int, 0444);
MODULE_PARM_DESC(kern_minor, "minor to allocate in static mode");

// constants for this module

// number of files we expose via the chr dev
static const int MINORS_COUNT = 1;

int register_dev(void);
void unregister_dev(void);

// our own functions
static int __init mod_init(void) {
	return(register_dev());
}


static void __exit mod_exit(void) {
	unregister_dev();
}


// declaration of init/cleanup functions of this module

module_init(mod_init);
module_exit(mod_exit);

// first the structures

struct kern_dev {
	// pointer to the first device number allocated to us
	dev_t first_dev;
	// cdev structures for the char devices we expose to user space
	struct cdev cdev;
};

// static data
static struct kern_dev *pdev;
static struct class    *my_class;
static struct device   *my_device;

// now the functions

/*
 * This is the ioctl implementation.
 */
static long kern_ioctl(struct file *filp, unsigned int cmd, unsigned long arg) {
	INFO("start");
	switch (cmd) {
	case 0:
		// empty ioctl to 'just work';
		INFO("end");
		return(0);

	case 1:
		// long ioctl to stall for time
		ssleep(10);
		INFO("end");
		return(0);

	case 2:
		// long ioctl to stall for time
		ssleep(10000);
		INFO("end");
		return(0);
	}
	INFO("end");
	return(-EINVAL);
}


/*
 * The release implementation. Currently this does nothing
 */
static int kern_release(struct inode *inode, struct file *filp) {
	INFO("start");
	ssleep(2);
	INFO("end");
	return(0);
}


/*
 * The file operations structure.
 */
static struct file_operations my_fops = {
	.owner   = THIS_MODULE,
	.release = kern_release,
	.unlocked_ioctl   = kern_ioctl,
};

int register_dev(void) {
	// create a class
	my_class = class_create(THIS_MODULE, THIS_MODULE->name);
	if (IS_ERR(my_class)) {
		goto goto_nothing;
	}
	INFO("created the class");
	// alloc and zero
	pdev = kmalloc(sizeof(struct kern_dev), GFP_KERNEL);
	if (pdev == NULL) {
		goto goto_destroy;
	}
	memset(pdev, 0, sizeof(struct kern_dev));
	if (chrdev_alloc_dynamic) {
		if (alloc_chrdev_region(&pdev->first_dev, first_minor, MINORS_COUNT, THIS_MODULE->name)) {
			INFO("cannot alloc_chrdev_region");
			goto goto_dealloc;
		}
	} else {
		pdev->first_dev = MKDEV(kern_major, kern_minor);
		if (register_chrdev_region(pdev->first_dev, MINORS_COUNT, THIS_MODULE->name)) {
			INFO("cannot register_chrdev_region");
			goto goto_dealloc;
		}
	}
	INFO("allocated the device");
	// create the add the sync device
	cdev_init(&pdev->cdev, &my_fops);
	pdev->cdev.owner = THIS_MODULE;
	pdev->cdev.ops = &my_fops;
	kobject_set_name(&pdev->cdev.kobj, THIS_MODULE->name);
	if (cdev_add(&pdev->cdev, pdev->first_dev, 1)) {
		INFO("cannot cdev_add");
		goto goto_deregister;
	}
	INFO("added the device");
	// now register it in /dev
	my_device = device_create(
	        my_class,                                                                                                                   /* our class */
	        NULL,                                                                                                                       /* device we are subdevices of */
	        pdev->first_dev,
	        NULL,
	        THIS_MODULE->name,
	        0
	        );
	if (my_device == NULL) {
		INFO("cannot create device");
		goto goto_create_device;
	}
	INFO("did device_create");
	return(0);

	//goto_all:
	//	device_destroy(my_class,pdev->first_dev);
goto_create_device:
	cdev_del(&pdev->cdev);
goto_deregister:
	unregister_chrdev_region(pdev->first_dev, MINORS_COUNT);
goto_dealloc:
	kfree(pdev);
goto_destroy:
	class_destroy(my_class);
goto_nothing:
	return(-1);
}

void unregister_dev(void) {
	device_destroy(my_class, pdev->first_dev);
	cdev_del(&pdev->cdev);
	unregister_chrdev_region(pdev->first_dev, MINORS_COUNT);
	kfree(pdev);
	class_destroy(my_class);
}
