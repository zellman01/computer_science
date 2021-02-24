#include <iostream>
using namespace std;

const int TOTAL = 50;

void divides(int*, int*, int&);

int main() {
	int * m = nullptr, * n = nullptr;
	cout << "Input m and n where m|n:" << endl;
	m = new int; n = new int;
	cin >> *m >> *n;
	int k = 0;
	divides(m, n, k);
	if (k <= TOTAL)
	cout << "k is " << k << endl;
	else
	cout << "Either the number does not exist, or it is larger than " << TOTAL;
	delete m; delete n; delete k;
	return 0;
}

void divides (int * m, int * n, int &k) {
	for (int i = 0; i < TOTAL && k == 0; i++) {
		if (*m*i == *n) {
			k = i;
		}
	}
	k=  TOTAL+1;
}
