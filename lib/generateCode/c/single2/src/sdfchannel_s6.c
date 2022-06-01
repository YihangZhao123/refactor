#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s6[3];
	size_t buffer_s6_size = 3;
	ref_fifo  fifo_s6;
	spinlock spinlock_s6={.flag=0};
