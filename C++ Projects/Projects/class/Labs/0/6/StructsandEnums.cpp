// Zachary Wellman

#include <iostream>
#include <string>

using namespace std;
enum Groups {SOLO, DUET, TRIO, QUARTER, QUINTET, SEXTET, SEPTET, NONET, DECTET};
struct Person {
	string fname, lname;
	int age;
};

int main() {
	// Question 1 part a
	Person person[3];
	int average = 0;
	for (int i = 0; i < 3; i++) {
		cout << "Enter the first name of person " << i+1 << ": ";
		cin >> person[i].fname;
		cout << "Enter the last name of person " << i+1 << ": ";
		cin >> person[i].lname;
		cout << "Enter the age of person " << i+1 << ": ";
		cin >> person[i].age;
		average += person[i].age;
	}
	cout << average/3 << endl << endl;
	// Question 1 part b
	average = 0;
	Person * person_ptr = new Person[3];
	for (int i = 0; i < 3; i++) {
		cout << "Enter the first name of person " << i+1 << ": ";
		cin >> person_ptr[i].fname;
		cout << "Enter the last name of person " << i+1 << ": ";
		cin >> person_ptr[i].lname;
		cout << "Enter the age of person " << i+1 << ": ";
		cin >> person_ptr[i].age;
		average += person_ptr[i].age;
	}
	cout << average/3 << endl;
	delete [] person_ptr;
	
	// Question 2
	for (int i = SOLO; i <= DECTET; i++) {
		cout << i+1;
		if (i<DECTET) cout << ", ";
		else cout << ".";
	}
	cout << endl;
	return 0;
}
