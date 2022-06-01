#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s_out[11];
	size_t buffer_s_out_size = 11;
	ref_fifo  fifo_s_out;
	spinlock spinlock_s_out={.flag=0};
