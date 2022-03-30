//
// circles.cpp
// This program calculates and displays the area and circumference of a
//  circle having a given radius
//
// Programmer: Zachary Wellman
// Date of last modification: 2/12/2020

#include <iostream>
#include <iomanip>

using namespace std;

const double PI = 3.14159265359;

double CalcArea(double r);

double CalcCircumference(double r);

int main() {
    double radius, area, circumference;

    cout << endl << endl << endl;

    cout << "Please enter the radius of the circle: ";
    cin >> radius;

    area = CalcArea(radius);
    circumference = CalcCircumference(radius);

    cout << showpoint << fixed << setprecision(2);
    cout << "Area of circle is: " << area << endl;
    cout << "Circumference of circle is: " << circumference << endl;

    cout << endl << endl;

    return 0;
}

// function to calculate the area of a circle
// IN: the radius of the circle
// OUT: (via return stmt) the area of the circle
double CalcArea(double r) { return PI * r * r; } // end CalcArea()

// function to calculate the circumference of a circle
// IN: the radius of the circle
// OUT: (via return stmt) the circumference of the circle
double CalcCircumference(double r) { return 2 * PI * r; } // end CalcCircumference()
