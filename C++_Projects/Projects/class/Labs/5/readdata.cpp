// Lab 10.3
// Zachary Wellman
#include <iostream>
#include <cctype>
using namespace std;

int main() {
	char * last = new char[10];
	cout << "Enter your last name using no more than 9 characters: ";
	cin.getline(last, 9);
	cout << last;
	delete [] last;
	return 0;
}
