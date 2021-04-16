// Name: Zachary Wellman
// File Name: npda.h
// Date: 4 April, 2021
// Description: npda class declarations

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
		bool transition(const std::string, std::vector<std::string>, bool);
		bool isFinal();
		void reset();
		void output();
	private:
		bool validSymbol(std::string);
		std::vector<std::string> states, inputSymbols, stackSymbols, finalStates, outputString;
		std::multimap<std::tuple<std::string, std::string, std::string>, std::pair<std::string, std::string>> transitions;
		std::string currentState, initialState, startingStack;
		void debug(std::string, std::string, std::vector<std::string>, bool);
};

#endif
