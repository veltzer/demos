#ifndef __shared_h
#define __shared_h

// randomly chosen
#define DEMO_MAGIC					'd'

// misc - needs to be sorted out...
#define IOCTL_DEMO_MAP					_IO(DEMO_MAGIC, 0)
#define IOCTL_DEMO_UNMAP				_IO(DEMO_MAGIC, 1)
#define IOCTL_DEMO_READ					_IO(DEMO_MAGIC, 2)
#define IOCTL_DEMO_WRITE				_IO(DEMO_MAGIC, 3)
#define IOCTL_DEMO_CHECK				_IO(DEMO_MAGIC, 4)
#define IOCTL_DEMO_COPY					_IO(DEMO_MAGIC, 5)
#define IOCTL_DEMO_KMALLOC				_IO(DEMO_MAGIC, 6)
#define IOCTL_DEMO_GET_FREE_PAGES			_IO(DEMO_MAGIC, 7)
#define IOCTL_DEMO_PCI_ALLOC_CONSISTENT			_IO(DEMO_MAGIC, 8)
#define IOCTL_DEMO_DMA_ALLOC_COHERENT			_IO(DEMO_MAGIC, 9)

// timing
#define IOCTL_TIMING_CLOCK				_IO(DEMO_MAGIC, 10)
#define IOCTL_TIMING_TSC				_IO(DEMO_MAGIC, 11)
#define IOCTL_TIMING_JIFFIES				_IO(DEMO_MAGIC, 12)
#define IOCTL_TIMING_EMPTY				_IO(DEMO_MAGIC, 13)

// list
#define IOCTL_LIST_CREATE				_IO(DEMO_MAGIC, 14)
#define IOCTL_LIST_DESTROY				_IO(DEMO_MAGIC, 15)
#define IOCTL_LIST_ISEMPTY				_IO(DEMO_MAGIC, 16)
#define IOCTL_LIST_ADD					_IO(DEMO_MAGIC, 17)
#define IOCTL_LIST_DEL					_IO(DEMO_MAGIC, 18)
#define IOCTL_LIST_PRINT				_IO(DEMO_MAGIC, 19)

// completion
#define IOCTL_COMPLETE_INIT				_IO(DEMO_MAGIC,20)
#define IOCTL_COMPLETE_INIT_MACRO			_IO(DEMO_MAGIC,21)
#define IOCTL_COMPLETE_WAIT				_IO(DEMO_MAGIC,22)
#define IOCTL_COMPLETE_WAIT_INTERRUPTIBLE		_IO(DEMO_MAGIC,23)
#define IOCTL_COMPLETE_WAIT_INTERRUPTIBLE_TIMEOUT	_IO(DEMO_MAGIC,24)
#define IOCTL_COMPLETE_COMPLETE				_IO(DEMO_MAGIC,25)
#define IOCTL_COMPLETE_COMPLETE_ALL			_IO(DEMO_MAGIC,26)

// div
#define IOCTL_DIV_DOOPS					_IO(DEMO_MAGIC,27)

// eventfd
#define IOCTL_EVENTFD_SIGNAL				_IO(DEMO_MAGIC,28)

// spinlock
#define IOCTL_SPINLOCK_IRQSAVE				_IO(DEMO_MAGIC,29)
#define IOCTL_SPINLOCK_REGULAR				_IO(DEMO_MAGIC,30)
#define IOCTL_SPINLOCK_ALLOCATED			_IO(DEMO_MAGIC,31)

// mmap
#define IOCTL_MMAP_PRINT				_IO(DEMO_MAGIC,32)
#define IOCTL_MMAP_READ					_IO(DEMO_MAGIC,33)
#define IOCTL_MMAP_WRITE				_IO(DEMO_MAGIC,34)
#define IOCTL_MMAP_WRITE_USER				_IO(DEMO_MAGIC,35)
#define IOCTL_MMAP_MMAP					_IO(DEMO_MAGIC,36)
#define IOCTL_MMAP_UNMAP				_IO(DEMO_MAGIC,37)
#define IOCTL_MMAP_SETSIZE				_IO(DEMO_MAGIC,38)

// race
#define IOCTL_RACE_EMPTY				_IO(DEMO_MAGIC,39)
#define IOCTL_RACE_10					_IO(DEMO_MAGIC,40)
#define IOCTL_RACE_10000				_IO(DEMO_MAGIC,41)

typedef struct _buffer
{
	unsigned long long u1;
	unsigned long long u2;
	unsigned long long udiv;
	unsigned long long umul;
	unsigned long long uadd;
	unsigned long long usub;
	long long d1;
	long long d2;
	long long ddiv;
	long long dmul;
	long long dadd;
	long long dsub;
} buffer;

typedef struct _BufferStruct {
	void* pointer;
	unsigned int size;
} BufferStruct;

#endif // __shared_h
