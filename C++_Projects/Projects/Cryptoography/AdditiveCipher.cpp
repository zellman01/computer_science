#include <iostream>
#include <string>
#include <cstring>

using namespace std;

int main() {
	int menuChoice;
	const char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	cout << "1.) Encryption\n";
	cout << "2.) Decryption\n";
	cout << "Please select the function you want: ";
	cin >> menuChoice;
	
	if (menuChoice == 1) {
		string word;
		int key;
		key = -1;
		cout << "Please input the word you would like to use for encryption: ";
		cin >> word;
		do {
			cout << "Please input the key value to encrypt (0-25): ";
			cin >> key;
			if (key < 0 || key > 25) {
				cout << "Key value must be between 1 and 25, inclusive.\n";
			}
		} while (key < 0 || key > 25);
		char string[word.size()+1];
		strcpy(string, word.c_str());
		// Make all charcters lowercase
		for (int i = 0; i < strlen(string); i++) {
			string[i] = tolower(string[i]);
		}
		for (int i = 0; i < strlen(string); i++) {
			if (string[i] == ' ' || string[i] == '\n' || string[i] == '.') {
				
			} else {
				int value = (int)(string[i])+key;
				// Execute the wrap-around if out of bounds
				if (value >= '{') {
					int backwards = 0;
					do {
						value--;
						backwards++;
					} while(value >= '{');
					value = (int)(toupper(alphabet[backwards-1]));
				}
				cout << (char)value;
			}
		}
	} else if (menuChoice == 2) {
		string word;
		int key;
		key = -1;
		cout << "Please input the ciphertext you want to decrypt: ";
		cin >> word;
			do {
			cout << "Please input the key value to decrypt (0-25): ";
			cin >> key;
			if (key < 0 || key > 25) {
				cout << "Key value must be between 0 and 25, inclusive.\n";
			}
		} while (key < 0 || key > 25);
		char string[word.size()+1];
		strcpy(string, word.c_str());
		// Make all charcters uppercase
		for (int i = 0; i < strlen(string); i++) {
			string[i] = toupper(string[i]);
		}
		for (int i = 0; i < strlen(string); i++) {
			if (string[i] == ' ' || string[i] == '\n' || string[i] == '.') {

			} else {
				int value = (int)(string[i])-key;
				// Execute the wrap-around if out of bounds
				if (value <= '@') {
					int forward = 0;
					do {
						value++;
						forward++;
					} while (value <= '@');
					value = (int)(alphabet[25-(forward-1)]);
				}
				cout << (char)value;
			}
		}
	}
}
