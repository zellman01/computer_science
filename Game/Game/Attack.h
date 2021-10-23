#pragma once
#include <string>

class Attack {
	public:
		Attack(int, int, int, std::string);
		~Attack();
	private:
		int * damage, * hitChance, * criticalChance;
		std::string * attackName;
};

