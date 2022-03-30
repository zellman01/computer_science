// Zachary Wellman
#include <iostream>
#include <cstring>
#include <string>
using namespace std;

char * test(char[]);
void test2(string);

int main() {
	char strEntry[50];
	char * test1 = new char[50];
	test1 = test(strEntry);
	cout << test1 << endl;
	
	string test;
	cout << "Enter a word, followed by *, then followed by the number: ";
	cin >> test;
	test2(test);
}

char * test(char strEntry[]) {
	cout << "Enter a word: ";
	cin >> strEntry;
	int a = strlen(strEntry);
	char * array = new char[a];
	for (int i = 0; i < a; i++) {
		array[i] = strEntry[i];
	}
	return array;
}

void test2(string thing) {
	string repeat = thing.substr(0, thing.find("*"));
	int repeatNum = stoi(thing.substr(thing.find("*")+1, thing.length()));
	for (int i = 0; i < repeatNum; i++) {
		cout << repeat << endl;
	}
}
