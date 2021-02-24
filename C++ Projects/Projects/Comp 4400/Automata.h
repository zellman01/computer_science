#ifndef AUTOMATA_H
#define AUTOMATA_H

#include <vector>
#include <string>
#include <map>

class Automata
{
	public:
		Automata();
		void addState();
		void addSymbol();
		void addTransition();
		void setInitialState();
		void addFinalState();
		void description();
		void validSymbol();
		void currectState(); // Return value must be the current state
		void changeStates();
		void isFinal(); // Return value must indicate if it is a final state or not
	private:
		std::vector<std::string> states; // https://www.geeksforgeeks.org/vector-in-cpp-stl/
		std::vector<std::string> inputSymbols;
		std::vector<std::string> finalStates;
		std::map<std::string, std::string> transitions; // https://www.geeksforgeeks.org/map-associative-containers-the-c-standard-template-library-stl/
		std::string currentState;
};

#endif
