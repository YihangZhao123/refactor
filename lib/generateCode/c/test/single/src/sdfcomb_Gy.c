/* Includes-------------------------- */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_Gy.h"



/*
========================================
	Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo_DoubleType fifo_gysig;
extern spinlock spinlock_gysig;
/* Output FIFO */
extern circular_fifo_DoubleType fifo_absysig;
extern spinlock spinlock_absysig;
/*
========================================
	Actor Function
========================================
*/			
void actor_Gy(){
	#if defined(TESTING)
	HAL_GPIO_WritePin(GPIOC,GPIO_PIN_6,1);
	#endif
	
	/* Initilize Memory      */
	DoubleType gy; 
	Array6OfDoubleType imgBlockY; 
	/* Read From Input Port  */
	for(int i=0;i<6;++i){
		#if GYSIG_BLOCKING==0
		read_non_blocking_DoubleType(&fifo_gysig,&imgBlockY[i]);
		#else
		read_blocking_DoubleType(&fifo_gysig,&imgBlockY[i],&spinlock_gysig);
		#endif
	}
	
	/* Inline Code           */
	/* in combFunction GyImpl */
	gy=0;
	gy=gy+imgBlockY[0];
	gy=gy+2.0*imgBlockY[1];
	gy=gy+imgBlockY[2];
	gy=gy-imgBlockY[3];
	gy=gy-2.0*imgBlockY[4];
	gy=gy-imgBlockY[5];

	/* Write To Output Ports */
	#if ABSYSIG_BLOCKING==0
	write_non_blocking_DoubleType(&fifo_absysig,gy);
	#else
	write_blocking_DoubleType(&fifo_absysig,gy,&spinlock_absysig);
	#endif
							
HAL_Delay(1000);
HAL_GPIO_WritePin(GPIOC,GPIO_PIN_6,0);		
}
