#ifndef HASHTABLE_H
#define HASHTABLE_H

struct Record {
	int key;
	char word[51];
};

class HashTable
{
	public:
		HashTable(int);
		bool insertRec(const char[], int);
		bool deleteRec(const char[], int);
		int searchRec(const char[], int);
		void display();
	private:
		int tableSize;
		Record * info;
		int hash(const char[], int);
		void copyArray(const char[], int, Record&);
		bool sameWord(const char[], int, const Record&);
};

#endif
