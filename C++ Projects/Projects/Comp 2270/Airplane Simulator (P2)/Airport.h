#ifndef AIRPORT_H
#define AIRPORT_H

#include "Queue.h"

class Airport
{
	public:
		Airport();
		~Airport();
		void landingPlane(Airplane&);
		void landedPlane(); // Will always assume that it is the top of the landing queue
		void departingPlane(Airplane&);
	private:
		Queue * landing; // Planes requesting to land
		Queue * departing; // Planes requesting to take off
		int airplaneNumber;
};

#endif
