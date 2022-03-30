// Student generated code

// Zachary Wellman

#include<iostream>

using namespace std;

void sort(int[], const int);
int binarySearch(const int[], const int, int);
float calculateMean(const int[], const int);
void printInformation(const int[], const int[], const int, const int, const int, const float);
void printArray(const int [], const int);

int main() {
	int array[50] = {0}, sortedArray[50] = {0};
	int size = 0, testFor, valueSearch = 3;
	do {
		cout << "Enter a number to add to the array (Enter -999 to quit): ";
		cin >> testFor;
		cout << endl;
		array[size] = testFor;
		sortedArray[size] = testFor;
		size++;
	} while (testFor != -999);
	size--;
	sort(sortedArray, size);
	printInformation(array, sortedArray, size, valueSearch, binarySearch(sortedArray, size, valueSearch), calculateMean(array, size));
	return 0;
}

void sort(int array[], const int size) {
	bool swap;
	int temp, bottom = size-1;
	do {
		swap = false;
		for (int i = 0; i < bottom; i++) {
			if (array[i] > array[i+1]) {
				temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
				swap = true;
			}
		}
		bottom--;
	} while (swap != false);
}

int binarySearch(const int array[], const int size, int value) {
	int mid, first = 0, last = size-1;
	while (first <= last) {
		mid = (first+last)/2;
		
		if (array[mid] == value)
			return mid;

		if (array[mid] > value)
			last = mid-1;
		else
			first = mid+1;

	}
	return -1;
}

float calculateMean(const int array[], const int size) {
	int sum = 0;
	for (int i = 0; i < size; i++) {
		sum += array[i];
	}
	return sum/size;
}

void printInformation(const int array[], const int sortedArray[], const int size, const int searchNumber, const int searchPosition, const float mean) {
	cout << "Array size: " << size << endl;
	printArray(array, size);
	cout << "Sorted ";
	printArray(sortedArray, size);
	cout << "Number being searched: " << searchNumber << endl;
	if (searchPosition == -1) {
		cout << "The specific search item could not be found in the array." << endl;
	} else {
		cout << "Search Position: " << searchPosition << endl;
	}
	cout << "Mean of the array: " << mean << endl;
}

void printArray(const int array[], const int size) {
	cout << "Array: ";
	for (int i = 0; i < size; i++) {
		cout << array[i];
		if (i < size-1)
		   cout << ", ";
		else
			cout << ".";
	}
	cout << endl;
}
