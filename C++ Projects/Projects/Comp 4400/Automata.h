// Name: Zachary Wellman
// File Name: Automata.h
// Date: 24 February, 2021
// Description: Header file for the Automata objects

#ifndef AUTOMATA_H
#define AUTOMATA_H

#include <vector>
#include <string>
#include <map>

class Automata
{
	public:
		Automata();
		void addState(std::string);
		void addSymbol(std::string);
		void addTransition(std::string, std::string);
		void setInitialState(std::string);
		void addFinalState(std::string);
		void description();
		std::string getCurrentState();
		bool changeStates(std::string);
		bool isFinal();
	private:
		bool validSymbol(std::string);
		std::vector<std::string> states; // https://www.geeksforgeeks.org/vector-in-cpp-stl/
		std::vector<std::string> inputSymbols;
		std::vector<std::string> finalStates;
		std::map<std::string, std::string> transitions; // https://www.geeksforgeeks.org/map-associative-containers-the-c-standard-template-library-stl/
		std::string currentState;
};

#endif
