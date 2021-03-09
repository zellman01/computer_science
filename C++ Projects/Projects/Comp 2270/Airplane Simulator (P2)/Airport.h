#ifndef AIRPORT_H
#define AIRPORT_H

#include "Queue.h"

class Airport
{
	public:
		Airport();
		~Airport();
		bool landingPlane(Airplane&);
		void landedPlane(); // Will always assume that it is the top of the landing queue
		void departingPlane(Airplane&);
		void departedPlane();
		void view(int);
		void update();
		bool planeNeedsLanded();
		bool planeReadyDepart();
		void isIdle();
	private:
		Queue * landing, * takeOff; // Planes requesting to land, and to take off
		int planesTakenOff, planesLanded, planesCrashed, planesRefused, totalTimeWaitedLand, totalTimeWaitedTakeoff, idle;
};

#endif
