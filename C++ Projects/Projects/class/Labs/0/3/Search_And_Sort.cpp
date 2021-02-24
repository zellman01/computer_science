// Zachary Wellman

#include<iostream>
using namespace std;

int search(const int[], int, int); // Searches the array for a given value (3rd parameter)
void selectionSort(int[], int); // Sorts the given array (uses swap as a dependency)
void swap(int&, int&); // Swap two values
void printArray(const int[], int); // Print array

int main() {
	const int SIZE = 10;
	int array[] = {19, 6, 12, 2, 11, 4, 14, 10, 1, 8};
	printArray(array, SIZE); // Print initial array
	selectionSort(array, SIZE);
	cout << search(array, SIZE, 8) << endl; // Print search result for the number 8 of the sorted int array
	printArray(array, SIZE); // Print modified array
}

int search(const int array[], int size, int value) {
	bool found = false;
	int start = 0,
		last = size-1,
		mid,
		index = -1;
		
	while (!found && start <= last) {
		mid = (start+last)/2;
		if (array[mid] == value) {
			index = mid;
			found = true;
		} else if (array[mid] > value) {
			last = mid - 1;
		} else {
			start = mid + 1;
		}
	}
	return index;
}

void selectionSort(int array[], int size) {
	int maxIndex, maxValue;
	
	for (int start = size-1; start >= 0; start--) {
		maxIndex = start;
		maxValue = array[start];
		for (int index = start-1; index >= 0; index--) {
			if (array[index] > maxValue) {
				maxValue = array[index];
				maxIndex = index;
			}
		}
		swap(array[maxIndex], array[start]);
	}
}

void swap(int& a, int& b) {
	int temp = a;
	a = b;
	b = temp;
}

void printArray(const int array[], int size) {
	for (int i = 0; i < size; i++) {
		cout << array[i] << " ";
	}
	cout << endl;
}
