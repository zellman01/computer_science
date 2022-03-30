/**
 * This program calculates and puts in ascending order pitchers based on their ERA from a data file.
 * Author: Zachary Wellman
 * Date of last modification: 4/19/2020
*/

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>

using namespace std;

void Swap(int*, int*);
void sort(float era[], int displayOrder[], char names[][30], float innings[], int lines);
void displayPlayers(float era[], int displayOrder[], char names[][30], float innings[], int lines);

int main() {
    char names[50][30];
    float innings[50];
    float era[50];

    int displayOrder[50];

    ifstream myFile;

    do {
        char name[70];
        cout << "What is the name and extension of the file being used: ";
        cin >> name;
        myFile.open(name);
    } while (myFile.fail());

    cout << endl;

    int lines = 0;
    char c;

    for (int i = 0; i < sizeof(displayOrder)/sizeof(*displayOrder); i++) {
        displayOrder[i] = i;
    }

    while (myFile.get(c)) {
        if (c == '\n')
            myFile.get(c);

        myFile.getline(names[lines], 30);
        int pos = 0;
        for (int i = 0; i < 30; i++) {
            if (names[lines][i] == '\n') {
                pos = i;
                break;
            }
        }
        for (; pos >= 0; pos--) {
            names[lines][pos+1] = names[lines][pos];
        }
        names[lines][0] = c;

        double er = 0.0;
        int erRuns = 0;
        int num = 0;
        innings[lines] = 0;
        era[lines] = 0;
        while (myFile.peek() != '\n' && myFile >> er) {
            innings[lines] += er;
            myFile >> num;
            erRuns += num;
        }
        era[lines] = (erRuns/innings[lines])*9;
        lines++;
    }
    myFile.close();
    sort(era, displayOrder, names, innings, lines);

    return 0;
}

void Swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void sort(float era[], int displayOrder[], char names[][30], float innings[], int lines) { // Unused parameters here are used in the last line of this function
    int k = 0;
    int indexOfMin = 0;
    int pass = 0;
    for (; pass < lines; pass++) {
        indexOfMin = pass;

        for (k = pass; k < lines; k++) {
            if (era[displayOrder[k]] < era[displayOrder[indexOfMin]]) {
                indexOfMin = k;
            }
            Swap(&displayOrder[k], &displayOrder[indexOfMin]);
        }
    }
    Swap(&displayOrder[0], &displayOrder[1]);
    displayPlayers(era, displayOrder, names, innings, lines);
}

void displayPlayers(float era[], int displayOrder[], char names[][30], float innings[], int lines) {
    cout << fixed << setprecision(2);
    for (int i = 0; i < lines; i++) {
        cout << names[displayOrder[i]] << setw(15) << innings[displayOrder[i]] << setw(8) << era[displayOrder[i]] << endl;
    }
}
