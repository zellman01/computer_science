// Name: Zachary Wellman
// File Name: program2.cpp
// Date: 4 April, 2021
// Description: NPDA class

#ifndef NPDA_H
#define NPDA_H

#include <string>
#include <vector>
#include <map>
#include <iterator>

class npda {
	public:
		npda();
		void addState(std::string);
		void addSymbol(std::string);
		void addStackSymbol(std::string);
		void addTransition(std::string, std::string, std::string, std::string, std::string);
		void setInitialState(std::string);
		void addFinalState(std::string);
		void stackStart(std::string);
		void description();
		std::string getCurrentState() { return currentState; }
		bool transition(std::string, std::vector<std::string>, bool);
		bool isFinal();
		void reset();
		std::string getOutputString();
	private:
		void debug(std::string state, std::string symbols, std::vector<std::string> stack);
		bool validSymbol(std::string);
		std::vector<std::string> states;
		std::vector<std::string> inputSymbols;
		std::vector<std::string> stackSymbols;
		std::vector<std::string> finalStates;
		std::vector<std::string> outputString;
		std::multimap<std::tuple<std::string, std::string, std::string>, std::pair<std::string, std::string>> transitions; // Need to change to a triple and a pair
		std::string currentState, initialState, startingStack;
};

#endif
