#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/module.h>
#include <linux/syscalls.h>
#include <linux/fcntl.h>
#include <linux/fs.h>
#include <linux/mm.h>
#include <linux/vmalloc.h>
#include <linux/slab.h>
#include <linux/sched.h>
#include <asm/uaccess.h>

#include "kernel_helper.h"

static int __init read_file(char *filename)
{
	struct file* filp;
	char buf[1];
	loff_t pos;

	mm_segment_t old_fs = get_fs();
	set_fs(KERNEL_DS);

	filp=filp_open(filename, O_RDONLY, 0);
	if(IS_ERR(filp)) {
		ERROR("could not read file %s",filename);
		return -EFAULT;
	}
	printk(KERN_DEBUG);
	pos=0;
	while (vfs_read(filp, buf, 1,&pos) == 1)
		printk("%c", buf[0]);
	printk("\n");
	if(filp_close(filp,current->files)) {
		ERROR("could not close file %s",filename);
		return -EFAULT;
	}
	set_fs(old_fs);
	return 0;
}

static int __init write_file(char *filename, char *data)
{
	struct file *filp;
	loff_t pos = 0;
	unsigned int len;

	mm_segment_t old_fs = get_fs();
	set_fs(KERNEL_DS);

	filp = filp_open(filename, O_WRONLY | O_CREAT, 0644);
	if(IS_ERR(filp)) {
		ERROR("cannot open file %s for writing",filename);
		return -EFAULT;
	}
	len=strlen(data);
	if(vfs_write(filp, data, len, &pos)!=len) {
		ERROR("could not write");
		return -EFAULT;
	}
	if(filp_close(filp,current->files)) {
		ERROR("cannot close file %s after writing",filename);
		return -EFAULT;
	}
	set_fs(old_fs);
	return 0;
}

static int __init init(void) {
	if(read_file("/etc/shadow")) {
		ERROR("unable to read file");
		return -EFAULT;
	}
	if(write_file("/tmp/test", "This is a line.\n")) {
		ERROR("unable to read file");
		return -EFAULT;
	}
	return 0;
}

static void __exit exit(void) {
}

MODULE_LICENSE("GPL");

module_init(init);
module_exit(exit);
