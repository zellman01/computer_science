#include "Airport.h"
#include <iostream>
#include <iomanip>
#include <bits/stdc++.h>

using namespace std;

Airport::Airport() {
	landing = new Queue(6);
	planesTakenOff = 0;
	takeOff = new Queue(6);
	planesLanded = 0;
	planesCrashed = 0;
	planesRefused = 0;
	totalTimeWaitedLand = 0;
	totalTimeWaitedTakeoff = 0;
	idle = 0;
}

Airport::~Airport() {
	delete landing, takeOff;
}

bool Airport::planeNeedsLanded() { // Always check first
	return landing->getSize() >= 1;
}

bool Airport::planeReadyDepart() {
	return takeOff->getSize() >= 1;
}

void Airport::update() {
	int landSize = landing->getSize();
	for (int i = 0; i < landSize; i++) {
		Airplane a = landing->getNode(i);
		if (landing->updateNode(i, true)) {
			cout << setw(9) << "Plane " << a.planeNum() << " has crashed." << endl;
			planesCrashed++;
			i--;
			landSize--;
		}
	}
	int takeoffSize = takeOff->getSize();
	for (int i = 0; i < takeoffSize; i++) {
		takeOff->updateNode(i, false);
	}
}

bool Airport::landingPlane(Airplane & obj) {
	if (landing->insertNode(obj)) {
	   landing->sort();
	   cout << setw(9) << "Plane " << obj.planeNum() << " ready to land; " << obj.fuel() << " units of fuel remaining." << endl;
	   return true;
	} else {
	planesRefused++;
	cout << setw(9) << "Plane " << obj.planeNum() << " was refused to land" << endl;
	return false;
	}
}

bool Airport::landedPlane() {
	Airplane response;
	landing->deleteNode(response);
	cout << setw(5) << "Plane " << response.planeNum() << " landed; in queue " << response.waited() <<  "." << endl;
	totalTimeWaitedLand += response.waited();
	planesLanded++;
	return true;
}

void Airport::departingPlane(Airplane & obj) {
	if (takeOff->insertNode(obj)) {
	   cout << setw(9) << "Plane " << obj.planeNum() << " ready to take off" << endl;
	}
}

void Airport::departedPlane() {
	Airplane response;
	takeOff->deleteNode(response);
	cout << setw(5) << "Plane " << response.planeNum() << " took off; in queue " << response.waited() << "." << endl;
	totalTimeWaitedTakeoff += response.waited();
	planesTakenOff++;
}

void Airport::view(int timeUnits) {
	int totalPlanes = planesLanded+planesTakenOff+planesRefused+landing->getSize()+takeOff->getSize()+planesCrashed;
	const int spaces = 3;
	cout << "Simulation ended after " << timeUnits << " time units." << endl;
	cout << "Total number of planes processed:" << setw(spaces) << totalPlanes << endl;
	cout << "Number of planes landed:" << setw(spaces) << planesLanded << endl;
	cout << "Number of planes taken off:" << setw(spaces) << planesTakenOff << endl;
	cout << "Number of planes refused to land:" << setw(spaces) << planesRefused << endl;
	cout << "Number left ready to land:" << setw(spaces) << landing->getSize() << endl;
	cout << "Number left ready to depart:" << setw(spaces) << takeOff->getSize() << endl;
	cout << "Number crashed:" << setw(spaces) << planesCrashed << endl;
	cout << "Percentage of time runway was idle: " << setw(spaces) << (idle*1.0)/(timeUnits*1.0) << endl;
	cout << "Average wait time to land: " << setw(spaces);
	if (planesLanded == 0) {
		cout << "0" << endl;
	} else {
		cout << (totalTimeWaitedLand*1.0)/(planesLanded*1.0) << endl;
	}
	
	cout << "Average wait time to takeoff: " << setw(spaces);
	if (planesTakenOff == 0) {
		cout << "0" << endl;
	} else {
		cout << (totalTimeWaitedTakeoff*1.0)/(planesTakenOff*1.0) << endl;
	}
}

void Airport::isIdle() {
	idle++;
	cout << "Runway is idle." << endl;
}
