#include "Airport.h"

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
