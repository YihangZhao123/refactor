/* Includes */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_p5.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */

extern ref_fifo fifo_s4;
extern spinlock spinlock_s4;	
/* Output FIFO */
extern ref_fifo fifo_s5;
extern spinlock spinlock_s5;
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
void actor_p5(){

	/* Read From Input Port  */
	int ret=0;

	
	/* Inline Code           */
	
	/* Write To Output Ports */

}
