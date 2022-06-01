#ifndef CIRCULAR_FIFO_LIB_H_
#define CIRCULAR_FIFO_LIB_H_
#include "config.h"

/*
************************************************************
This header file defines all the prototype of token types in
SDFChannels
************************************************************
*/


#include "datatype_definition.h"

#include "spinlock.h"	
	typedef struct{
		void**  buffer;
		size_t front;
		size_t rear;
		size_t size;
		
	}ref_fifo;		
	
	void ref_init(ref_fifo* fifo_ptr, void** buffer, size_t size);
	void read_non_blocking(ref_fifo* fifo_ptr, void** dst);
	void write_non_blocking(ref_fifo* fifo_ptr, void* src);			


#endif
