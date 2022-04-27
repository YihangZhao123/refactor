#include "../inc/sdfchannel_gysig.h"
#include <stdio.h>
volatile spinlock spinlock_gysig={.flag=0};
unsigned long buffersize_gysig = 7;
volatile token_gysig arr_gysig[7];
circularFIFO_gysig channel_gysig;

void init_circularFIFO_gysig(circularFIFO_gysig* channel ,token_gysig* buffer,size_t size){
		  
		    channel->buffer = buffer;
		    channel->size=size;
		    channel->front = 0;
		    channel->rear = 0;			
		}

	/* 
	read a token from channel.
	src: is channel gysig
	dst:data
	*/
inline int read_circularFIFO_non_blocking_gysig(circularFIFO_gysig* channel, token_gysig* data){
		if(channel->front==channel->rear){
		    	//empty 
		    	return -1;
		    			
		   }else{
		    	*data = channel->buffer[channel->front];
		    	//printf("buffer gysig: before read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
		    	channel->front= (channel->front+1)%channel->size;
		    	//printf("buffer gysig: after read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
		    	return 0;
		    }
	}
	
	/*
		write a token to _circular gysig.
	
			
	*/
inline int write_circularFIFO_non_blocking_gysig(circularFIFO_gysig* channel, token_gysig value){
	    /*if the buffer is full*/
	    if((channel->rear+1)%channel->size == channel->front){
	        //full!
	        //discard the data
	        //printf("buffer full error\n!");
	        return -1;
	     }else{
	        channel->buffer[channel->rear] = value;
	       //printf("buffer gysig:before write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
	        channel->rear= (channel->rear+1)%channel->size;
	        //printf("buffer gysig:after write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
	        return 0;
	    }			
		
	}	
	
inline	int read_circularFIFO_blocking_gysig(circularFIFO_gysig* channel, token_gysig* data,spinlock* lock){
		spinlock_get(lock);
		if(channel->front==channel->rear){
		    	//empty 
		    	spinlock_release(lock);
		    	return -1;
		    			
		   }else{
		    	*data = channel->buffer[channel->front];
		    	//printf("buffer gysig: before read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
		    	channel->front= (channel->front+1)%channel->size;
		    	//printf("buffer gysig: after read, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
		    	spinlock_release(lock);
		    	return 0;
		    }
	}

inline int write_circularFIFO_blocking_gysig(circularFIFO_gysig* channel, token_gysig value,spinlock* lock){
	spinlock_get(lock);
	
	   /*if the buffer is full*/
	   if((channel->rear+1)%channel->size == channel->front){
	       //full!
	       //discard the data
	       //printf("buffer full error\n!");
	       spinlock_release(lock);
	       return -1;
	    }else{
	       channel->buffer[channel->rear] = value;
	      //printf("buffer gysig:before write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
	       channel->rear= (channel->rear+1)%channel->size;
	       //printf("buffer gysig:after write, front: %d, rear %d size:%d\n",channel->front,channel->rear,channel->size);
	       spinlock_release(lock);
	       return 0;
	   }				
}			
