//Zachary Wellman

#include <iostream>

using namespace std;
void print(int[], int);

int main() {
    int array[10];

    for (int i = 0; i < 10; i++)
        array[i] = i;

    int arraya[20] = {0};

    for (int i = 0; i < 10; i++)
        arraya[i] = array[i];

    print(arraya, 20);
}

void print(int value[], int size) {
    for (int i = 0; i < size; i++)
        cout << value[i] << "\t";
}
