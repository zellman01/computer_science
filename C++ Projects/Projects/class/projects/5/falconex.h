// Name: Zachary Wellman
// File Name: falconex.h
// Date: 10 November, 2020
// Description: The header file of the class FalconEx
#include <string>

#ifndef FALCONEX_H
#define FALCONEX_H

class FalconEx {
	public:
		FalconEx();
		void togglePower();
		void loadGame(std::string);
		void unloadGame();
		void raiseVolume();
		void lowerVolume();
		void play();
	private:
		bool power;
		std::string gameTitle;
		const int MAX_VOLUME = 6, MIN_VOLUME = 0;
		int currentVolume;
};

#endif
