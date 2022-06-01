#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s2[2];
	size_t buffer_s2_size = 2;
	ref_fifo  fifo_s2;
	spinlock spinlock_s2={.flag=0};
