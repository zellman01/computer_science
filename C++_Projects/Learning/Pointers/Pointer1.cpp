#include <iostream>

using namespace std;

int main() {
    char letter; short number; float amount;

    amount = 500;

    short* ptr = &number; // Creates a pointer to the variable "number"

    cout << &amount << endl; // Address
    cout << sizeof(amount) << endl; // How big it is
    cout << amount << endl; // What it is

    return 0;
}
