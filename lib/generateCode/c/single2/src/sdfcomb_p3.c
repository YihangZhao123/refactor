/* Includes */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_p3.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */

extern ref_fifo fifo_s5;
extern spinlock spinlock_s5;	

extern ref_fifo fifo_s3;
extern spinlock spinlock_s3;	
/* Output FIFO */
extern ref_fifo fifo_s6;
extern spinlock spinlock_s6;
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
void actor_p3(){

	/* Read From Input Port  */
	int ret=0;

	
	/* Inline Code           */
	
	/* Write To Output Ports */

}
