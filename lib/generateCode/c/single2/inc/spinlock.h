#ifndef SPINLOCK_H_
#define SPINLOCK_H_
#include "config.h"


	#define ARM
	typedef struct{
	volatile	int flag;
	}spinlock;
	
	void spinlock_get(spinlock* lock);
	void spinlock_release(spinlock* lock);


#endif
