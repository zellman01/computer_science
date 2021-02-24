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
	landing->insertNode(obj, airplaneNumber);
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
	cout << "Simulation ended after " << timeUnits << " time units." << endl;
	cout << "Total number of planes processed:" << setw(50) << airplaneNumber-1 << endl;
	
}
