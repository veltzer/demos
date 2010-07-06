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

// and here is the division function:

/*
 * 64bit division - for sync stuff..
 */

/*
 #define udiv_qrnnd(q, r, n1, n0, d) \
 * __asm__ ("divl %4" \
 *         : "=a" ((u32)(q)), \
 *           "=d" ((u32)(r)) \
 *         : "0" ((u32)(n0)), \
 *           "1" ((u32)(n1)), \
 *           "rm" ((u32)(d)))
 *
 #define u64_div(x,y,q) do {u32 __tmp; udiv_qrnnd(q, __tmp, (x)>>32, x, y);} while (0)
 #define u64_mod(x,y,r) do {u32 __tmp; udiv_qrnnd(__tmp, q, (x)>>32, x, y);} while (0)
 #define u64_divmod(x,y,q,r) udiv_qrnnd(q, r, (x)>>32, x, y)
 */

/*
 #define _FP_W_TYPE_SIZE         32
 #define _FP_W_TYPE              unsigned int
 #define _FP_WS_TYPE             signed int
 #define _FP_I_TYPE              int
 *
 #include <math-emu/op-1.h>
 #include <math-emu/op-2.h>
 #include <math-emu/op-4.h>
 #include <math-emu/op-common.h>
 */
// there is no such file for x86
//#include <asm/sfp-machine.h>
// creates compilation issues...
//#include <math-emu/soft-fp.h>
// there is no such file for x86
//#include <math-emu/sfp-util.h>

// this is what gives us the division...
#include <asm/div64.h>

unsigned long long __udivdi3(unsigned long long divided, unsigned long long divisor)
{
	unsigned int reminder;

	DEBUG("divided is %llu", divided);
	DEBUG("divisor is %llu", divisor);
	return(div_u64_rem(divided, divisor, &reminder));
}


long long __divdi3(long long divided, long long divisor)
{
	unsigned int reminder;

	DEBUG("divided is %lld", divided);
	DEBUG("divisor is %lld", divisor);
	return(div_u64_rem(divided, divisor, &reminder));
}
