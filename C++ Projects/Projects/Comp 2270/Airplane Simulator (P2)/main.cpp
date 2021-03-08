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
	const float expectedDepartures = 0.02, expectedLandings = 0.25;
	Airport a;
	cout << "How long would you like to run the simulation?" << endl;
	int timeUnit = 0, airplaneNumber = 1;
	cin >> timeUnit;
	srand((unsigned)time(NULL));
	for (int i = 0; i < timeUnit; i++) {
		// Generate planes to depart
		int takeoffPlanes = poissonRandom(expectedDepartures);
		int landingPlanes = poissonRandom(expectedLandings);
		for (int i = 0; i < takeoffPlanes; i++) {
			Airplane b(rand()%8+1, airplaneNumber);
			a.landingPlane(b);
		}
		//for (int i = 0; i < landingPlanes; i++) {
		//	Airplane b(rand()%8+1, airplaneNumber);
		//	a.departingPlane(b);
		//}
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
	max = 6 + 1.0; // 6 is a constant given that the max value rand will return
	limit = exp(-exValue);
	product = max/rand(); // 0 <= product < 1
	while (product > limit) {
		count++;
		product = product *  (max/rand());
	}
	return count;
}
