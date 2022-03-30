#include "Personality.h"

using namespace std;

Personality::Personality(string personalityName) {
	name = new string(personalityName);
	responses = new vector<string>;
}

Personality::~Personality() {
	delete name, responses;
}
