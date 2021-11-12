#include <iostream>
#include <fstream>
#include <string>
using namespace std;

struct FileBlock {
	char name[11];
	int location; // 0 - 49
	int size;
	bool inUse;
};

struct Data {
	char character[5]; // 5 characters per block
};

struct FreeIndex {
	bool isFree; // True if free, false if not
	Data * dataBlock; // A pointer to the referenced data block
};

void createFileSystem();
void readFileSystem();
void menu(int);
void manageFileSystem(char*, int);

int main() {
	int choice = 0;
	do {
		cout << "Menu:" << endl << "1) Create a new file system" << endl << "2) Read a current file system" << endl;
		cin >> choice;
		if (choice == 1) {
			createFileSystem();
		} else if (choice == 2) {
			readFileSystem();
		}
	} while (choice > 2 || choice < 0);
	return 0;
}

void createFileSystem() {
	char * fileName = new char[20];
	cout << "Enter the name of the file you want to create for the file system: ";
	cin >> fileName;
	manageFileSystem(fileName, 1);
}

void readFileSystem() {
	char * fileName = new char[20];
	cout << "Enter the name of the already created file system: ";
	cin >> fileName;
	manageFileSystem(fileName, 2);
}

void menu(int mode) {
	if (mode == 1) {
		cout << "Options:" << endl << "1) Create a file" << endl << "-1) Stop" << endl;
		return;
	}
	cout << "Options:" << endl << "1) Create a file" << endl << "2) Read a file" << endl << "3) Delete a file" << endl << "-1) Stop" << endl;
}

void manageFileSystem(char * fileName, int mode) {
	FileBlock fb[20]; // 20 files in the system, max
	for (int i = 0; i < 20; i++) fb[i].inUse = false;
	// 1 = newly created file system
	// 2 = already created file system
	FreeIndex fi[50]; // 50 free indexes (100 characters)
	Data d[50]; // 50 blocks of data
	for (int i = 0; i < 50; i++) {
		fi[i].isFree = true;
		fi[i].dataBlock = &d[i];
	}
	ofstream fs(fileName);
	int choice = 0;
	
	do {
		if (mode == 1) {
			do {
				menu(1);
				cin >> choice;
			} while (choice != 1 || choice != -1);
		} else if (mode == 2) {
			do {
				menu(2);
				cin >> choice;
			} while (choice < -1 || choice > 3 || choice == 0);
		}
		char fName[11];
		int fileBlockPosition, freeIndexPosition;
		// Do the menu choice
		switch(choice) {
		case 1:
			string text;
			// Create a file in the filesystem
			cout << "Input the name of the file you want to use: ";
			cin >> fName;
			fileBlockPosition = -1;
			bool nameInUse = false;
			for (int i = 0; i < 20; i++) {
				if (!fb[i].inUse && fileBlockPosition == -1) fileBlockPosition = i;
				if (fb[i].inUse && strcmp(fb[i].name, fName) == 0) {
					nameInUse = true;
				}
			}
			
			if (nameInUse) cout << "The name you choose already exists in the filesystem." << endl;
			else {
				if (fileBlockPosition == -1) cout << "There is no more room for a file." << endl;
				else {
					cout << "Please enter the text you want in this file: ";
					getline(cin, text);
					cin.ignore();
					freeIndexPosition = -1;
					for (int i = 0; i < 50 && freeIndexPosition == -1; i++) {
						if (fi[i].isFree) freeIndexPosition = i;
					}
					if (freeIndexPosition == -1) cout << "There is no more room for a file." << endl;
					else {
						fb[fileBlockPosition].name = fName;
						fb[fileBlockPosition].location = freeIndexPosition;
						fb[fileBlockPosition].size = text.size(); // Get the text they want to use, and then put it in here (or use the amount of free indexes they used for it)
						fb[fileBlockPosition].inUse = true;
						fi[freeIndexPosition].isFree = false;
						int length = fb[fileBlockPosition].size;
						int amountOfBlocksUsed = length/5;
						if (amountOfBlocksUsed == 1) {
							// Set all 5 characters in given free index to the corresponding string
						} else {
							// Fill the first one, then move on to the next until all characters are used
							// Need to find enough room to fit the full thing in
						}
						fi[freeIndexPosition].dataBlock->character = '\0'; // null terminator for now - get a way to put the data in the block, and add more if neccessary.
					}
				}
			}
			break;
		case 2:
			cout << "Input the file name that you want to read: ";
			cin >> fName;
			fileBlockPosition = -1;
			for (int i = 0; i < 20 && fileBlockPosition == -1; i++) {
				if (fb[i].inUse && strcmp(fb[i].name, fName) == 0) {
					fileBlockPosition = i;
				}
			}
			if (fileBlockPosition == -1) {
				cout << "The file could not be found." << endl;
			} else {
				// Get to the address of the Data block from the FreeIndex array, and then read from there
			}
			break;
		case 3:
			// Remove a file in the filesystem
			cout << "Input the name of the file you want to delete: ";
			cin >> fName;
			fileBlockPosition = -1;
			for (int i = 0; i < 20 && fileBlockPosition == -1; i++) {
				if (strcmp(fb[i].name, fName) == 0) {
					fileBlockPosition = i;
				}
			}
			if (fileBlockPosition == -1) cout << "There is no such file with that name." << endl;
			else {
				fb[fileBlockPosition].inUse = false; // Logically delete the file
			}
			break;
		}
	} while (choice != -1);
}
