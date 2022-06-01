#include "../inc/config.h"

/*
*******************************************************
	This file contains the function definition for 
	token types: 
	For each token type, there are five functions:
	init_channel_typeName(...)
	read_non_blocking_typeName(...)
	read_blocking_typeName(...)
	write_non_blocking_typeName(...)
	write_blocking_typeName(...)
*******************************************************
*/
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"

void ref_init(ref_fifo* fifo_ptr, void** buffer, size_t size){
	
	fifo_ptr->front=0;
	fifo_ptr->rear=0;
	fifo_ptr->buffer=buffer;
	fifo_ptr->size=size;
}
void read_non_blocking(ref_fifo* fifo_ptr, void** dst){
	if(fifo_ptr->front==fifo_ptr->rear){
	 //empty
	 return;
	}else{
		*dst = fifo_ptr->buffer[fifo_ptr->front];					
		fifo_ptr->front= (fifo_ptr->front+1)%fifo_ptr->size;
		return;
	}
}
void write_non_blocking(ref_fifo* channel, void* src){
    if((channel->rear+1)%channel->size == channel->front){
		//full
        return ;
     }else{
        channel->buffer[channel->rear] = src;
       channel->rear= (channel->rear+1)%channel->size;
        return;
    }					
}		



