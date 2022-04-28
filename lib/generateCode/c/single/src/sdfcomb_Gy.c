#include "../inc/datatype_definition.h"
inline void actor_Gy(){
	//initilize the memory
	  gy;
	  imgBlockY;
	
	//read from the input port
	for(int i=0;i<6;++i){
		read_nonblocking(gy_channel);
	}
	//inline code
	//in combFunction GyImpl
	gy=gy+imgBlockY[0];
	gy=gy+2.0*imgBlockY[1];
	gy=gy+imgBlockY[2];
	gy=gy-imgBlockY[3];
	gy=gy-2.0*imgBlockY[4];
	gy=gy-imgBlockY[5];

	//write to the output port
	write(resy_channel);
}
