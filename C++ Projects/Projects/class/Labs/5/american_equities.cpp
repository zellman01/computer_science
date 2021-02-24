// This program tests a password for the American Equities
// web page to see if the format is correct

// Zachary Wellman

#include <iostream>
#include <cctype>
#include <cstring>
using namespace std;

//function prototypes
bool testPassWord(char[]);
int countLetters(char*);
int countDigits(char*);

int main()
{
	char passWord[20];

	cout << "Enter a password consisting of exactly 4 "
		 << "letters (all lowercase) and 6 digits:" << endl;

	cin.getline(passWord, 20);

	if (testPassWord(passWord))
		cout << "Please wait - your password is being verified" << endl;

	else
	{
		cout << "Invalid password. Please enter a password "
			 << "with exactly 4 letters and 6 digits" << endl;
		cout << "For example, my37r8n901 is valid" << endl;
	}

	// Fill in the code that will call countLetters and
	// countDigits and will print to the screen both the number of
	// letters and digits contained in the password.
	cout << "Number of valid letters: " << countLetters(passWord) << "\nNumber of valid digits: " << countDigits(passWord) << endl;

	return 0;
}

//**************************************************************
//	testPassWord
//
//  task:	       determines if the word in the
//	               character array passed to it, contains
//	               exactly 5 letters and 3 digits.
//  data in:	   a word contained in a character array
//  data returned: true if the word contains 5 letters & 3
//	               digits, false otherwise
//
//**************************************************************
bool testPassWord(char custPass[])
{
	int numLetters, numDigits, length;

	length = strlen(custPass);
	numLetters = countLetters(custPass);
	numDigits = countDigits(custPass);

	if (numLetters == 4 && numDigits == 6 && length == 10)
		return true;
	else
		return false;
}

// the next 2 functions are from Sample Program 10.5

//*******************************************************************
//	countLetters
//
//  task:	       This function counts the number of letters
//	               (both capital and lower case) in the string
//  data in:	   pointer that points to an array of characters
//  data returned: number of letters in the array of characters
//
//*******************************************************************

int countLetters(char *strPtr)
{
	int occurs = 0;

	while (*strPtr != '\0')	// loop is executed as long as
		// the pointer strPtr does not point
		// to the null character which
		// marks the end of the string
	{
		if (isalpha(*strPtr) && islower(*strPtr))	// isalpha determines if
			occurs++;								// the character is a letter
													// islower determines if
													// the character is lowercase

		strPtr++;
	}

	return occurs;
}

//*******************************************************************
//	countDigits
//
//  task:	       This function counts the number of digits
//	               in the string
//  data in:	   pointer that points to an array of characters
//  data returned: number of digits in the array of characters
//
//*******************************************************************

int countDigits(char *strPtr)
{
	int occurs = 0;

	while (*strPtr != '\0')
	{
		if (isdigit(*strPtr))	// isdigit determines if
			occurs++;			// the character is a digit

		strPtr++;
	}

	return occurs;
}
