#include <string>
#include "Personality.h"

#ifndef CHARACTER_H
#define CHARACTER_H

class Character
{
	public:
		Character(std::string);
		~Character();
	private:
		std::string * name;
};

#endif
