#include <fstream>
#include <iostream>
using namespace std;

// Zachary Wellman

const int MAXNAME = 20;

int main()
{
	ifstream inData;
	inData.open("grades.txt");

	char name[MAXNAME + 1];	// holds student name 
	float average;			// holds student average

	while (inData)
	{
		inData.get(name, MAXNAME + 1);
		inData >> average;
		inData.ignore();

		// Fill in the code to print out name and
		// student average
		cout << name << "\t has a(n) " << average << " average" << endl;

		// Fill in the code to complete the while
		// loop so that the rest of the student
		// names and average are read in properly
	}

	return 0;
}
