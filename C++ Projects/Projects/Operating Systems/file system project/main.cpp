#include <iostream>
#include <fstream>
#include <string>
#include <cstring>

#define CHARLIMIT 5
using namespace std;

struct FileBlock {
	char name[11];
	int location; // 0 - 49
	int size;
	bool inUse;
};

struct Data {
	char character[CHARLIMIT]; // 5 characters per block
};

struct FreeIndex {
	bool isFree; // True if free, false if not
	Data * dataBlock; // A pointer to the referenced data block
};

void createFileSystem();
void readFileSystem();
void menu(int);
void manageFileSystem(char*, int);
void saveFS(struct FileBlock[], struct FreeIndex[], struct Data[], FILE*, char*);

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
	FILE * out = fopen(fileName, "ab+");
	int choice = 0;
	
	do {
		if (mode == 1) {
			do {
				menu(1);
				cin >> choice;
			} while (choice < -1 || choice > 1 || choice == 0);
			mode = 3;
		} else if (mode == 2) {
			do {
				menu(2);
				cin >> choice;
			} while (choice < -1 || choice > 3 || choice == 0);
			fread(fb, sizeof(FileBlock), 20, out);
			fread(fi, sizeof(FreeIndex), 50, out);
			fread(d, sizeof(Data), 50, out);
			for (int i = 0; i < 50; i++) {
				fi[i].dataBlock = &d[i];
			}
			mode = 3;
		} else if (mode == 3) {
			do {
				menu(2);
				cin >> choice;
			} while (choice < -1 || choice > 3 || choice == 0);
		}
		if (choice == -1) {
			fclose(out);
			break;
		}
		char fName[11];
		int fileBlockPosition, freeIndexPosition;
		// Do the menu choice
		switch(choice) {
		case 1: {
			string text;
			// Create a file in the filesystem
			cout << "Input the name of the file you want to use: ";
			cin >> fName;
			cin.ignore();
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
					freeIndexPosition = -1;
					for (int i = 0; i < 50 && freeIndexPosition == -1; i++) {
						if (fi[i].isFree) freeIndexPosition = i;
					}
					if (freeIndexPosition == -1) cout << "There is no more room for a file." << endl;
					else {
						strcpy(fb[fileBlockPosition].name, fName);
						fb[fileBlockPosition].location = freeIndexPosition;
						fb[fileBlockPosition].size = text.size();
						fb[fileBlockPosition].inUse = true;
						int length = fb[fileBlockPosition].size;
						int amountOfBlocksUsed = length/CHARLIMIT;
						if (amountOfBlocksUsed == 1 || amountOfBlocksUsed == 0) {
							strcpy(fi[freeIndexPosition].dataBlock->character, text.c_str());
							fi[freeIndexPosition].isFree = false;
						} else {
							int pos = 0;
							int textSize = text.size();
							bool forNew = false;
							bool stop = false;
							do {
								forNew = false;
								int pos = -1;
								for (int i = 0; i < amountOfBlocksUsed; i++) {
									if (!fi[freeIndexPosition+(i+1)].isFree) {
										forNew = true;
									}
									if (forNew && fi[freeIndexPosition+(i+1)].isFree && pos == -1) {
										pos = freeIndexPosition+(i+1);
									}
								}
								if (pos == -1) {
								   for (int i = freeIndexPosition+amountOfBlocksUsed; i < 50; i++) {
									   if (fi[i].isFree) {
										  pos = i;
									   }
								   }
								}
								
								if (pos == -1) {
									cout << "There is no room for this size of a file.";
									stop = true;
								} else {
									freeIndexPosition = pos;
									fb[fileBlockPosition].location = freeIndexPosition;
								}
								
							} while(forNew && !stop);
							if (!stop) {
								pos = 0;
								do {
									string temp = text.substr(pos, pos+CHARLIMIT);
									text = text.substr(pos+CHARLIMIT+1, text.size());
									fi[freeIndexPosition].isFree = false;
									strcpy(fi[freeIndexPosition].dataBlock->character, temp.c_str());
									freeIndexPosition++;
									textSize -= CHARLIMIT;
								} while (textSize > 0);
							}
						}
						saveFS(fb, fi, d, out, fileName);
					}
				}
			}
			break;
		}
		case 2: {
			// Read a file in the FS
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
				FreeIndex temp = fi[fb[fileBlockPosition].location];
				int sizeTemp = fb[fileBlockPosition].size;
				do {
					printf(temp.dataBlock->character);
					temp = fi[fb[fileBlockPosition+1].location];
					sizeTemp -= CHARLIMIT;
				} while (sizeTemp > 0);
				cout << endl;
			}
			break;
		}
		case 3: {
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
			saveFS(fb, fi, d, out, fileName);
			break;
		}
		}
	} while (choice != -1);
}

void saveFS(struct FileBlock fb[], struct FreeIndex fi[], struct Data d[], FILE * out, char * fileName) {
	fclose(out);
	out = fopen(fileName, "wb");
	size_t f = fwrite(fb, sizeof(FileBlock), 20, out);
	size_t g = fwrite(fi, sizeof(FreeIndex), 50, out);
	size_t h = fwrite(d, sizeof(Data), 50, out);
	
	if (f == 0 || g == 0 || h == 0) ferror(out);
	
}
