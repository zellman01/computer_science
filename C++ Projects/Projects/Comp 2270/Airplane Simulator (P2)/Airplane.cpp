#include "Airplane.h"

Airplane::Airplane(int fuel) {
	fuelLeft = fuel;
	crashed = false;
}

Airplane::Airplane() {
	
}

void Airplane::update() {
	if (!crashed) {
		if (fuelLeft > 0) {
			fuelLeft--;
		} else {
			crashed = true;
		}
	}
}
