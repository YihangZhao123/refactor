#include "../inc/config.h"
#include "../inc/circular_fifo_lib.h"
					
	void* buffer_GrayScaleToAbs[3];
	size_t buffer_GrayScaleToAbs_size = 3;
	ref_fifo  fifo_GrayScaleToAbs;
	spinlock spinlock_GrayScaleToAbs={.flag=0};
