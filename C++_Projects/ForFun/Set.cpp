// Zachary Wellman
// C++ side for sets (int)

#include <iostream>
using namespace std;

int setUnion(const int[], int, const int[], int, int&);
int setCompliment(int[], int, int[], int, int&);
bool binarySearch(const int[], int, int);
void sort(int[], int);

int setUnion(const int seta[], int seta_size, const int setb[], int setb_size, int& newSet_size) {
	newSet_size = seta_size+setb_size;
	int[] newSet = new int[newSet_size];
	newSet = {0};
	int insPos = 0;
	for (int i = 0; i < seta_size; i++) {
		newSet[insPos] = seta[i];
		insPos++;
	}
	for (int i = 0; i < setb_size; i++) {
		newSet[insPos] = setb[i];
		insPos++;
	}
	return newSet;
}

int setCompliment(int seta[], int seta_size, int[] universalSet, int universal_size, int& newSet_size) {
	newSet_size = universal_size-seta_size;
	int[] newSet = new int[newSet_size];
	if (newSet_size > 0) {
		int insPos = 0;
		sort(seta, seta_size);
		sort(universalSet, universal_size);
		for (int i = 0; i < universal_size; i++) {
			if (!binarySearch(seta, seta_size, universalSet[i])) {
				newSet[insPos] = universalSet[i];
				insPos++;
			}
		}
	}
	return newSet;
}

bool binarySearch(const int array[], int size, int value) {
	int first = 0, last = size - 1, middle;
	
	while (first <= last) {
		middle = (first+last)/2;
		if (array[middle]==value)
			return true;
		else if (array[middle] > value)
			last = middle - 1;
		else
			first = middle + 1;
	}
	return false;
}

void sort(int array[], int size) {
	int swaps = 0;
	do {
		for (int i = 0; i < size-1; i++) {
			if (array[i] > array[i+1]) {
				int temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
				swaps++;
			}
		}
	} while (swaps != 0);
}
