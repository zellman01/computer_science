// Zachary Wellman
// Parallel Arrays assignment

#include <iostream>
using namespace std;

int main() {
	float assn1[5] = {67.5,87.9,90,98,0};
	float assn2[5] = {87.5,87.4,60,78,90};
	float assn3[5] = {97.5,83.9,40,88,80};
	int studID[] = {101,202,301,401,502};
	
	for (int i = 0; i < 5; i++) {
		if (studID[i]%2 == 0) {
			assn1[i] += 5;
			assn2[i] += 5;
			assn3[i] += 5;
		}
		cout << "Student ID: " << studID[i] << ", Assignment 1: " << assn1[i] << ", Assignment 2: " << assn2[i] << ", Assigneent 3: " << assn3[i] << "." << endl;
	}
	return 0;
}
