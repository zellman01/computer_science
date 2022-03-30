#include "HashTable.h"
#include <iostream>

using namespace std;

// Creates the hashtable
HashTable::HashTable(int size) {
	tableSize = size;
	info = new Record[tableSize];
	for (int i = 0; i < tableSize; i++) {
		info[i].key = 0;
	}
}

// Hashes the word to get the key and first position to attempt when inserting, searching and deleting
int HashTable::hash(const char word[], int size) {
	int sum = 0;
	for (int i = 0; i < size; i++) sum += (int)word[i];
	return (7 * sum) % tableSize;
}

// Copies the word array over to the record
void HashTable::copyArray(const char word[], int size, Record & rec) {
	for (int i = 0; i < size; i++) rec.word[i] = word[i];
	rec.word[size] = '\0';
}

// Checks if the record contains the same word as what the function was given
bool HashTable::sameWord(const char word[], int size, const Record & rec) {
	for (int i = 0; i < size; i++) if ((int)word[i] != (int)rec.word[i]) return false;
	return true;
}

// Inserts the record, and reports if the insertion was successful
bool HashTable::insertRec(const char word[], int size) {
	cout << "Inserting " << word << "." << endl;
	int hashKey = hash(word, size);
	cout << "Trying position " << hashKey << "." << endl;
	if (info[hashKey].key == 0 || info[hashKey].key == -1) {
		info[hashKey].key = hashKey;
		copyArray(word, size, info[hashKey]);
	} else {
		int pos = hashKey;
		for (int i = 1; i < (1+tableSize)/2; i++) {
			int k = (pos+(i*i))%tableSize;
			cout << "Trying position " << k << "." << endl;
			if (info[k].key == 0 || info[k].key == -1) {
				info[k].key = hashKey;
				copyArray(word, size, info[k]);
				return true;
			}
			pos = k;
		}
		return false;
	}
	return true;
}

// Searches for a record, and returns the given position. -1 if it was not found.
int HashTable::searchRec(const char word[], int size) {
	cout << "Searching for " << word << "." << endl;
	int hashKey = hash(word, size);
	cout << "Trying position " << hashKey << "." << endl;
	if (info[hashKey].key != 0) {
		if (sameWord(word, size, info[hashKey]) && info[hashKey].key != -1) return hashKey;
		int pos = hashKey;
		for (int i = 1; i < (1+tableSize)/2; i++) {
			int k = (pos+(i*i))%tableSize;
			cout << "Trying position " << k << "." << endl;
			if (sameWord(word, size, info[k]) && info[k].key != -1) return k;
			pos = k;
		}
	}
	return -1;
}

// Deletes a given record, and returns if it was successful
bool HashTable::deleteRec(const char word[], int size) {
	cout << "Deleting " << word << "." << endl;
	int pos = searchRec(word, size);
	if (pos == -1) return false;
	info[pos].key = -1;
	return true;
}

// Displays the hashtable to the console.
void HashTable::display() {
	for (int i = 0; i < tableSize; i++) {
		cout << "Position " << i;
		if (info[i].key > 0) cout << " has the word \"" << info[i].word << "\"." << endl;
		else cout << " is empty." << endl;
	}
}
