#include <iostream>
using namespace std;
// Zachary Wellman

class Rectangle {
	private:
		int length, width;
	public:
		Rectangle(int, int);
		bool operator<(const Rectangle&);
		bool operator>(const Rectangle&);
		int operator+(const Rectangle&);
		int area() { return length*width; }
};

int main() {
	Rectangle a(3,2), b(4,3);
	cout << a + b << endl << (a <  b) << endl << (a > b) << endl;
	return 0;
}

// Class definitions
Rectangle::Rectangle(int x, int y) {
	length = x;
	width = y;
}

int Rectangle::operator+(const Rectangle &right) {
	return area()+(right.length*right.width);
}

bool Rectangle::operator<(const Rectangle &right) {
	return area() < (right.length*right.width);
}

bool Rectangle::operator>(const Rectangle &right) {
	return area() > (right.length*right.width);
}
