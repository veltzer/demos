//#define DEBUG
#include<linux/module.h> // for MODULE_*
#include<linux/fs.h> // for fops
#include<linux/device.h> // for struct device
#include<linux/delay.h> // for udelay
#include<linux/cpufreq.h> // for cpufreq_*
#include<linux/cpu.h> // for getting the number of cpus

#include"shared.h"

//#define DO_DEBUG
#include"kernel_helper.h" // our own helper

/*
 *	This driver shows how various timing delays are done in the kernel
 */

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Mark Veltzer");
MODULE_DESCRIPTION("Modules for testing timing");

// static data
static struct device* my_device;

/*
 * This is the ioctl implementation.
 */
// cycles_t is actually unsigned long long (look at arch/x86/include/asm/tsc.h).
static long kern_unlocked_ioctll(struct file *filp, unsigned int cmd, unsigned long arg) {
	// for register measurements...
	cycles_t curreg, cnt1, cnt2;
	unsigned long cdiff, crmic;
	// for jiffies measurements...
	unsigned long j1, j2, jdiff, jmil, jmic;
	unsigned int freq;

	PR_DEBUG("start");
	//char str[256];
	//PR_DEBUG(str,"start");
	switch (cmd) {
	case IOCTL_TIMING_CLOCK:
		/* first lets get the number of cpus */
		//cpus=get_online_cpus();
		/* this shows how to work with the x86 counters */
		curreg = get_cycles();
		pr_info("get_cycles: %llu\n", curreg);
		// getting the cpufreq for cpu0
		// I used the quick version under the assumption that the
		// frequency doesn't change. If this assumption is not
		// correct and the cpu scales for some reason you need
		// to use 'cpufreq_get'.
		freq = cpufreq_quick_get(0);
		pr_info("cpufreq_quick_get: %i\n", freq);
		freq = cpufreq_get(0);
		pr_info("cpufreq_get: %i\n", freq);
		return 0;

	case IOCTL_TIMING_TSC:
		/* this is how to measure the speed of some code using counters */
		freq = cpufreq_quick_get(0);
		cnt1 = get_cycles();
		udelay(arg);
		//msleep(mic/1000);
		//ssleep(mic/1000000);
		cnt2 = get_cycles();
		cdiff = cnt2 - cnt1;
		crmic = (cdiff * 1000) / freq;
		pr_info("freq: %i\n", freq);
		pr_info("cnt1: %llu\n", cnt1);
		pr_info("cnt2: %llu\n", cnt2);
		pr_info("cdiff: %lu\n", cdiff);
		pr_info("micros: %lu\n", crmic);
		return 0;
	case IOCTL_TIMING_JIFFIES:

		/*
		 *	This shows how to work with jiffies...
		 *	It will demostrate the most important attribute of jiffies and that
		 *	is that jiffies DO NOT change
		 */
		j1 = jiffies;
		udelay(arg);
		//msleep(mic/1000);
		//ssleep(mic/1000000);
		j2 = jiffies;
		jdiff = (j2 - j1) * 1000;
		//jmic=do_div(jdiff,HZ);
		jmil = jdiff / HZ;
		jmic = jmil * 1000;
		pr_info("j1 is %lu", j1);
		pr_info("j2 is %lu", j2);
		pr_info("jdiff is %lu", jdiff);
		pr_info("HZ is %d", HZ);
		pr_info("jmil is %lu", jmil);
		pr_info("jmic is %lu", jmic);
		return 0;

	case IOCTL_TIMING_EMPTY:
		/*
		 *	This syscall does nothing on purpose to enable timing
		 *	from user space
		 */
		return 0;
	}
	return(-EINVAL);
}


/*
 * The file operations structure.
 */
static struct file_operations my_fops = {
	.owner = THIS_MODULE,
	.unlocked_ioctl = kern_unlocked_ioctll,
};

#include"device.inc"
