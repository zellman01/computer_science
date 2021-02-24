// Lab 10.5
// Zachary Wellman
#include <iostream>
#include <cstring>
using namespace std;

bool panlin(const char[]);

int main() {
	int size = 50;
	char pana[size+1];
	cout << "Enter a string of characters that is " << size << " or less." << endl;
	cin >> pana;
	if (panlin(pana)) cout << "The string is a palindrome." << endl;
	else cout << "The string is not a palindrome." << endl;
	return 0;
}

bool panlin(const char test[]) {
	if (strlen(test) == 1) return true;
	
	char test2[strlen(test)-1];
	int i;
	for (i = 1; i < strlen(test); i++) {
		test2[i-1] = test[i];
	}
	i--;
	char t1 = test2[0]; char t2 = test2[i-2];
	return t1==t2;
}
