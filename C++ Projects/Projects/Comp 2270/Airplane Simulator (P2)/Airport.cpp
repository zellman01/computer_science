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
	for (int i = 0; i < landSize; i++) {
		landing->updateNode(i); // Need to check if it crashed, and update the variable accordingly
	}
}

bool Airport::landingPlane(Airplane & obj) {
	if (landing->insertNode(obj)) { // Turn into a boolean, and if false, update the planes refused
	   landing->sort();
	   return true;
	} else {
	planesRefused++;
	return false;
	}
}

void Airport::landedPlane() {
	landing->deleteNode();
	planesLanded++;
}

void Airport::departingPlane(Airplane & obj) {
	takeOff->insertNode(obj);
}

void Airport::departedPlane() {
	takeOff->deleteNode();
	planesTakenOff++;
}

void Airport::view(int timeUnits) {
	const int spaces = 50;
	cout << "Simulation ended after " << timeUnits << " time units." << endl;
	cout << "Total number of planes processed:" << setw(spaces) << -1 << endl;
	cout << "Number of planes landed:" << setw(spaces) << planesLanded << endl;
	cout << "Number of planes taken off:" << setw(spaces) << planesTakenOff << endl;
	cout << "Number of planes refused to land:" << setw(spaces) << planesRefused << endl;
	cout << "Number left ready to land:" << setw(spaces) << landing->getSize() << endl;
	cout << "Number left ready to depart:" << setw(spaces) << takeOff->getSize() << endl;
	cout << "Number crashed:" << setw(spaces) << planesCrashed << endl;
	cout << "Percentage of time runway was idle:" << setw(spaces) << endl; // Create a new var, and then proceed to add one every time the runway is doing 
	//nothing, then divide by the timeUnits
	cout << "Average wait time to land:" << setw(spaces) << endl; // Figure out
	cout << "Average wait time to takeoff:" << setw(spaces) << endl; // Figure out
}
