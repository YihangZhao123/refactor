/* Includes */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_p4.h"

/*
========================================
Declare Extern Channal Variables
========================================
*/
/* Input FIFO */

extern ref_fifo fifo_s2;
extern spinlock spinlock_s2;	
/* Output FIFO */
extern ref_fifo fifo_s_out;
extern spinlock spinlock_s_out;
extern ref_fifo fifo_s4;
extern spinlock spinlock_s4;
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
void actor_p4(){

	/* Read From Input Port  */
	int ret=0;

	
	/* Inline Code           */
	
	/* Write To Output Ports */

}
