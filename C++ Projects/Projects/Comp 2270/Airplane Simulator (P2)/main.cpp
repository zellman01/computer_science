#include <iostream>
#include <cmath>
#include "Airport.h"
#include "Airplane.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int PoissonRandom(float);

int main(int argc, char** argv) {
	int expectedDepartures, expectedLandings;
	Airport a;
	Airplane b(4);
	a.landingPlane(b);
	return 0;
}

int PoissonRandom(float exValue) {
	// srand needs to be seeded already
	//Uses rank()
	float limit = 0.0F;
	int count = 0;
	float max = 0.0F;
	float product = 0.0F;
	max = 6 + 1.0; // 4 is a constant given the max value rand will return
	limit = exp(-exValue);
	product = rand()/max; // 0 <= product < 1
	while (product > limit) {
		count++;
		product *= rand()/max;
	}
	return count;
}
