#include "Airport.h"
#include <iostream>
#include <iomanip>

using namespace std;

Airport::Airport() {
	landing = new Queue(6);
	departing = new Queue(6);
	airplaneNumber = 1;
}

Airport::~Airport() {
	delete landing, departing;
}

void Airport::landingPlane(Airplane & obj) {
	landing->insertNode(obj, airplaneNumber);
	airplaneNumber++;
	landing->sort();
}

void Airport::landedPlane() {
	landing->deleteNode();
}

void Airport::departingPlane(Airplane & obj) {
	takeOff->insertNode(obj, airplaneNumber);
	airplaneNumber++;
}

void Airport::departedPlane() {
	takeOff->deleteNode();
}

void Airport::view(int timeUnits) {
	cout << "Simulation ended after " << timeUnits << " time units." << endl;
	cout << "Total number of planes processed:" << setw(50) << airplaneNumber-1 << endl;
	
}
