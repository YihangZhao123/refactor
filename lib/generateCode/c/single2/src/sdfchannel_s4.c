#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s4[2];
	size_t buffer_s4_size = 2;
	ref_fifo  fifo_s4;
	spinlock spinlock_s4={.flag=0};
