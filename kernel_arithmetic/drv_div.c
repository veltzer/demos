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

#include "kernel_helper.h"

#include "buffer.h"

/*
 *      Driver that demostrates arithmetic operations in the kernel...
 */

// parameters for this module

static int chrdev_alloc_dynamic = 1;
static int first_minor = 0;
static int kern_major = 253;
static int kern_minor = 0;
static const int MINORS_COUNT = 1;

// first the structures

struct kern_dev {
	// pointer to the first device number allocated to us
	dev_t first_dev;
	// cdev structures for the char devices we expose to user space
	struct cdev cdev;
};

// static data
static struct kern_dev *pdev;
static const char      *name = "demo";
static struct class    *my_class;
static struct device   *my_device;

/*
 * This is the ioctl implementation. Currently this function supports
 * getting the image rows and columns
 */
static int kern_ioctl(struct inode *inode, struct file *filp, unsigned int cmd, unsigned long arg) {
	// the buffer which will be used for the transaction
	buffer b;

	DEBUG("start");
	switch (cmd) {
	case 0:
		// get the data from the user
		if (copy_from_user(&b, (void *)arg, sizeof(b))) {
			ERROR("problem with copy_from_user");
			return(-EFAULT);
		}
		DEBUG("after copy");
		PRINT("b.u1 is %llu", b.u1);
		PRINT("b.u2 is %llu", b.u2);
		PRINT("b.d1 is %lld", b.d1);
		PRINT("b.d2 is %lld", b.d2);
		b.udiv = b.u1 / b.u2;
		b.umul = b.u1 * b.u2;
		b.uadd = b.u1 + b.u2;
		b.usub = b.u1 - b.u2;
		b.ddiv = b.d1 / b.d2;
		b.dmul = b.d1 * b.d2;
		b.dadd = b.d1 + b.d2;
		b.dsub = b.d1 - b.d2;
		// copy the data back to the user
		if (copy_to_user((void *)arg, &b, sizeof(b))) {
			ERROR("problem with copy_to_user");
			return(-EFAULT);
		}
		// everything is ok
		return(0);

		break;
	}
	return(-EFAULT);
}


/*
 * The file operations structure.
 */
static struct file_operations my_fops = {
	.owner = THIS_MODULE,
	.ioctl = kern_ioctl,
};

static int register_dev(void) {
	// create a class
	my_class = class_create(THIS_MODULE, MYNAME);
	if (IS_ERR(my_class)) {
		goto goto_nothing;
	}
	DEBUG("created the class");
	// alloc and zero
	pdev = kmalloc(sizeof(struct kern_dev), GFP_KERNEL);
	if (pdev == NULL) {
		goto goto_destroy;
	}
	memset(pdev, 0, sizeof(struct kern_dev));
	DEBUG("set up the structure");
	if (chrdev_alloc_dynamic) {
		if (alloc_chrdev_region(&pdev->first_dev, first_minor, MINORS_COUNT, myname)) {
			DEBUG("cannot alloc_chrdev_region");
			goto goto_dealloc;
		}
	} else {
		pdev->first_dev = MKDEV(kern_major, kern_minor);
		if (register_chrdev_region(pdev->first_dev, MINORS_COUNT, myname)) {
			DEBUG("cannot register_chrdev_region");
			goto goto_dealloc;
		}
	}
	DEBUG("allocated the device");
	// create the add the sync device
	cdev_init(&pdev->cdev, &my_fops);
	pdev->cdev.owner = THIS_MODULE;
	pdev->cdev.ops = &my_fops;
	kobject_set_name(&pdev->cdev.kobj, MYNAME);
	if (cdev_add(&pdev->cdev, pdev->first_dev, 1)) {
		DEBUG("cannot cdev_add");
		goto goto_deregister;
	}
	DEBUG("added the device");
	// now register it in /dev
	my_device = device_create(
	        my_class,                                                                                                                   /* our class */
	        NULL,                                                                                                                       /* device we are subdevices of */
	        pdev->first_dev,
	        NULL,
	        "%s",
	        name
	        );
	if (my_device == NULL) {
		DEBUG("cannot create device");
		goto goto_create_device;
	}
	DEBUG("did device_create");
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


static void unregister_dev(void) {
	device_destroy(my_class, pdev->first_dev);
	cdev_del(&pdev->cdev);
	unregister_chrdev_region(pdev->first_dev, MINORS_COUNT);
	kfree(pdev);
	class_destroy(my_class);
}


static int __init mod_init(void) {
	return(register_dev());
}


static void __exit mod_exit(void) {
	unregister_dev();
}


module_init(mod_init);
module_exit(mod_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Demo module for testing");
