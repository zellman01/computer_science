#include <iostream>

//Sorting and searching algorithms
int linearSearch(const int[], int, int);
int linearSearch(const int[], int, const int[], int, int[]);

// Returns the position in the array where the given value is, or -1 if not found (linear check)
int linearSearch(const int array[], int size, int value) {
	int searchResult = -1;
	for (int i = 0; i < size, i++) {
		if (array[i] == value) {
			searchResult = i;
		}
	}
	return searchResult;
}

// Same as above, but searches for multiple values (linear check)
void linearSearch(const int array[], int size, const int value[], int valueSize, int returnValues[]) {
	returnValues = new int[valueSize];
	for (int i = 0; i < valueSize; i++) {
		returnValues[i] = -1;
	}
	int pos = 0;
	for (int i = 0; i < valueSize; i++) {
		for (int j = 0; j < size; j++) {
			if (array[j] == value[i]) {
				returnValues[pos] = j;
			}
		}
		pos++;
	}
}


