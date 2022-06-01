#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s5[3];
	size_t buffer_s5_size = 3;
	ref_fifo  fifo_s5;
	spinlock spinlock_s5={.flag=0};
