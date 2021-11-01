#include <iostream>
#include <fstream>
using namespace std;

struct FileBlock {
	char name[11];
	int location;
}

int main() {
	char fileName[20];
	cout << "Enter the file name of the file system: ";
	cin >> fileName; // Do this more securely
}