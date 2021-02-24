// Zachary Wellman
#include <iostream>
#include <cstring>
#include <string>
using namespace std;

int main() {
	// Number 1
	int number;
	cout << "How long of a word do you want to enter? ";
	cin >> number;
	number++;
	char * array1 = new char[number];
	array1[number-1] = '\0';
	for (int i = 0; i < number-1; i++) {
		cout << "Enter the " << (i+1) << " character of the word: ";
		cin >> array1[i];
	}
	cout << array1 << endl;
	char * array2 = new char[strlen(array1)];
	strncpy(array2, array1, number);
	cout << array2 << endl;
	
	delete [] array1; delete [] array2;
	
	// Number 2
	string thing;
	cout << "Enter a string, followed by an *, followed by punctuation, followed by another *, followed by a number (ex., headslap*!!!!!!*12) ";
	cin >> thing;
	int location = thing.find("*");
	int location2 = thing.find("*", location+1);
	string punct = thing.substr(location+1, location2);
	punct = punct.substr(0, punct.find("*"));
	string repeat = thing.substr(location2, thing.length());
	string strRepeat = thing.substr(0, location);
	int repeatNum = stoi(thing.substr(location2+1));
	for (int i = 0; i < repeatNum; i++) {
		cout << strRepeat;
	}
	cout << punct << endl;
}
