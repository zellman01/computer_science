// Name: Zachary Wellman
// File Name: falconex.cpp
// Date: 10 November, 2020
// Description: The class definitions of the FalconEx class

#include "falconex.h"
#include <string>
#include <iostream>
using namespace std;

FalconEx::FalconEx() {
	power = false;
	gameTitle = "";
	currentVolume = (MIN_VOLUME+MAX_VOLUME)/2;
}

void FalconEx::togglePower() {
	power = !power;
	string temp;
	if (power) temp = "ON";
	else temp = "OFF";
	cout << "System turned " << temp << "." << endl;
}

void FalconEx::loadGame(string game) {
	if (!power) {
		cout << "System not powered." << endl;
		return;
	}
	gameTitle = game;
	cout << gameTitle << " loaded." << endl;
}

void FalconEx::unloadGame() {
	if (gameTitle == "") {
		cout << "No game has been loaded." << endl;
		return;
	}
	cout << gameTitle << " unloaded." << endl;
	gameTitle = "";
}

void FalconEx::raiseVolume() {
	if (!power) {
		cout << "System not powered." << endl;
		return;
	}
	if (currentVolume == MAX_VOLUME) {
		cout << "Volume at maximum." << endl;
		return;
	}
	currentVolume++;
	cout << "Volume raised to " << currentVolume << "." << endl;
}

void FalconEx::lowerVolume() {
	if (!power) {
		cout << "System not powered." << endl;
		return;
	}
	if (currentVolume == MIN_VOLUME) {
		cout << "Volume at minimum." << endl;
		return;
	}
	currentVolume--;
	cout << "Volume lowered to " << currentVolume << "." << endl;
}

void FalconEx::play() {
	if (!power) {
		cout << "System not powered." << endl;
		return;
	}
	if (gameTitle == "") {
		cout << "No game has been loaded." << endl;
		return;
	}
	cout << gameTitle << " being played." << endl;
}
