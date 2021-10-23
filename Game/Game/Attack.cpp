#include "Attack.h"

using namespace std;

// Creates a new attack.
// Dam: The damage the attack does (can be negative for healing)
// hit: The change an attack will land/execute (must be positive; will default to 50 otherwise)
// crit: Chance an attack will be critical (can be negative to never allow any critical strikes)
Attack::Attack(int dam, int hit, int crit, string name) {
	damage = new int(dam);
	if (hit <= 0)
		hit = 50;
	hitChance = new int(hit);
	criticalChance = new int(crit);
	attackName = new string(name);
}

Attack::~Attack() {
	delete damage, hitChance, criticalChance, attackName;
}