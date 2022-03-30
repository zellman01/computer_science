// Zachary Wellman
#include <iostream>
using namespace std;

class SavingsAccount {
	private:
		int dollars, cents;
		void normalize() {              // Run it on the other normalize function
			normalize(dollars, cents);
		}
		void normalize(int&, int&);     // Normalizes cents
	public:
		SavingsAccount(int=0, int=0);       // Creates a new SavingsAccount object
		void deposit(int, int);
		void withdraw(int, int);
		void balance();
};

int main() {
	int dol, cen;
	cout << "Please input the initial dollars" << endl;
	cin >> dol;
	cout << "Please input the initial cents" << endl;
	cin >> cen;
	SavingsAccount bank1(dol, cen), bank2;
	cout << endl;
	
	char test;
	cout << "Would you like to make a deposit? Y or y for yes" << endl;
	cin >> test;
	while (tolower(test) == 'y') {
		cout << "Please input the dollars to be deposited" << endl;
		cin >> dol;
		cout << "Please input the cents to be deposited" << endl;
		cin >> cen;
		bank1.deposit(dol, cen);
		bank2.deposit(dol, cen);
		cout << "Would you like to make a deposit? Y or y for yes" << endl;
		cin >> test;
	}
	
	cout << "Would you like to make a withdrawl? Y of y for yes" << endl;
	cin >> test;
	while(tolower(test) == 'y') {
		cout << "Please input the dollars to be widthdrawn" << endl;
		cin >> dol;
		cout << "Please input the cents to be widthdrawn" << endl;
		cin >> cen;
		bank1.withdraw(dol, cen);
		bank2.withdraw(dol, cen);
		cout << "Would you like to make a withdrawl? Y of y for yes" << endl;
		cin >> test;
	}
	cout << endl;
	bank1.balance();
	cout << endl;
	bank2.balance();
}

// Class methods
SavingsAccount::SavingsAccount(int dol, int cen) {
	normalize(dol, cen);
	dollars = dol;
	cents = cen;
}

void SavingsAccount::deposit(int dol, int cen) {
	normalize(dol, cen);
	dollars += dol,
	cents += cen;
	normalize();
}

void SavingsAccount::withdraw(int dol, int cen) {
	normalize(dol, cen);
	dollars -= dol;
	cents -= cen;
	normalize();
}

void SavingsAccount::balance() {
	cout << "Dollars = " << dollars << "  cents = " << cents << ".";
}

void SavingsAccount::normalize(int & dol, int & cen) {
	int temp = 0;
	temp = cen%100;
	if (temp > 0) {
	   dol += temp;
	   cen -= temp*100;
	}
	if (cen < 0) {
		while (cen < 0) {
			dol--;
			cen += 100;
		}
	}
}
