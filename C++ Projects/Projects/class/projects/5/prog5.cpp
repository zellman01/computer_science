// Name: Zachary Wellman
// File Name: prog5.cpp
// Date: 10 November, 2020
// Description: Driver code for the class

#include <iostream>
#include "falconex.h"
using namespace std;

int main(int argc, char** argv) {
	FalconEx * test = new FalconEx;
	test->loadGame("Pokemon");
	test->raiseVolume();
	test->lowerVolume();
	test->play();
	test->togglePower();
	test->play();
	test->unloadGame();
	test->loadGame("Pokemon");
	test->play();

	for (int i = 0; i < 4; i++) test->raiseVolume();
	for (int i = 0; i < 7; i++) test->lowerVolume();
	
	test->unloadGame();
	test->loadGame("DragonMaze");
	test->play();
	test->togglePower();
	
	delete test;

	return 0;
}
