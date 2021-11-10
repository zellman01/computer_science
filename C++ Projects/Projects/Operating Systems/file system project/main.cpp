#include <iostream>
#include <fstream>
using namespace std;

struct FileBlock {
	char name[11];
	int location;
	int size;
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
void createFile();
void readFile();
void deleteFile();

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
		cout << "Options:" << endl << "1) Create a file" << endl;
		return;
	}
	cout << "Options:" << endl << "1) Create a file" << endl << "2) Read a file" << endl << "3) Delete a file" << endl;
}

void manageFileSystem(char * fileName, int mode) {
	FileBlock fb[20]; // 20 files in the system, max 
	// 1 = newly created file system
	// 2 = already created file system
	FreeIndex fi[50]; // 50 free indexes (100 characters)
	Data d[50]; // 50 blocks of data
	for (int i = 0; i < 50; i++) {
		fi[i].dataBlock = &d[i];
	}
	ofstream fs(fileName);
	int choice = 0;
	
	if (mode == 1) {
		do {
			menu(1);
			cin >> choice;
		} while (choice != 1);
	}
}
