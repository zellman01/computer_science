#include <iostream>
using namespace std;
const double PI = 3.14159;

int main() {
    double r, area, circ;

    cout << "Enter the radius: ";
    cin >> r;

    if (r <= 0) {
        cout << "Error in processing - radius cannot be 0 or negative.";
        return -8;
    }

    area = PI * (r*r);
    circ = 2 * PI * r;

    cout << "Area: " << area << endl;
    cout << "Circumference: " << circ << endl;

    return main();
}
