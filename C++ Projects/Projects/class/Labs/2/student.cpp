// This program will input an undetermined number of student names
// and a number of grades for each student. The number of grades is
// given by the user. The grades are stored in an array.
// Two functions are called for each student.
// One function will give the numeric average of their grades.
// The other function will give a letter grade to that average.
// Grades are assigned on a 10 point spread.
// 90-100 A	80-89 B	70-79 C	60-69 D	Below 60 F

// Zachary Wellman

#include <iostream>
#include <iomanip>
using namespace std;

const	int MAXGRADE = 25;	// maximum number of grades per student 
const	int MAXCHAR = 30;	// maximum characters used in a name

typedef char StringType30[MAXCHAR + 1];	// character array data type for names
										// having 30 characters or less.

typedef	float GradeType[MAXGRADE];		// one dimensional integer array data type

float findGradeAvg(GradeType, int);		// finds grade average by taking array of
										// grades and number of grades as parameters

char findLetterGrade(float);			// finds letter grade from average given
										// to it as a parameter 
int main()
{
	StringType30 firstname, lastname;	// two arrays of characters defined
	int numOfGrades;					// holds the number of grades
	GradeType grades;					// grades defined as a one dimensional array
	float average;						// holds the average of a student's grade
	char moreInput;						// determines if there is more input
	
	cout << setprecision(2) << fixed << showpoint;

	// Input the number of grades for each student
	cout << "Please input the number of grades each student will receive." << endl
		 << "This must be a number between 1 and " << MAXGRADE << " inclusive"
		 << endl;
	cin >> numOfGrades;

	while (numOfGrades > MAXGRADE || numOfGrades < 1)
	{
		cout << "Please input the number of grades for each student." << endl
		<< "This must be a number between 1 and " << MAXGRADE
		<< " inclusive\n";
		cin >> numOfGrades;
	}

	// Input names and grades for each student
	cout << "Please input a y if you want to input more students"
	     << " any other character will stop the input" << endl;
	cin >> moreInput;

	while (moreInput == 'y' || moreInput == 'Y')
	{
		cout << "Please input the first name of the student" << endl;
		cin >> firstname;

		cout << endl << "Please input the last name of the student" << endl;
		cin >> lastname;

		for (int count = 0; count < numOfGrades; count++)
		{
			cout << endl << "Please input a grade" << endl;

			// Fill in the input statement to place grade in the array
			int grade = 0;
			cin >> grade;
			grades[count] = grade;
		}

		cout << firstname << " " << lastname << " has an average of ";

		// Fill in code to get and print average of student to screen
		float avg = findGradeAvg(grades, numOfGrades);
		cout << avg << " which gives the letter grade of ";
		// Fill in call to get and print letter grade of student to screen
		cout << findLetterGrade(avg);
		cout << endl << endl << endl;

		cout << "Please input a y if you want to input more students"
		     << " any other character will stop the input" << endl;
		cin >> moreInput;
	}

	return 0;
}

//***********************************************************************
// findGradeAvg
//
// task:	      This function finds the average of the
//                numbers stored in an array.
//
// data in:	      an array of integer numbers
// data returned: the average of all numbers in the array
//
//***********************************************************************

float findGradeAvg(GradeType array, int numGrades)
{
	// Fill in the code for this function
	float sum;
	for (int i = 0; i < numGrades; i++) {
		sum += array[i];
	}
	return sum/numGrades;
}

//***********************************************************************
// findLetterGrade
//
// task:	      This function finds the letter grade for the number
//                passed to it by the calling function
//
// data in:	      a floating point number
// data returned: the grade (based on a 10 point spread) based on the 
//                number passed to the function
//
//***********************************************************************

char findLetterGrade(float numGrade)
{
	// Fill in the code for this function
	char grade = 'g';
	if (numGrade < 60) {
		grade = 'F';
	} else if (numGrade < 70) {
		grade = 'D';
	} else if (numGrade < 80) {
		grade = 'C';
	} else if (numGrade < 90) {
		grade = 'B';
	} else {
		grade = 'A';
	}
	return grade;
}
