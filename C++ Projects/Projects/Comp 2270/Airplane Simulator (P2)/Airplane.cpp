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

void Airplane::update() {
	if (!crashed) {
		if (fuelLeft > 0) {
			fuelLeft--;
			timeWaiting++;
		} else {
			crashed = true;
		}
	}
}

bool Airplane::isCrashed() { return crashed; }
