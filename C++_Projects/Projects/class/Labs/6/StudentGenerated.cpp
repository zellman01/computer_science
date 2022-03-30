// Zachary Wellman
// Option 2
#include <iostream>
#include <string>
using namespace std;

struct airport {
	int landed, departed, mostPlanes, leastPlanes;
};

string month(int);

int main() {
	airport prog[12];
	int input, avgLanded = 0, avgDeparted = 0, totalLandingDeparting = 0;
	for (int i = 0; i < 12; i++) {
		string aMonth = month(i);
		cout << "Input the total amount of planes that landed in " << aMonth << ": ";
		cin >> input;
		prog[i].landed = input;
		avgLanded += input;

		cout << "Input the total amount of planes that departed in " << aMonth << ": ";
		cin >> input;
		prog[i].departed = input;
		avgDeparted += input;
		
		totalLandingDeparting += prog[i].landed + prog[i].departed;

		cout << "Input the greatest number of planes that landed in " << aMonth << " on a single day: ";
		cin >> input;
		prog[i].mostPlanes = input;

		cout << "Input the least number of planes that landed in " << aMonth << " on a single day: ";
		cin >> input;
		prog[i].leastPlanes = input;
		
		cout << endl;
	}
	avgLanded /= 12;
	avgDeparted /= 12;

	int greatestMonthNum = 0, leastMonthNum = 0, mostLanded = prog[0].mostPlanes, leastLanded = prog[0].leastPlanes;
	for (int i = 1; i < 12; i++) {
		if (mostLanded < prog[i].mostPlanes) {
			mostLanded = prog[i].mostPlanes;
			greatestMonthNum = i;
		}
		if (leastLanded > prog[i].leastPlanes) {
			leastLanded = prog[i].leastPlanes;
			leastMonthNum = i;
		}
	}

	cout << endl << "Average amount of planes that landed: " << avgLanded << "." << endl;
	cout << "Average amount of planes that departed: " << avgDeparted << "." << endl;
	cout << "Total amount of planes that landed and departed: " << totalLandingDeparting << "." << endl;
	cout << endl << "Greatest amount of planes that landed was " << mostLanded << ", and occured on " << month(greatestMonthNum) << "." << endl;
	cout << "Least amount of planes that landed was " << leastLanded << ", and occured on " << month(leastMonthNum) << ".";

	return 0;
}

string month(int num) {
	num++;
	switch (num) {
	case 1:
		return "January";
	case 2:
		return "Feburary";
	case 3:
		return "March";
	case 4:
		return "April";
	case 5:
		return "May";
	case 6:
		return "June";
	case 7:
		return "July";
	case 8:
		return "August";
	case 9:
		return "September";
	case 10:
		return "October";
	case 11:
		return "November";
	case 12:
		return "December";
	}
}
