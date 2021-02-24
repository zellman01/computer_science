#include <iostream>
using namespace std;

int main() {
	/*char name[5];
	cout << "Enter your name: ";
	cin >> name;
	cout << "Your name is " << name << "." << endl;
	
	float assn1[] = {87.6, 98.7, 87, 56, 43};
	float assn2[] = {78.5, 78.9, 70, 90, 94.3};
	float assn3[] = {65.7, 45, 19, 100, 34};
	float classAvg = 0;
	int number = 0;
	for (int i = 0; i < 5; i++) {
		classAvg += assn1[i]+assn2[i]+assn3[i];
		number+=3;
		cout << "Student " << i+1 << " average is " << (assn1[i]+assn2[i]+assn3[i])/3 << endl;
	}
	cout << "Class average: " << classAvg/number << endl;*/
	
	float grades[3][5] = { {87.6, 98.7, 87, 56, 43}, {78.5, 78.9, 70, 90, 94.3}, {65.7, 45, 19, 100, 34} };
	// average of assignment 0
	float avg = 0;
	for (int i = 0; i < 5; i++) {
		avg += grades[0][i];
	}
	avg /= 5;
	cout << "Average of assignment 0 is " << avg;
}
