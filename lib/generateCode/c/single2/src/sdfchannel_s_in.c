#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s_in[11];
	size_t buffer_s_in_size = 11;
	ref_fifo  fifo_s_in;
	spinlock spinlock_s_in={.flag=0};
