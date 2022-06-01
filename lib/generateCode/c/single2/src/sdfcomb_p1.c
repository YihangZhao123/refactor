/* Includes */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_p1.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */

extern ref_fifo fifo_s6;
extern spinlock spinlock_s6;	

extern ref_fifo fifo_s_in;
extern spinlock spinlock_s_in;	
/* Output FIFO */
extern ref_fifo fifo_s1;
extern spinlock spinlock_s1;
/*
========================================
	Declare Extern Global Variables
========================================
*/			
	
/*
========================================
	Actor Function
========================================
*/	
/*  initialize memory*/
void actor_p1(){

	/* Read From Input Port  */
	int ret=0;

	
	/* Inline Code           */
	
	/* Write To Output Ports */

}
