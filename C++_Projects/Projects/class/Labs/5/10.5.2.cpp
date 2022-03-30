// Lab 10.5
// Zachary Wellman
#include <iostream>
#include <cstring>
using namespace std;

int main() {
	int size = 25;
	char * firstName = new char[size]; char * secondName = new char[size];
	
	cout << "Please input the first name" << endl;
	cin.getline(firstName, size);
	cout << endl;
	
	cout << "Please input the second name" << endl;
	cin.getline(secondName, size);
	cout << endl;
	
	cout << "The names are as follows:" << endl;
	if (strcmp(firstName, secondName) > 0) {
		cout << secondName << endl << firstName;
	} else if (strcmp(firstName, secondName) < 0) {
		cout << firstName << endl << secondName;
	} else {
		cout << firstName << endl << secondName << endl << "The names are the same";
	}
	cout << endl;
	delete [] firstName, secondName;
	return 0;
}
