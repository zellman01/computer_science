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
		void addTransition(std::string, std::string, std::string);
		void setInitialState(std::string);
		void addFinalState(std::string);
		void description();
		std::string getCurrentState();
		bool changeStates(std::string);
		bool isFinal();
		void reset();
	private:
		bool validSymbol(std::string);
		std::vector<std::string> states;
		std::vector<std::string> inputSymbols;
		std::vector<std::string> finalStates;
		std::multimap<std::pair<std::string, std::string>, std::string> transitions;
		std::string currentState, initialState;
};

#endif
