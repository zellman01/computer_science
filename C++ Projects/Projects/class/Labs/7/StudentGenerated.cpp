// Zachary Wellman
// Option 1

#include <iostream>
#include <fstream>
#include <iomanip>
using namespace std;

struct person {
	char firstName[16], lastName[16], address[31], city[21], state[6];
	int zip;
};

int main() {
	fstream data("studentGenerated.bin", ios::out | ios::binary);
	ofstream output("studentGenerated.txt");
	
	output << left << fixed;
	
	output << setw(16) << "First Name" << setw(16) << "Last Name" << setw(31) << "Street"
		    << setw(21) << "City" << setw(21) << "State" << setw(6)
		    << "Zip Code" << endl << endl;
	
	person persons[20];
	char test;
	int pos = 0;
	do {
		if (pos != 0) cin.ignore();
		cout << "Enter the following information" << endl;
		
		cout << "Person's First Name: ";
		cin.getline(persons[pos].firstName, 15);
		
		cout << "Person's Last Name: ";
		cin.getline(persons[pos].lastName, 15);
		
		cout << "Street: ";
		cin.getline(persons[pos].address, 30);
		
		cout << "City: ";
		cin.getline(persons[pos].city, 20);
		
		cout << "State: ";
		cin.getline(persons[pos].state, 5);
		
		cout << "Zip: ";
		cin >> persons[pos].zip;
		
		cout << endl << endl << "Enter a Y if you would like to input more data ";
		cin >> test;
		cout << endl;
		pos++;
		
	} while (toupper(test) == 'Y');
	data.write((char*)&persons, sizeof(persons));
	data.close();
	data.open("studentGenerated.bin", ios::in | ios::binary);
	
	data.read((char*)&persons, sizeof(persons));
	data.close();
	
	for (int i = 0; i < pos; i++) {
		output << setw(16) << persons[i].firstName << setw(16) << persons[i].lastName << setw(31) << persons[i].address
		    << setw(21) << persons[i].city << setw(21) << persons[i].state << setw(6)
		    << persons[i].zip << endl << endl;
	}
}
