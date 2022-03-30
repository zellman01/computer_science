#include <iostream>
#include <fstream>
#include "Car.h"
using namespace std;

// Zachary Wellman
/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char** argv) {
	int smashiestCarIndex = 0, totalAmountCars = 5;
	// read file
	ifstream file("Cars.txt");
	if (!file) {
		cout << "Cannot find file.";
		return -1;
	}
	// create classes
	Car cars[totalAmountCars];
	for (int i = 0; i < totalAmountCars; i++) {
		string str;
		file >> str;
		cars[i].setName(str);
		float number;
		file >> number;
		cars[i].setMass(number);
		file >> number;
		cars[i].setVelocity(number);
		file.ignore();
		// find highest force car
		if (cars[smashiestCarIndex].getForce() < cars[i].getForce()) smashiestCarIndex = i;
	}
	
	// show on screen
	cout << "The smashiest car is " << cars[smashiestCarIndex].getName() << endl;
	
	return 0;
}
