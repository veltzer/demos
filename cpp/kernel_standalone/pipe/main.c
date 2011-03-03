#include <linux/module.h> // for MODULE_*, module_*
#include <linux/moduleparam.h> // for module_param, MODULE_PARM_DESC
#include <linux/fs.h> // for fops
#include <linux/device.h> // for class_create
#include <linux/slab.h> // for kzalloc
#include <asm/uaccess.h> // for copy_to_user, access_ok
#include <linux/cdev.h> // for cdev_*
#include <linux/sched.h> // for TASK_INTERRUPTIBLE and more constants
#include <linux/spinlock.h> // for spinlock_t and ops on it
#include <linux/wait.h> // for wait_queue_head_t and ops on it

#include "kernel_helper.h" // our own helper

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("A named pipe exercise");

/*
 * See the README.txt for the explanation of this exercise.
 *
 * TODO:
 */

static int pipes_count=8;
module_param(pipes_count, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
MODULE_PARM_DESC(pipes_count, "How many pipes to create ?");
static int pipe_size=PAGE_SIZE;
module_param(pipe_size, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
MODULE_PARM_DESC(pipe_size, "What is the pipe size ?");

// struct for each pipe
struct my_pipe {
	char* data;
	int size;
	int read_pos;
	int write_pos;
	wait_queue_head_t read_queue;
	wait_queue_head_t write_queue;
	spinlock_t lock;
	struct device* pipe_device;
};

static struct my_pipe* pipes;

static inline void init_pipe(struct my_pipe* pipe) {
	pipe->data=kzalloc(pipe_size,GFP_KERNEL);
	pipe->size=pipe_size;
	pipe->read_pos=0;
	pipe->write_pos=0;
	spin_lock_init(&pipe->lock);
	pipe->pipe_device=NULL;
	init_waitqueue_head(&pipe->read_queue);
	init_waitqueue_head(&pipe->write_queue);
}

static inline void del_pipe(struct my_pipe* pipe) {
	kfree(pipe->data);
}

static inline bool have_data(struct my_pipe* pipe) {
	return pipe->read_pos!=pipe->write_pos;
}

static inline int pipe_room(struct my_pipe* pipe) {
	if(pipe->read_pos<pipe->write_pos) {
		return pipe->write_pos-pipe->read_pos;
	} else {
		return pipe->size-pipe->read_pos+pipe->write_pos;
	}
}

static inline void pipe_lock(struct my_pipe* pipe) {
	spinlock_lock(&pipe->lock);
}
static inline void pipe_unlock(struct my_pipe* pipe) {
	spinlock_unlock(&pipe->lock);
}

// these are the actual operations

static ssize_t read_pipe(struct file * file, char __user * buf, size_t count, loff_t *ppos) {
	struct my_pipe*;
	INFO("start");
	pipe=pipes+file->minor;
	pipe_lock(pipe);
	if(pipe_room(pipe)) {
		pipe_unlock(pipe);
	} else {
		pipe_unlock(pipe);
		pipe_wait_read(pipe);
	}
	*ppos+=count;
	return count;
}

static ssize_t write_null(struct file * file, const char __user * buf, size_t count, loff_t *ppos) {
	struct my_pipe*;
	INFO("start");
	pipe=pipes+file->minor;
	pipe_lock(pipe);
	// lets check if we have room in the pipe
	int room=pipe_room(pipe);
	if(room>0) {
		int size_to_write=min(room,count);
		if(pipe->read_pos<pipe->write_pos) {
		}
		// we have room in the pipe
		// copy user data into the pipe
		pipe_unlock(pipe);
		return size_to_write;
	} else {
		// we have no room in the pipe
		// put the process to sleep
		pipe_unlock(pipe);
		pipe_wait_write(pipe);
		pipe_lock(pipe);
	}
	*ppos+=count;
	return count;
}

// this is the operations table
static const struct file_operations pipe_fops = {
	.read=read_pipe,
	.write=write_pipe,
};

// this variable will store the class
static struct class *my_class;
// this variable will hold our cdev struct
static struct cdev cdev;
// this is the first dev_t allocated to us...
static dev_t first_dev;
// this is our first minor
static int first_minor=0;

static int pipe_init(void) {
	int err=0;
	int i;
	// allocate all pipes
	pipes=(struct my_pipe*)kmalloc(sizeof(struct my_pipe)*pipes_count,GFP_KERNEL);
	// initialize all pipes
	for(i=0;i<pipes_count;i++) {
		init_pipe(pipes+i);
	}
	// allocate our own range of devices
	if((err=alloc_chrdev_region(&first_dev, first_minor, pipes_count, THIS_MODULE->name))) {
		ERROR("cannot alloc_chrdev_region");
		goto err_final;
	}
	INFO("allocated the region");
	// add the cdev structure
	cdev_init(&cdev, &pipe_fops);
	if((err=cdev_add(&cdev, first_dev, pipes_count))) {
		ERROR("cannot cdev_add");
		goto err_dealloc;
	}
	INFO("added the cdev");
	// this is creating a new class (/proc/devices)
	my_class=class_create(THIS_MODULE,THIS_MODULE->name);
	if(IS_ERR(my_class)) {
		ERROR("failed in class_create");
		err=PTR_ERR(my_class);
		goto err_cdev_del;
	}
	INFO("created the class");
	for(i=0;i<pipes_count;i++) {
	// and now lets auto-create a /dev/ node
		pipes[i].pipe_device=device_create(my_class, NULL, MKDEV(MAJOR(first_dev),first_minor+i),NULL,"%s%d",THIS_MODULE->name,i);
		if(IS_ERR(pipes[i].pipe_device)) {
			ERROR("failed in device_create");
			err=PTR_ERR(pipes[i].pipe_device);
			goto err_class;
		}
	}
	INFO("created the device");
	INFO("loaded ok");
	return 0;
//err_device:
	device_destroy(my_class, first_dev);
err_class:
	class_destroy(my_class);
err_cdev_del:
	cdev_del(&cdev);
err_dealloc:
	unregister_chrdev_region(first_dev, pipes_count);
err_final:
	return err;
}

static void pipe_exit(void) {
	int i;
	for(i=0;i<pipes_count;i++) {
		device_destroy(my_class,MKDEV(MAJOR(first_dev),i));
	}
	class_destroy(my_class);
	cdev_del(&cdev);
	unregister_chrdev_region(first_dev, pipes_count);
	for(i=0;i<pipes_count;i++) {
		del_pipe(pipes+i);
	}
	INFO("unloaded ok");
}

module_init(pipe_init);
module_exit(pipe_exit);
