#include "Airport.h"
#include <iostream>
#include <iomanip>

using namespace std;

Airport::Airport() {
	landing = new Queue(6);
	departing = new Queue(6);
	airplaneNumber = 1;
	planesTakenOff = 0;
	planesLanded = 0;
	planesCrashed = 0;
	planesRefused = 0;
}

Airport::~Airport() {
	delete landing, departing;
}

void Airport::update() {
	int landSize = landing->getSize();
	for (int i = 0; i < landSize; i++) {
		landing->updateNode(i); // Need to check if it crashed, and update the variable accordingly
	}
}

void Airport::landingPlane(Airplane & obj) {
	landing->insertNode(obj, airplaneNumber); // Turn into a boolean, and if false, update the planes refused
	airplaneNumber++;
	landing->sort();
}

void Airport::landedPlane() {
	landing->deleteNode();
	planesLanded++;
}

void Airport::departingPlane(Airplane & obj) {
	takeOff->insertNode(obj, airplaneNumber);
	airplaneNumber++;
}

void Airport::departedPlane() {
	takeOff->deleteNode();
	planesTakenOff++;
}

void Airport::view(int timeUnits) {
	int spaces = 50;
	cout << "Simulation ended after " << timeUnits << " time units." << endl;
	cout << "Total number of planes processed:" << setw(spaces) << airplaneNumber-1 << endl;
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
