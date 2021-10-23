#include "Personality.h"
#include <string>
#include <vector>

using namespace std;

Personality::Personality(string pName) {
	name = new string(pName);
	responses = new vector<string>;
}

void Personality::addResponse(string resp) {
	responses->insert(responses->end(), resp);
}

Personality::~Personality() {
	delete name;
	delete responses;
}
