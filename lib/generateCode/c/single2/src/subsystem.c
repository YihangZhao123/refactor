#include "../inc/subsystem.h"
#include <stdio.h>
#include "../inc/sdfcomb_p1.h"
#include "../inc/sdfcomb_p2.h"
#include "../inc/sdfcomb_p3.h"
#include "../inc/sdfcomb_p4.h"
#include "../inc/sdfcomb_p5.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
/*
==============================================
	Subsystem Function
==============================================
*/	
int subsystem(){
		//printf("%s\n","enter p1");
			actor_p1();
		//printf("%s\n","enter p2");
			actor_p2();
		//printf("%s\n","enter p4");
			actor_p4();
		//printf("%s\n","enter p5");
			actor_p5();
		//printf("%s\n","enter p1");
			actor_p1();
		//printf("%s\n","enter p2");
			actor_p2();
		//printf("%s\n","enter p4");
			actor_p4();
		//printf("%s\n","enter p5");
			actor_p5();
		//printf("%s\n","enter p3");
			actor_p3();
}


	/*
	*********************************************************
	Initialize All the Channels
	Should be called before subsystem_single_uniprocessor()
	*********************************************************
	*/
int init_subsystem(){
	/* Extern Variables */
	
	/* extern sdfchannel s_out*/
	extern void* buffer_s_out[];
	extern size_t buffer_s_out_size;
	extern ref_fifo fifo_s_out;
	/* extern sdfchannel s4*/
	extern void* buffer_s4[];
	extern size_t buffer_s4_size;
	extern ref_fifo fifo_s4;
	/* extern sdfchannel s5*/
	extern void* buffer_s5[];
	extern size_t buffer_s5_size;
	extern ref_fifo fifo_s5;
	/* extern sdfchannel s6*/
	extern void* buffer_s6[];
	extern size_t buffer_s6_size;
	extern ref_fifo fifo_s6;
	/* extern sdfchannel s_in*/
	extern void* buffer_s_in[];
	extern size_t buffer_s_in_size;
	extern ref_fifo fifo_s_in;
	/* extern sdfchannel s1*/
	extern void* buffer_s1[];
	extern size_t buffer_s1_size;
	extern ref_fifo fifo_s1;
	/* extern sdfchannel s2*/
	extern void* buffer_s2[];
	extern size_t buffer_s2_size;
	extern ref_fifo fifo_s2;
	/* extern sdfchannel s3*/
	extern void* buffer_s3[];
	extern size_t buffer_s3_size;
	extern ref_fifo fifo_s3;
	
	/* initialize the channels*/
		ref_init(&fifo_s_out,buffer_s_out,buffer_s_out_size);
		ref_init(&fifo_s4,buffer_s4,buffer_s4_size);
		ref_init(&fifo_s5,buffer_s5,buffer_s5_size);
		ref_init(&fifo_s6,buffer_s6,buffer_s6_size);
		ref_init(&fifo_s_in,buffer_s_in,buffer_s_in_size);
		ref_init(&fifo_s1,buffer_s1,buffer_s1_size);
		ref_init(&fifo_s2,buffer_s2,buffer_s2_size);
		ref_init(&fifo_s3,buffer_s3,buffer_s3_size);
		
		return 0;
	}		


