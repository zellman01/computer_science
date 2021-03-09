#include "Airport.h"
#include <iostream>
#include <iomanip>

using namespace std;

Airport::Airport() {
	landing = new Queue(6);
	planesTakenOff = 0;
	takeOff = new Queue(6);
	planesLanded = 0;
	planesCrashed = 0;
	planesRefused = 0;
	totalTimeWaitedLand = 0;
	totalTimeWaitedTakeoff = 0;
	idle = 0;
}

Airport::~Airport() {
	delete landing, takeOff;
}

bool Airport::planeNeedsLanded() { // Always check first
	return landing->getSize() >= 1;
}

bool Airport::planeReadyDepart() {
	return takeOff->getSize() >= 1;
}

void Airport::update() {
	int landSize = landing->getSize();
	//bool planeCrash = false;
	for (int i = 0; i < landSize; i++) {
		if (landing->updateNode(i, true)) { // Plane has crashed
			cout << setw(9) << "Plane " << landing->getNode(i).planeNum() << " has crashed." << endl;
			planesCrashed++;
			//planeCrash = true;
			landing->deleteNode(i);
			i--;
			landSize--;
		}
	}
	/*while (planeCrash) { // Hopefully logically deletes a plane that has crashed
		planeCrash = false;
		for (int i = 0; i < landSize; i++) {
			if (landing->getNode(i).isCrashed()) {
				Airplane * test = new Airplane(0,0);
				landing->deleteNode(*test);
				delete test;
				landSize--;
				planeCrash = true;
			}
		}
	}*/
	int takeoffSize = takeOff->getSize();
	for (int i = 0; i < takeoffSize; i++) {
		takeOff->updateNode(i, false);
	}
}

bool Airport::landingPlane(Airplane & obj) {
	if (landing->insertNode(obj)) { // Turn into a boolean, and if false, update the planes refused
	   landing->sort();
	   cout << setw(9) << "Plane " << obj.planeNum() << " ready to land; " << obj.fuel() << " units of fuel remaining." << endl;
	   return true;
	} else {
	planesRefused++;
	return false;
	}
}

void Airport::landedPlane() {
	Airplane response;
	landing->deleteNode(response);
	cout << setw(5) << "Plane " << response.planeNum() << " landed; in queue " << response.waited() <<  "." << endl; // Add int to calculate how long it was in queue for
	totalTimeWaitedLand += response.waited();
	planesLanded++;
}

void Airport::departingPlane(Airplane & obj) {
	takeOff->insertNode(obj);
	cout << setw(9) << "Plane " << obj.planeNum() << " ready to take off" << endl;
}

void Airport::departedPlane() {
	Airplane response;
	takeOff->deleteNode(response);
	cout << setw(5) << "Plane " << response.planeNum() << " took off; in queue " << response.waited() << "." << endl; // Add int to calculate how long it was in queue for
	totalTimeWaitedTakeoff += response.waited();
	planesTakenOff++;
}

void Airport::view(int timeUnits) {
	const int spaces = 3;
	cout << "Simulation ended after " << timeUnits << " time units." << endl;
	cout << "Total number of planes processed:" << setw(spaces) << planesLanded+planesTakenOff+planesRefused << endl;
	cout << "Number of planes landed:" << setw(spaces) << planesLanded << endl;
	cout << "Number of planes taken off:" << setw(spaces) << planesTakenOff << endl;
	cout << "Number of planes refused to land:" << setw(spaces) << planesRefused << endl;
	cout << "Number left ready to land:" << setw(spaces) << landing->getSize() << endl;
	cout << "Number left ready to depart:" << setw(spaces) << takeOff->getSize() << endl;
	cout << "Number crashed:" << setw(spaces) << planesCrashed << endl;
	cout << "Percentage of time runway was idle: " << setw(spaces) << (idle*1.0)/(timeUnits*1.0) << endl; // Create a new var, and then proceed to add one every time the runway is doing
	//nothing, then divide by the timeUnits
	cout << "Average wait time to land:" << setw(spaces) << totalTimeWaitedLand/planesLanded << endl; // Figure out
	cout << "Average wait time to takeoff:" << setw(spaces) << totalTimeWaitedTakeoff/planesTakenOff << endl; // Figure out
}

void Airport::isIdle() {
	idle++;
	cout << "Runway is idle." << endl;
}
