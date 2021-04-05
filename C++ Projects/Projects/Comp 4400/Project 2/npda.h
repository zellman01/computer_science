#ifndef NPDA_H
#define NPDA_H

#include <string>
#include <vector>
#include <map>

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
		std::string getCurrentState();
		bool changeStates(std::string);
		bool isFinal();
		void reset();
	private:
		bool validSymbol(std::string);
		void stackPush(std::string);
		std::string stackPop();
		std::vector<std::string> states;
		std::vector<std::string> inputSymbols;
		std::vector<std::string> stackSymbols;
		std::vector<std::string> finalStates;
		std::vector<std::string> stack;
		std::multimap<std::tuple<std::string, std::string, std::string>, std::pair<std::string, std::string>> transitions; // Need to change to a triple and a pair
		std::string currentState, initialState, startingStack;
};

#endif
