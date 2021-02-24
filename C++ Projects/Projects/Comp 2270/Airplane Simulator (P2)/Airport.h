#ifndef AIRPORT_H
#define AIRPORT_H

#include "Queue.h"

class Airport
{
	public:
		Airport();
	private:
		Queue * landing;
		Queue * departing;
};

#endif
