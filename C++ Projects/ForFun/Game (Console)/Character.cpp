#include "Character.h"

using namespace std;

Character::Character(string characterName) {
	name = new string(characterName);
}

Character::~Character() {
	delete name;
}
