#include <iostream>
#include <iomanip>
using namespace std;

// This program uses a structure to hold data about a rectangle

// Zachary Wellman

// Fill in code to declare a structure named rectangle which has
// members length, width, area, and perimeter all of which are floats
struct rectangle {
	float length, width, area, perimeter;
};

int main()
{
	float input;
	// Fill in code to define a rectangle variable named box
	rectangle box;

	cout << "Enter the length of a rectangle: ";

	// Fill in code to read in the length member of box
	cin >> input;
	box.length = input;
	
	cout << "Enter the width of a rectangle: ";

	// Fill in code to read in the width member of box
	cin >> input;
	box.width = input;

	cout << endl << endl;

	// Fill in code to compute the area member of box
	box.area = box.length*box.width;

	// Fill in code to compute the perimeter member of box
	box.perimeter = box.length+box.width;

	cout << fixed << showpoint << setprecision(2);

	// Fill in code to output the area with an appropriate message
	cout << "The area of the rectangle is " << box.area << endl;

	// Fill in code to output the perimeter with an appropriate message
	cout << "The perimeter of the rectangle is " << box.perimeter;
	
	if (box.length == box.width) {
		cout << endl << "The rectangle is a square.";
	}

	return 0;
}