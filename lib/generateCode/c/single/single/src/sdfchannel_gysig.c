#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
					
	void* buffer_gysig[7];
	size_t buffer_gysig_size = 7;
	ref_fifo  fifo_gysig;
	spinlock spinlock_gysig={.flag=0};
