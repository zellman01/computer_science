// Zachary Wellman
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>

using namespace std;
void q1(ifstream&);
void q2(ifstream&, char);
void q3(ifstream&, char);

struct Information {
	string first, last, phone;
};

int main() {
	ifstream file1("tab_separated.dat");
	q1(file1);
	file1.close();
	cout << endl << endl;
	
	file1.open("comma_separated.csv");
	q2(file1, ',');
	file1.close();
	cout << endl;
	
	file1.open("custom.dat");
	q3(file1, '|');
	file1.close();
	return 0;
}

void q1(ifstream & file) {
	char chara;
	while (file >> chara) {
		cout << chara;
	}
}

void q2(ifstream & file, char delim) {
	string aString;
	int total=0, nums=0;
	while(getline(file, aString)) {
		stringstream line(aString);
		string test;
		while (getline(line, test, delim)) {
			nums++;
			total += stoi(test);
		}
	}
	cout << total/nums << endl;
}

void q3(ifstream & file, char delim) {
		Information test;
	while (getline(file, test.first, delim)) {
		getline(file, test.last, delim);
		getline(file, test.phone, '\0');
		cout << test.first << ", " << test.last << ", " << test.phone << "." << endl;
	}
}
