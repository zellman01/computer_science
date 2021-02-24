#include <iostream>
#include <cstring>
#include <fstream>
#include "HashTable.h"

using namespace std;

void displayResult(int);
void menuOptions();
void readFile(const char[], HashTable&);

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char** argv) {
	HashTable a(17);
	char word[51] = {};

	cout << "Please input a file that contains words 50 characters or less separated by a new line here. Include the file extension as well." << endl;
	cin >> word;
	readFile(word, a);
	cout << endl;
	
	char inputOption = ' ';
	// Menu options:
	// 1 - insert
	// 2 - search
	// 3 - delete
	// 4 - Display the table
	// 9 - exit
	while ((int)inputOption != (int)'9') {
		int length = 0;
		cout << "What would you like to do?" << endl;
		menuOptions();
		cin >> inputOption;
		cin.ignore();
		cout << endl;
		switch((int)inputOption) {
			case (int)'1':
				cout << "What word would you like to insert?" << endl;
				cin >> word;
				length = strlen(word);
				displayResult(a.insertRec(word, length));
				break;
			case (int)'2': {
				cout << "What word would you like to look for?" << endl;
				cin >> word;
				length = strlen(word);
				int position = a.searchRec(word, length);
				if (position < 0) {
					cout << "The word could not be found." << endl;
				} else {
					cout << "The word was in position " << position << "." << endl;
				}
				break;
			}
			case (int)'3': {
				cout << "What word would you like to remove from the hash table?" << endl;
				cin >> word;
				length = strlen(word);
				displayResult(a.deleteRec(word, length));
				break;
			}
			case (int)'4':
				a.display();
				break;
		}
		cout << endl;
	}
}

// Displays the menu options
void menuOptions() {
	cout << "1 - Insert a new word into the hash table" << endl;
	cout << "2 - Search the hash table for a word" << endl;
	cout << "3 - Delete a given word from the hash table (if it exists)" << endl;
	cout << "4 - Display the hash table" << endl;
	cout << "9 - Exits the program" << endl;
}

// Displays if insert/delete was successful
void displayResult(int result) {
	if (result == 1) cout << "Successful." << endl;
	else cout << "Unsuccessful." << endl;
}

// Reads a given file, and then proceeds to fill the hash table with found words
void readFile(const char fileName[], HashTable & hashTable) {
	ifstream readFile(fileName);
	
	char * word = new char[51];
	while(readFile.getline(word, 50)) {
		displayResult(hashTable.insertRec(word, strlen(word)));
	}
	delete word;
}
