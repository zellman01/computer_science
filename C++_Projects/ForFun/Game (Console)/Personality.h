#include <string>
#include <vector>

#ifndef PERSONALITY_H
#define PERSONALITY_H

class Personality
{
	public:
		Personality(std::string);
		~Personality();
	private:
		std::string * name;
		std::vector<std::string> * responses;
};

#endif
