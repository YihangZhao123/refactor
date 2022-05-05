/* Includes-------------------------- */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_getPx.h"



/*
========================================
	Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo_DoubleType fifo_GrayScaleToGetPx;
extern spinlock spinlock_GrayScaleToGetPx;
/* Output FIFO */
extern circular_fifo_DoubleType fifo_gysig;
extern spinlock spinlock_gysig;
extern circular_fifo_DoubleType fifo_gxsig;
extern spinlock spinlock_gxsig;
/*
========================================
	Actor Function
========================================
*/			
void actor_getPx(){
	#if defined(TESTING)
	HAL_GPIO_WritePin(GPIOC,GPIO_PIN_9,1);
	#endif
	
	/* Initilize Memory      */
	Array6OfDoubleType gray; 
	Array6OfDoubleType imgBlockY; 
	Array6OfDoubleType imgBlockX; 
	/* Read From Input Port  */
	for(int i=0;i<6;++i){
		#if GRAYSCALETOGETPX_BLOCKING==0
		read_non_blocking_DoubleType(&fifo_GrayScaleToGetPx,&gray[i]);
		#else
		read_blocking_DoubleType(&fifo_GrayScaleToGetPx,&gray[i],&spinlock_GrayScaleToGetPx);
		#endif
	}
	
	/* Inline Code           */
	/* in combFunction getPxImpl1 */
	imgBlockX[0]=gray[0];
	imgBlockX[1]=gray[1];
	imgBlockX[2]=gray[2];
	imgBlockX[3]=gray[3];
	imgBlockX[4]=gray[4];
	imgBlockX[5]=gray[5];
	/* in combFunction getPxImpl2 */
	imgBlockY[0]=gray[0];
	imgBlockY[1]=gray[1];
	imgBlockY[2]=gray[2];
	imgBlockY[3]=gray[3];
	imgBlockY[4]=gray[4];
	imgBlockY[5]=gray[5];

	/* Write To Output Ports */
	for(int i=0;i<6;++i){
		#if GYSIG_BLOCKING==0
		write_non_blocking_DoubleType(&fifo_gysig,imgBlockY[i]);
		#else
		write_blocking_DoubleType(&fifo_gysig,imgBlockY[i],&spinlock_gysig);
		#endif
	}
	
	for(int i=0;i<6;++i){
		#if GXSIG_BLOCKING==0
		write_non_blocking_DoubleType(&fifo_gxsig,imgBlockX[i]);
		#else
		write_blocking_DoubleType(&fifo_gxsig,imgBlockX[i],&spinlock_gxsig);
		#endif
	}
	
HAL_Delay(1000);
HAL_GPIO_WritePin(GPIOC,GPIO_PIN_9,0);					
}
