// Zachary Wellman number 1
#include <iostream>
using namespace std;

int main(int argc, char** argv) {
	int sum = 0;
	if (argc == 1) {
		cout << "Something";
		return -1;
	}
	
	for (int i = 1; i < argc; i++) {
		sum += atoi(argv[i]);
	}
	cout << "The sum of the numbers is " << sum << endl;
	return 0;
}
