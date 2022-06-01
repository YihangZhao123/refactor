/* Includes */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_p2.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */

extern ref_fifo fifo_s1;
extern spinlock spinlock_s1;	
/* Output FIFO */
extern ref_fifo fifo_s2;
extern spinlock spinlock_s2;
extern ref_fifo fifo_s3;
extern spinlock spinlock_s3;
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
void actor_p2(){

	/* Read From Input Port  */
	int ret=0;

	
	/* Inline Code           */
	
	/* Write To Output Ports */

}
