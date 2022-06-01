#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
	
	void* buffer_s3[3];
	size_t buffer_s3_size = 3;
	ref_fifo  fifo_s3;
	spinlock spinlock_s3={.flag=0};
