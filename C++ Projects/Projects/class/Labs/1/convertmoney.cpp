#include <iostream>
#include <iomanip>
using namespace std;

// This program will input American money and convert it to foreign currency

// Zachary Wellman

// Global variables
const float EUROCONV = 1.06, PESOSCONV = 9.73, YENCONV = 124.35;

// Prototypes of the functions
void convertMulti(float dollars, float& euros, float& pesos);
void convertMulti(float dollars, float& euros, float& pesos, float& yen);
float convertToYen(float dollars);
float convertToEuros(float dollars);
float convertToPesos(float dollars);

int main()
{
	float dollars;
	float euros;
	float pesos;
	float yen;

	cout << fixed << showpoint << setprecision(2);

	cout << "Please input the amount of American Dollars you want converted "
		 << endl;
	cout << "to euros and pesos" << endl;
	cin >> dollars;
	// Fill in the code to call convertMulti with parameters dollars, euros, and pesos
	convertMulti(dollars, euros, pesos);

	// Fill in the code to output the value of those dollars converted to both euros
	// and pesos
	cout << endl << "$" << dollars << " is converted to: \n Euros: " << euros << "\n Pesos: " << pesos << endl << endl;

	cout << "Please input the amount of American Dollars you want converted\n";
	cout << "to euros, pesos and yen" << endl;
	cin >> dollars;

	// Fill in the code to call convertMulti with parameters dollars, euros, pesos and yen
	convertMulti(dollars, euros, pesos, yen);

	// Fill in the code to output the value of those dollars converted to euros,
	// pesos and yen
	cout << endl << "$" << dollars << " is converted to: \n Euros: " << euros << "\n Pesos: " << pesos << "\n Yen: " << yen << endl << endl;

	cout << "Please input the amount of American Dollars you want converted\n";
	cout << "to yen" << endl;
	cin >> dollars;

	// Fill in the code to call convertToYen
	yen = convertToYen(dollars);

	// Fill in the code to output the value of those dollars converted to yen
	cout << endl << "$" << dollars << " is converted to: \n Yen: " << yen << endl << endl;

	cout << "Please input the amount of American Dollars you want converted\n";
	cout << " to euros" << endl;
	cin >> dollars;

	// Fill in the code to call convert ToEuros
	euros = convertToEuros(dollars);

	// Fill in the code to output the value of those dollars converted to euros
	cout << endl << "$" << dollars << " is converted to: \n Euros: " << euros << endl << endl;

	cout << "Please input the amount of American Dollars you want converted\n";
	cout << " to pesos " << endl;
	cin >> dollars;

	// Fill in the code to call convertToPesos
	pesos = convertToPesos(dollars);

	// Fill in the code to output the value of those dollars converted to pesos
	cout << endl << "$" << dollars << " is converted to \n Pesos: " << pesos << endl << endl;

	return 0;
}

// All of the functions are stubs that just serve to test the functions
// Replace with code that will cause the functions to execute properly

//	**************************************************************************
//	convertMulti
//
//	task:	  This function takes a dollar value and converts it to euros
//	          and pesos
//	data in:  dollars
//	data out: euros and pesos
//
//	*************************************************************************

void convertMulti(float dollars, float& euros, float& pesos)
{
		 euros = convertToEuros(dollars);
		 pesos = convertToPesos(dollars);
}

//	************************************************************************
//	convertMulti
//
//	task:	  This function takes a dollar value and converts it to euros
//	          pesos and yen
//	data in:  dollars
//	data out: euros pesos yen
//
//	***********************************************************************

void convertMulti(float dollars, float& euros, float& pesos, float& yen)
{
		 convertMulti(dollars, euros, pesos);
		 yen = convertToYen(dollars);
}

//	****************************************************************************
//	convertToYen
//
//	task:	       This function takes a dollar value and converts it to yen
//	data in:	   dollars
//	data returned: yen
//
//	***************************************************************************

float convertToYen(float dollars)
{
	return dollars * YENCONV;
}

//	****************************************************************************
//	convertToEuros
//
//	task:	       This function takes a dollar value and converts it to euros
//	data in:	   dollars
//	data returned: euros
//
//	****************************************************************************

float convertToEuros(float dollars)
{
	return dollars * EUROCONV;
}

//	*****************************************************************************
//	convertToPesos
//
//	task:	       This function takes a dollar value and converts it to pesos
//	data in:	   dollars
//	data returned: pesos
//
//	****************************************************************************

float convertToPesos(float dollars)
{
	return dollars * PESOSCONV;
}
