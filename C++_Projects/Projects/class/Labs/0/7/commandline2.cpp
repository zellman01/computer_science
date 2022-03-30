// Zachary Wellman number 2
#include <iostream>
#include <string>
using namespace std;

int main(int argc, char** argv) {
	string day;
	switch(atoi(argv[argc-1])) {
		case 1:
			day = "Monday";
			break;
		case 2:
			day = "Tuesday";
			break;
		case 3:
			day = "Wednesday";
			break;
		case 4:
			day = "Thursday";
			break;
		case 5:
			day = "Firday";
			break;
		default: // Does not need a break, as it will terminate the program
			cout << "You may only use the numbers 1, 2, 3, 4 or 5." << endl;
			return -1;
	}
	cout << "The day is " << day << "." << endl;
	return 0;
}
