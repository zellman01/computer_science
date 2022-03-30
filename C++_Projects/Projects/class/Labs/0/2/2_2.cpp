// Zachary Wellman
// 2D Array
#include <iostream>
using namespace std;

void printArray(int[][5], int);

int main() {
	int table[5][5] = {0};
	int total = 25;
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			table[i][j] = total;
			total--;
		}
	}
	printArray(table, 5);
}

void printArray(int array[][5], int size) {
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < 5; j++) {
			if (array[i][j] != 13) {
				if (array[i][j] > 9){
				   cout << array[i][j] << " ";
				} else
				  cout << 0 << array[i][j] << " ";
			} else
				cout << "   ";
		}
		cout << endl;
	}
}
