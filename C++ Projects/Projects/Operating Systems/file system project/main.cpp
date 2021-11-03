#include <iostream>
#include <fstream>
using namespace std;

struct FileBlock {
	char name[11];
	int location;
}

void createFileSystem();
void readFileSystem();
void menu(int);
void manageFileSystem(char*, int);
void createFile();
void readFile();
void deleteFile();

int main() {
	int choice = 0;
	cout << "Menu:" << endl << "1) Create a new file system" << endl << "2) Read a current file system" << endl;
	cin >> choice;
	if (choice == 1) {
		createFileSystem();
	} else {
		readFileSystem();
	}
	/*char fileName[20];
	cout << "Enter the file name of the file system: ";
	cin >> fileName; // Do this more securely*/
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
	ofstream fs(fileName);
	int choice = 0;
	
	if (mode == 1) {
		do {
			menu(1);
			cin >> choice;
		} while (choice != 1);
	}
}