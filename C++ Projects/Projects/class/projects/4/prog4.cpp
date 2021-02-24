/*Name: Zachary Wellman
 * File Name: prog4.cpp
 * Date: 10 October, 2020
 * Program Description: A calculator
*/

#include <iostream>
#include <cstring>
#include <fstream>
#include <cmath>
using namespace std;

void fibonacci(int, ostream&);
void factorial(int, ostream&);
void binaryOutput(float, float, char, ostream&);
void binaryOutput(int, int, char, ostream&);
void help(ostream&);

int main(int argc, char** argv) {
	switch (argc) {
	case 2:
		if (strcmp(argv[1], "--help")==0) help(std::cout); // If --help is present
		break;
	case 3:
		if (strcmp(argv[2], "F")==0) fibonacci(atoi(argv[1]), std::cout);
		else if (strcmp(argv[2], "!")==0) factorial(atoi(argv[1]), std::cout);
		break;
	case 4:
		if (strcmp(argv[1], "--help")==0) { // If --help is to be sent to a file
			ofstream file(argv[3]);
			help(file);
		} else {
			if (strcmp(argv[2], "%")==0|| strcmp(argv[2], "P")==0) {
				int num1 = atoi(argv[1]), num2 = atoi(argv[3]);
				binaryOutput(num1, num2, *argv[2], std::cout);
			} else {
				float num1 = atof(argv[1]), num2 = atof(argv[3]);
				binaryOutput(num1, num2, *argv[2], std::cout);
			}
		}
		break;
	case 5: { // Because of a compile-time error
		ofstream file(argv[4]);
		if (strcmp(argv[2], "F")==0) fibonacci(atoi(argv[1]), file);
		else if (strcmp(argv[2], "!")==0) factorial(atoi(argv[1]), file);
		break;
	}
	case 6: { // Compile-time error
		ofstream file(argv[5]);
		if (strcmp(argv[2], "%")==0|| strcmp(argv[2], "P")==0) {
				int num1 = atoi(argv[1]), num2 = atoi(argv[3]);
				binaryOutput(num1, num2, *argv[2], file);
			} else {
				float num1 = atof(argv[1]), num2 = atof(argv[3]);
				binaryOutput(num1, num2, *argv[2], file);
			}
		break;
	}
	default:
		help(std::cout);
		break;
	}
}

/***********************************************************
* Name: fibonacci
* Description: Obtain the first n-th numbers in the fibonacci sequence
* Input: amount, the first n-th numbers to go up to. output, where to print the fibonacci sequence to.
* Output: N/A
**********************************************************/
void fibonacci(int amount, ostream & output) {
	if (amount < 1) {
		output << "The number must be at least 1 or higher. You inputted " << amount << ".";
		return;
	}
	int * fib = new int [amount];
	fib[0] = 1;
	fib[1] = 1;

	output << "The first " << amount << " Fibonacci numbers are: ";
	output << fib[0];
	if (amount > 1) output << " " << fib[1];

	for (int i = 2; i < amount; i++) {
		fib[i] = fib[i-1] + fib[i-2];
		output << " " << fib[i];
	}
	delete [] fib;
}

/***********************************************************
* Name: factorial
* Description: Calculates the factorial of a number, then prints it out
* Input: number, the number to get the factorial of. output, where to print the factorial to.
* Output: N/A
**********************************************************/
void factorial(int number, ostream & output) {
	int sum;
	if (number < 0) {
		output << "Factorial must be 1 or greater.";
		return;
	} else {
		sum = number;
		for (int i = number-1; i > 1; i--) {
			sum *= i;
		}
	}
	if (sum==0) sum++;
	output << "The factorial of " << number << " is " << sum;
}

/***********************************************************
* Name: binaryOutput
* Description: Takes two numbers, and uses the operation to modify them in some way
* Input: num1, the first number. num2, the second number. operation, the operation to be used on the two numbers. output, the source output, either the console or a file
* Output: N/A
**********************************************************/
void binaryOutput(float num1, float num2, char operation, ostream & output) {
	float sum = 0;
	switch (operation) {
	case '+':
		sum = num1+num2;
		break;
	case '-':
		sum = num1-num2;
		break;
	case '*':
		sum = num1*num2;
		break;
	case '/':
		sum = num1/num2;
		break;
	}
	output << num1 << " " << operation << " " << num2 << " = " << sum << endl;
}

/***********************************************************
* Name: binaryOutput
* Description: Takes two numbers, and uses the operation to modify them in some way
* Input: num1, the first number. num2, the second number. operation, the operation to be used on the two numbers. output, the source output, either the console or a file
* Output: N/A
**********************************************************/
void binaryOutput(int num1, int num2, char operation, ostream & output) {
	float sum = 0;
	switch (operation) {
	case '%':
		sum = num1%num2;
		break;
	case 'P':
		sum = pow(num1, num2);
		break;
	}
	output << num1 << " " << operation << " " << num2 << " = " << sum << endl;
}

void help(ostream & output) {
	output << "Usage: calc <X> <operator> [Y] [options]";
}
