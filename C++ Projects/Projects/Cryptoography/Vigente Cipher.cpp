#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>

// Use a map to map each character to a number, whch represents the encryption column to use. Use it to get the column (key). Then use the map again to obtain the row from the plaintext

using namespace std;

void encryption();
void decryption(const vector<string>*, const map<string, int>*);
string decrypt(string, string);
void final(string);


int main() {
	int choice = 0;
	vector<string> encryptionTable;
	map<string, int> mapping;
	
	// Insert all of the encryption chart, one after the other so that position 1 is row one and so on
	encryptionTable.push_back("ABCDEFGHIJKLMNOPQRSTUVWXYZ"); // Key = A (also master key list)
	encryptionTable.push_back("BCDEFGHIJKLMNOPQRSTUVWXYZA"); // Key = B
	encryptionTable.push_back("CDEFGHIJKLMNOPQRSTUVWXYZAB"); // Key = C
	encryptionTable.push_back("DEFGHIJKLMNOPQRSTUVWXYZABC"); // Key = D
	encryptionTable.push_back("EFGHIJKLMNOPQRSTUVWXYZABCD"); // Key = E
	encryptionTable.push_back("FGHIJKLMNOPQRSTUVWXYZABCDE"); // Key = F
	encryptionTable.push_back("GHIJKLMNOPQRSTUVWXYZABCDEF"); // Key = G
	encryptionTable.push_back("HIJKLMNOPQRSTUVWXYZABCDEFG"); // Key = H
	encryptionTable.push_back("IJKLMNOPQRSTUVWXYZABCDEFGH"); // Key = I
	encryptionTable.push_back("JKLMNOPQRSTUVWXYZABCDEFGHI"); // Key = J
	encryptionTable.push_back("KLMNOPQRSTUVWXYZABCDEFGHIJ"); // Key = K
	encryptionTable.push_back("LMNOPQRSTUVWXYZABCDEFGHIJK"); // Key = L
	encryptionTable.push_back("MNOPQRSTUVWXYZABCDEFGHIJKL"); // Key = M
	encryptionTable.push_back("NOPQRSTUVWXYZABCDEFGHIJKLM"); // Key = N
	encryptionTable.push_back("OPQRSTUVWXYZABCDEFGHIJKLMN"); // Key = O
	encryptionTable.push_back("PQRSTUVWXYZABCDEFGHIJKLMNO"); // Key = P
	encryptionTable.push_back("QRSTUVWXYZABCDEFGHIJKLMNOP"); // Key = Q
	encryptionTable.push_back("RSTUVWXYZABCDEFGHIJKLMNOPQ"); // Key = R
	encryptionTable.push_back("STUVWXYZABCDEFGHIJKLMNOPQR"); // Key = S
	encryptionTable.push_back("TUVWXYZABCDEFGHIJKLMNOPQRS"); // Key = T
	encryptionTable.push_back("UVWXYZABCDEFGHIJKLMNOPQRST"); // Key = U
	encryptionTable.push_back("VWXYZABCDEFGHIJKLMNOPQRSTU"); // Key = V
	encryptionTable.push_back("WXYZABCDEFGHIJKLMNOPQRSTUV"); // Key = W
	encryptionTable.push_back("XYZABCDEFGHIJKLMNOPQRSTUVW"); // Key = X
	encryptionTable.push_back("YZABCDEFGHIJKLMNOPQRSTUVWX"); // Key = Y
	encryptionTable.push_back("ZABCDEFGHIJKLMNOPQRSTUVWXY"); // Key = Z
	
	string letter[26] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	
	// Insert into the map
	for (int i= 0; i < 26; i++) {
		mapping.insert(pair<string,int>(letter[i],i)); // Key mapping - What line of the table to use based on a given letter
	}
	
	
	do {
		cout << "Please input a number.\n1: Encryption\n2: Decryption\n";
		cin >> choice;
		cin.ignore();
		if (choice == 1) {
			encryption();
		} else if (choice == 2) {
			decryption(&encryptionTable, &mapping);
		}
	} while (choice < 1 || choice > 2);
}

void encryption() {
	string fileName, key, line, cipher;
	ifstream file;
	int keyLocation = 0; // At what part of the key is the program using
	
	cout << "Please input the file name to be used: ";
	getline(cin, fileName);
	cout << "Please input the key to encrypt the file with: ";
	getline(cin, key);
	// Do the encryption
	file.open(fileName);
	
	while (file) {
		if (getline(file, line, '\n')) {
			for (int i = 0; i < line.size(); i++) {
				char x = (toupper(line[i])+toupper(key[keyLocation]))%26;
				x += 'A';
				if (isalpha(line[i])) {
					cipher.push_back(x);
					keyLocation++;
					if (keyLocation >= key.size()) keyLocation = 0;
				}
			}
		}
	}
	file.close();
	final(cipher);
}

void decryption(const vector<string> * encryptionTable, const map<string, int> * mapping) {
	string fileName;
	
	cout << "Please input the file name to be used: ";
	getline(cin, fileName);
	
	ifstream file;
	file.open(fileName);
	string text, cipher = "", key;
	
	// Insert ciphertext into cipher string variable
	while (file) {
		if (getline(file, text, '\n')) {
			cipher += text;
		}
	}
	
	file.close();
	
	// find index of coincidence
	int indexCoincidence[26] = {0}, indexSquared[26] = {0}, squaredSum = 0;
	
	for (int i = 0; i < cipher.size(); i++) {
		indexCoincidence[cipher.at(i)-'A']++;
	}
	
	for (int i = 0; i < 26; i++) {
		indexSquared[i] = indexCoincidence[i]*indexCoincidence[i];
		squaredSum += indexSquared[i];
	}
	
	squaredSum -= cipher.size();
	
	double n = cipher.size()*1.0; // Number of letters in ciphertext
	double I = (squaredSum*1.0)/(cipher.size()*(cipher.size()-1)*1.0);;
	
	double denom = (n-1)*I-(0.038*n)+0.065;
	double numer = 0.027*n;
	
	double lengthKeywordNumber = numer/denom; // Decimal to determine the length of the keyword
	
	int keywordLength = lengthKeywordNumber; // Truncate the above number
	
	cout << "Frequency of characters:" << endl;
	for (int i = 0; i < 26; i++) {
		double frequency = indexCoincidence[i]/n;
		char x = i + 'A';
		cout << x << ": " << frequency << endl;
	}
	
	cout << "Index of coincidence: " << I << endl;
	
	int staticalAnalysis[26][keywordLength];
	char keyword[keywordLength];
	
	for (int pos = 0; pos < keywordLength; pos++) {
		int addition = pos+1;
		for (int pos1 = 0; pos1 < cipher.size(); pos1 = (pos1*keywordLength)+addition) {
			staticalAnalysis[cipher.at(pos1)-'A'][pos]++;
		}
	}
	
	for (int i = 0; i < keywordLength; i++) {
		char frequentLetter;
		int frequentLetterOccurence = 0;
		// Find what plaintext e most likely is
		for (int j = 0; j < 26; j++) {
			if (staticalAnalysis[j][i] > frequentLetterOccurence) {
				frequentLetter = j+'A';
				frequentLetterOccurence = staticalAnalysis[j][i];
			}
		}
		
		cout << frequentLetter << "|" << endl;
		
		auto itr = mapping->find("E");
		
		int keywordLetter = encryptionTable->at(itr->second).find(frequentLetter);
		keyword[i] = encryptionTable->at(0).at(keywordLetter);
	}
	cout << "Possible keyword: " << keyword << endl;
	
	string decryptedText = decrypt(cipher, keyword);
	cout << "Decrypted text: " << decryptedText << endl;
	
	do {
		char userInput;
		cout << endl << "Are you satisified with the Decrypted text? (y for yes, n for no): ";
		cin >> userInput;
		cin.ignore();

		if (tolower(userInput) == 'y') {
			break;
		} else {
			string key;
			cout << "Please enter the keyword: ";
			cin >> key;
			decryptedText = decrypt(cipher, key);
			cout << "Decrypted text: " << decrypt(cipher, key) << endl;
		}
	} while (true);
	final(decryptedText);
}

string decrypt(string cipher, string key) {
	int keyLocation = 0;
	string decryption = "";
	for (int i = 0; i < cipher.size(); i++) {
		char x = (toupper(cipher[i])-toupper(key[keyLocation])+26)%26;
		x += 'A';
		decryption.push_back(x);
		keyLocation++;
		if (keyLocation >= key.size()) keyLocation = 0;
	}
	return decryption;
}

void final(string text) {
	int choice = 0;
	do {
		cout << "Please input a number to display results.\n1: display to console\n2: output to file\n";
		cin >> choice;
		if (choice == 1) {
			cout << text;
		}
		if (choice == 2) {
			// output info to file
			string fileName;
			cout << "Please input the name of the file you would like to use: ";
			cin >> fileName;
			ofstream file(fileName);
			file << text;
			file.close();
		}
	} while (choice < 1 || choice > 2);
}
