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
		Queue * landing, takeOff; // Planes requesting to land, and to take off
		int airplaneNumber;
};

#endif
