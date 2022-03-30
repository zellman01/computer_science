// Option 1, student generated code

// Zachary Wellman

#include <iostream>
using namespace std;

float toKilo(int);
float toMile(int);

const float KILOCONV = .621;
const float MILECONV = 1.61;

int main() {
	int ans;
	do {
		cout << "Please input \n1 Convert miles to kilometers \n2 Convert kilometers to miles \n3 Quit" << endl << endl;
		cin >> ans;

		int convert;
		float calculated;
		switch(ans) {
		case 1:
			cout << "Please input the miles to be converted" << endl;
			cin >> convert;
			calculated = toKilo(convert);
			cout << convert << " miles = " << calculated << " kilometers." << endl << endl;
			break;
		case 2:
			cout << "Please input the kilometers to be converted" << endl;
			cin >> convert;
			calculated = toMile(convert);
			cout << convert << " kilometers = " << calculated << " miles." << endl << endl;
			break;
		}
	} while (ans < 3);
}

float toKilo(int miles) {
	return miles*MILECONV;
}

float toMile(int kilometers) {
	return kilometers*KILOCONV;
}
