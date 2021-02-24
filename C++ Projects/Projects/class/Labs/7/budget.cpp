// This program reads in from the keyboard a record of financial information
// consisting of a person’s name, income, rent, food costs, utilities and
// miscellaneous expenses. It then determines the net money
// (income minus all expenses)and places that information in a record
// which is then written to an output file.

// Zachary Wellman

#include <fstream>
#include <iostream>
#include <iomanip>
using namespace std;

const int NAMESIZE = 15;

struct	budget	// declare a structure to hold name and financial information
{
	char name[NAMESIZE + 1];
	float income;		// person's monthly income
	float rent;			// person's monthly rent
	float food;			// person's monthly food bill
	float utilities;	// person's monthly utility bill
	float miscell;		// person's other bills
	float net;			// person's net money after bills are paid
};

int main()
{
	fstream indata;
	ofstream outdata;	// output file of
						// student.

	indata.open("income.dat", ios::out | ios::binary);	// open file as binary
														// output.

	outdata.open("student.out");	// output file that we
									// will write student
									// information to.

	outdata << left << fixed << setprecision(2);	// left indicates left
													// justified for fields

	budget person[50];	// defines person to be a record
	char test;
	int pos = 0;

	do {
		if (pos > 0) cin.ignore();
		cout << "Enter the following information" << endl;

		cout << "Person's name: ";
		cin.getline(person[pos].name, NAMESIZE);

		cout << "Income: ";
		cin >> person[pos].income;

		// FILL IN CODE TO READ IN THE REST OF THE FIELDS:
		// rent, food, utilities AND miscell TO THE person RECORD
		cout << "Rent: ";
		cin >> person[pos].rent;

		cout << "Food: ";
		cin >> person[pos].food;

		cout << "Utilities: ";
		cin >> person[pos].utilities;

		cout << "Miscellaneous: ";
		cin >> person[pos].miscell;

		// find the net field
		person[pos].net = person[pos].income - (person[pos].food + person[pos].utilities + person[pos].miscell); // FILL IN CODE TO DETERMINE NET INCOME (income - expenses)
		pos++;
		cout << endl << endl << "Enter a Y if you would like to input more data" << endl;
		cin >> test;
		cout << endl;
	} while (toupper(test) == 'Y');
	// write this record to the file
	// Fill IN CODE TO WRITE THE RECORD TO THE FILE indata (one instruction)
	indata.write((char*)&person, sizeof(person));

	indata.close();

	// FILL IN THE CODE TO REOPEN THE indata FILE, NOW AS AN INPUT FILE.
	indata.open("income.dat", ios::in);

	// FILL IN THE CODE TO READ THE RECORD FROM indata AND PLACE IT IN THE
	indata.read((char*)&person, sizeof(person));

	// write information to output file
	outdata << setw(20) << "Name" << setw(10) << "Income" << setw(10) << "Rent"
		    << setw(10) << "Food" << setw(15) << "Utilities" << setw(15)
		    << "Miscellaneous" << setw(10) << "Net Money" << endl << endl;

	for (int i = 0; i < pos; i++) {
		// FILL IN CODE TO WRITE INDIVIDUAL FIELD INFORMATION OF THE RECORD TO
		// THE outdata FILE.(several instructions)
		outdata << setw(20) << person[i].name << setw(10) << person[i].income << setw(10) << person[i].rent
				<< setw(10) << person[i].food << setw(15) << person[i].utilities << setw(15)
				<< person[i].miscell << setw(10) << person[i].net;
		if (i != (pos-1)) outdata << endl;
	}

	return 0;
}
