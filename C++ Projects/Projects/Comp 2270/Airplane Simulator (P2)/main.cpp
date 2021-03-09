#include <iostream>
#include <cmath>
#include <time.h>
#include <iomanip>
#include "Airport.h"
#include "Airplane.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int poissonRandom(float);

int main(int argc, char** argv) {
	srand((unsigned)time(NULL));
	const float expectedDepartures = 0.45, expectedLandings = 2;
	Airport a;
	cout << "How long would you like to run the simulation?" << endl;
	int timeUnit = 0, airplaneNumber = 1;
	cin >> timeUnit;
	for (int i = 0; i < timeUnit; i++) {
		// Generate planes to depart
		int takeoffPlanes = poissonRandom(expectedDepartures);
		int landingPlanes = poissonRandom(expectedLandings);
		for (int j = 0; j < landingPlanes; j++) {
			Airplane * b = new Airplane(rand()%2+1, airplaneNumber);
			airplaneNumber++;
			a.landingPlane(*b);
			delete b;
		}
		for (int k = 0; k < takeoffPlanes; k++) {
			Airplane * b = new Airplane(rand()%2+1, airplaneNumber);
			airplaneNumber++;
			a.departingPlane(*b);
			delete b;
		}
		cout << i+1 << ": ";
		if (a.planeNeedsLanded()) {
			a.landedPlane();
		} else if (a.planeReadyDepart()) {
			a.departedPlane();
		} else {
			a.isIdle();
		}
		a.update();
		// Do loop
	}
	a.view(timeUnit);
	return 0;
}

int poissonRandom(float exValue) {
	// srand needs to be seeded already
	//Uses rand()
	float limit = 0.0F;
	int count = 0;
	float max = 0.0F;
	float product = 0.0F;
	//max = 6 + 1.0F; // 6 is a constant given that the max value rand will return
	limit = exp(-exValue);
	product = (rand()%100)/100.0; // 0 <= product < 1
	while (product > limit) {
		count++;
		product = product *  (rand()%100)/100.0;
	}
	return count;
}
