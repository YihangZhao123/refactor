/* Includes-------------------------- */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_Gx.h"



/*
========================================
	Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo_DoubleType fifo_gxsig;
extern spinlock spinlock_gxsig;
/* Output FIFO */
extern circular_fifo_DoubleType fifo_absxsig;
extern spinlock spinlock_absxsig;
/*
========================================
	Actor Function
========================================
*/			
void actor_Gx(){
	#if defined(TESTING)
	HAL_GPIO_WritePin(GPIOC,GPIO_PIN_8,1);
	#endif
	
	/* Initilize Memory      */
	DoubleType gx; 
	Array6OfDoubleType imgBlockX; 
	/* Read From Input Port  */
	for(int i=0;i<6;++i){
		#if GXSIG_BLOCKING==0
		read_non_blocking_DoubleType(&fifo_gxsig,&imgBlockX[i]);
		#else
		read_blocking_DoubleType(&fifo_gxsig,&imgBlockX[i],&spinlock_gxsig);
		#endif
	}
	
	/* Inline Code           */
	/* in combFunction GxImpl */
	gx=0;
	gx=gx-imgBlockX[0];
	gx=gx+imgBlockX[1];
	gx=gx-2.0*imgBlockX[2];
	gx=gx+2.0*imgBlockX[3];
	gx=gx-imgBlockX[4];
	gx=gx+imgBlockX[5];

	/* Write To Output Ports */
	#if ABSXSIG_BLOCKING==0
	write_non_blocking_DoubleType(&fifo_absxsig,gx);
	#else
	write_blocking_DoubleType(&fifo_absxsig,gx,&spinlock_absxsig);
	#endif
							
HAL_Delay(1000);
HAL_GPIO_WritePin(GPIOC,GPIO_PIN_8,0);	
}
