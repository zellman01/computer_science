#include "Car.h"

#include <string>
#include <cmath>
using namespace std;

string Car::getName() { return name; }

float Car::getMass() { return mass; }

float Car::getVelocity() { return velocity; }

float Car::getForce() { return (0.5*mass)*(pow(velocity, 2)); }

void Car::setMass(float tempMass) {
	mass = tempMass;
}

void Car::setVelocity(float tempVeloc) {
	velocity = tempVeloc;
}

void Car::setName(string tempName) {
	name = tempName;
}
