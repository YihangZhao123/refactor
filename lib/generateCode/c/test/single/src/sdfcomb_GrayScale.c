/* Includes-------------------------- */
#include "../inc/config.h"
#include "../inc/datatype_definition.h"
#include "../inc/circular_fifo_lib.h"
#include "../inc/sdfcomb_GrayScale.h"



/*
========================================
	Declare Extern Channal Variables
========================================
*/
/* Input FIFO */
extern circular_fifo_UInt16 fifo_GrayScaleX;
extern spinlock spinlock_GrayScaleX;
extern circular_fifo_UInt16 fifo_GrayScaleY;
extern spinlock spinlock_GrayScaleY;
/* Output FIFO */
extern circular_fifo_UInt16 fifo_GrayScaleToAbs;
extern spinlock spinlock_GrayScaleToAbs;
extern circular_fifo_DoubleType fifo_GrayScaleToGetPx;
extern spinlock spinlock_GrayScaleToGetPx;
/*
========================================
	Actor Function
========================================
*/			
void actor_GrayScale(){
	#if defined(TESTING)
	HAL_GPIO_WritePin(GPIOA,GPIO_PIN_5,1);
	#endif
	
	/* Initilize Memory      */
	UInt16 offsetX; 
	Array2OfUInt16 dimsOut; 
	UInt16 offsetY; 
	Array6OfDoubleType gray; 
	ArrayXOfArrayXOfDoubleType system_img_source_address = system_img_source_global; 
	UInt16 dimY = dimY_global; 
	UInt16 dimX = dimX_global; 
	/* Read From Input Port  */
	#if GRAYSCALEX_BLOCKING==0
	read_non_blocking_UInt16(&fifo_GrayScaleX,&offsetX);
	#else
	read_blocking_UInt16(&fifo_GrayScaleX,&offsetX,&spinlock_GrayScaleX);
	#endif
	
	#if GRAYSCALEY_BLOCKING==0
	read_non_blocking_UInt16(&fifo_GrayScaleY,&offsetY);
	#else
	read_blocking_UInt16(&fifo_GrayScaleY,&offsetY,&spinlock_GrayScaleY);
	#endif
	
	/* Inline Code           */
	/* in combFunction GrayScaleImpl */
	gray[0]=0.3125*system_img_source_address[offsetY+0][offsetX+0]+0.5625*system_img_source_address[offsetY+0][offsetX+1]+0.125*system_img_source_address[offsetY+0][offsetX+2];
	gray[1]=0.3125*system_img_source_address[offsetY+0][offsetX+2]+0.5625*system_img_source_address[offsetY+0][offsetX+3]+0.125*system_img_source_address[offsetY+0][offsetX+4];
	gray[2]=0.3125*system_img_source_address[offsetY+1][offsetX+0]+0.5625*system_img_source_address[offsetY+1][offsetX+1]+0.125*system_img_source_address[offsetY+1][offsetX+2];
	gray[3]=0.3125*system_img_source_address[offsetY+1][offsetX+2]+0.5625*system_img_source_address[offsetY+1][offsetX+3]+0.125*system_img_source_address[offsetY+1][offsetX+4];
	gray[4]=0.3125*system_img_source_address[offsetY+2][offsetX+0]+0.5625*system_img_source_address[offsetY+2][offsetX+1]+0.125*system_img_source_address[offsetY+2][offsetX+2];
	gray[5]=0.3125*system_img_source_address[offsetY+2][offsetX+2]+0.5625*system_img_source_address[offsetY+2][offsetX+3]+0.125*system_img_source_address[offsetY+2][offsetX+4];
	if(offsetX>=dimX-2){
	offsetY+=1;
	offsetX=0;
	}
	if(offsetY>=dimY-2){
	offsetY=0;
	}
	dimsOut[0]=dimX;
	dimsOut[1]=dimY;

	/* Write To Output Ports */
	for(int i=0;i<6;++i){
		#if GRAYSCALETOGETPX_BLOCKING==0
		write_non_blocking_DoubleType(&fifo_GrayScaleToGetPx,gray[i]);
		#else
		write_blocking_DoubleType(&fifo_GrayScaleToGetPx,gray[i],&spinlock_GrayScaleToGetPx);
		#endif
	}
	
	#if GRAYSCALEX_BLOCKING==0
	write_non_blocking_UInt16(&fifo_GrayScaleX,offsetX);
	#else
	write_blocking_UInt16(&fifo_GrayScaleX,offsetX,&spinlock_GrayScaleX);
	#endif
							
	#if GRAYSCALEY_BLOCKING==0
	write_non_blocking_UInt16(&fifo_GrayScaleY,offsetY);
	#else
	write_blocking_UInt16(&fifo_GrayScaleY,offsetY,&spinlock_GrayScaleY);
	#endif
							
	for(int i=0;i<2;++i){
		#if GRAYSCALETOABS_BLOCKING==0
		write_non_blocking_UInt16(&fifo_GrayScaleToAbs,dimsOut[i]);
		#else
		write_blocking_UInt16(&fifo_GrayScaleToAbs,dimsOut[i],&spinlock_GrayScaleToAbs);
		#endif
	}
	
HAL_Delay(1000);
HAL_GPIO_WritePin(GPIOA,GPIO_PIN_5,0);
}
