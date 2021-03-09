#include "Airplane.h"

Airplane::Airplane(int fuel, int num) {
	fuelLeft = fuel;
	crashed = false;
	timeWaiting = 0;
	airplaneNumber = num;
}

Airplane::Airplane() {
	
}

int Airplane::fuel() {
	return fuelLeft;
}

int Airplane::waited() {
	return timeWaiting;
}

void Airplane::update(bool landing) { // landing - If the plane is trying to land onto the runway
	if (!crashed && landing) {
		if (fuelLeft > 0) {
			fuelLeft--;
			timeWaiting++;
		} else {
			crashed = true;
		}
	} else {
		timeWaiting++;
	}
}

bool Airplane::isCrashed() { return crashed; }

int Airplane::planeNum() {
	return airplaneNumber;
}
