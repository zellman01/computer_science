// Zachary Wellman
// Option 3

#include <iostream>
#include <regex>
using namespace std;

void printFinal(int[]);
void tally(int[]);
void addGrade(int[], char);

const int SIZE = 5;

int main() {
	int grades[SIZE] = {0}; // A, B, C, D, F
	tally(grades);
	printFinal(grades);
}

void tally(int array[]) {
	int gradeTotal;
	regex check("[A-DF]");
	char letter[2];
	do {
	   cout << "Please input a number of grades to be read in. (1-50): ";
	   cin >> gradeTotal;
	} while (gradeTotal < 1 || gradeTotal > 50);
	cout << endl << "All grades mut be upper case A B C D or F" << endl;
	for (int i = 0; i < gradeTotal; i++) {
		cout << "Input a grade" << endl;
		cin >> letter[0];
		while (!regex_search(letter,check)) {
			cout << "Input a valid grade" << endl;
			cin >> letter;
		}
		addGrade(array, letter[0]);
	}
}
	
void addGrade(int array[], char letter) {
	switch(letter) {
		case 'A':
			array[0]++;
			break;
		case 'B':
			 array[1]++;
			 break;
		case 'C':
			array[2]++;
			break;
		case 'D':
			array[3]++;
			break;
		case 'F':
			array[4]++;
			break;
	}
}
	
void printFinal(int array[]) {
	cout << endl << endl << "Number of A=" << array[0];
	cout << endl << "Number of B=" << array[1];
	cout << endl << "Number of C=" << array[2];
	cout << endl << "Number of D=" << array[3];
	cout << endl << "Number of F=" << array[4] << endl;
}
