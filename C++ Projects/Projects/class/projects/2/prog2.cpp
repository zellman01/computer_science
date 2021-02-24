/* Name: Zachary Wellman
 * File Name: prog2.cpp
 * Date: 9 September, 2020
 * Program Description: Encrypting a given file using two different enctryption schemes
*/ 

#include <iostream>
#include <fstream>
using namespace std;

void readFile(char[]);
void getCaesarCipher(const char[], int, char[]);
void getAtbashCipher(const char[], int, char[]);
void showArray(const char[]);

int main() {
	const int size = 111;
	char fileInput[size], cipher1[size], cipher2[size];
	readFile(fileInput); // Set up the initial array, and putting it through the first encryption
	getCaesarCipher(fileInput, size, cipher1);
	getAtbashCipher(fileInput, size, cipher2);
	showArray(fileInput); showArray(cipher1); showArray(cipher2); // Show all three (plaintext and two ciphertexts)
}

/***********************************************************
* Name: readFile
* Description: Reads the information of a file, and then applies it to a character array
* Input: fileInput, the char array to which will recieve what is inside of the file
* Output: None
**********************************************************/
void readFile(char fileInput[]) {
	ifstream readFile("plain.txt"); // Obtain the input flle
	
	char character;
	int position = 0;
	while (readFile.get(character)) {
		fileInput[position] = character;
		position++;
	}
}

/***********************************************************
* Name: getCaesarCipher
* Description: It takes a character array, and shifts all characters in the array by a given amount (ROT-13 specifically) to encrypt it.
* Input: array, the caracter array to encrypt. size, how big the array is.
* Output: None
**********************************************************/
void getCaesarCipher(const char array[], int size, char result[]) {
	const char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	for (int i = 0; i < size; i++)
		result[i] = array[i];

	for (int index = 0; index < size-1 && result[index] != '\0'; index++)
		if (result[index] != ' ' && result[index] != '\n')
			result[index] = alphabet[result[index]%26];

	ofstream encryptedFile("ciphertext1.txt"); // Create the output file, put the ciphertext into it, and then close it
	encryptedFile << result;
	encryptedFile.close();
}

/***********************************************************
* Name: getAtbashCipher
* Description: Takes a character array, and flips what character is is given to the opposite side of the alphabet
* Input: array, the character array to encrypt., size, how big the array is.
* Output: None
**********************************************************/
void getAtbashCipher(const char array[], int size, char result[]) {
	const char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	const int alphabetSize = 26;
	char reverse[alphabetSize];
	int setPos;
	for (int i = alphabetSize-1, setPos = 0; i >= 0; i--) {
		reverse[setPos] = alphabet[i];
		setPos++;
	}
	for (int i = 0; i < size; i++)
		result[i] = array[i];
	for (int index = 0; index < size-1 && result[index] != '\0'; index++)
		if (result[index] != ' ' && result[index] != '\n')
			result[index] = reverse[result[index]-65];

	ofstream encryptedFile("ciphertext2.txt"); // Create the output file, put the ciphertext into it, and then close it
	encryptedFile << result;
	encryptedFile.close();
}

/***********************************************************
* Name: showArray
* Description: Prints the character array out onto the console
* Input: array, the chatacter array to be printed out to console
* Output: None
**********************************************************/
void showArray(const char array[]) {
	cout << array << endl << endl;
}
