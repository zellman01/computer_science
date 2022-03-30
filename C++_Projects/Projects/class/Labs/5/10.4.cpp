// Lab 10.4

// Zachary Wellman

#include <iostream>
#include <cstring>
using namespace std;

int main() {
	char string1[25] = "Total eclipse ";
	char string2[11] = "of the Sun";
	cout << string1 << endl;
	cout << string2 << endl;
	strcat(string1, string2);
	cout << string1 << endl;
}
