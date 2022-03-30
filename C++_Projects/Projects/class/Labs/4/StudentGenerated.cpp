// Option 3, student generated code

// Zachary Wellman

#include <iostream>
using namespace std;

double yearSum(double*, const int);
double average(double*, const int);

int main() {
	int size;
	double * sales = nullptr;

	do {
		cout << "Please input the number of monthly sales to be input" << endl;
		cin >> size;
	} while (size <= 0);
	sales = new double[size];
	
	for (int pos = 0; pos < size; pos++) {
		cout << "Please input the sales for month " << pos+1 << endl;
		cin >> *(sales+pos);
	}
	
	cout << "The total sales for the year is $" << yearSum(sales, size) << endl;
	cout << "The average monthly sale is $" << average(sales, size) << endl;
	
	delete [] sales;
	
	return 0;
}

double yearSum(double * array, const int size) {
	double sum = 0.0;
	for (int i = 0; i < size; i++) {
		sum += *(array+i);
	}
	return sum;
}

double average(double * array, const int size) {
	return yearSum(array, size)/size;
}
