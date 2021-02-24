#include <iostream>
using namespace std;

// int g_array[25];

int main(){
    const int SIZEA = 5;
    int array[SIZEA];

    for (int i = 0; i < SIZEA; i++) {
        array[i]=i+2;
    }

    for (int i = 0; i < SIZEA; i++) {
        cout << array[i] << "\t";
    }

    cout << endl << array[2] + array[4];
}
