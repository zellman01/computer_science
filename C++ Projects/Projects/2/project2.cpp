/**
 * This is a guessing game of two numbers.
 * Author: Zachary Wellman
 * Date: 4/1/2020
*/

#include <iostream>
#include <string.h>
#include <stdlib.h>
#include <time.h>

using namespace std;

bool continueGame = true;
int guessesRequired = 0;

void game(int, int); // Num 1, Num 2
bool testAnswer(int, int, int, int); // Num 1, Num 2, Guess 1, Guess 2

int main() {
    srand(unsigned(time(NULL)));
    do {
        int numOne = rand() % 1000 + 1;
        int numTwo = rand() % 1000 + 1;
        game(numOne, numTwo);
    } while (continueGame);
    cout << "Guesses required: " << guessesRequired << endl;
    return 0;
}

void game(int one, int two) {
    int testOne, testTwo;
    bool answer, test;
    do {
        guessesRequired++;
        do {
            cout << "What is your guess for the first number (between 1 and 1000)? ";
            cin >> testOne;
        } while (testOne < 1 || testOne > 1000);
        do {
            cout << "What is your guess for the second number (between 1 and 1000)? ";
            cin >> testTwo;
        } while (testTwo < 1 || testTwo > 1000);
        answer = testAnswer(one, two, testOne, testTwo);
    } while (!answer);
    do {
        cout << "Would you like to play again (1 for yes, 0 for no)? ";
        cin >> test;
    } while (test != true && test != false);
    continueGame = test;
    if (test)
        cout << endl;
}

bool testAnswer(int one, int two, int gOne, int gTwo) {
    int higher = 0, lower = 0, correct = 0;
    if (one == gOne && two == gTwo) {
        cout << "Congratulations! You guessed them both!" << endl << endl;
        return true;
    }

    if (one < gOne)
        higher++;
    else if (one > gOne)
        lower++;
    else if (one == gOne)
        correct++;

    if (two < gTwo)
        higher++;
    else if (two > gTwo)
        lower++;
    else if (two == gTwo)
        correct++;

    if (lower == 2)
        cout << "Sorry, both of your guesses are too low." << endl;
    else if (higher == 2)
        cout << "Sorry, both of your guesses are too high." << endl;
    else if (higher == 1 && lower == 1)
        cout << "One of your guesses is too high and the other is too low." << endl;
    else if (correct == 1 && higher == 1)
        cout << "One of your guesses is correct, but the other is too high." << endl;
    else if (correct == 1 && lower == 1)
        cout << "One of your guesses is correct, but the other is too low." << endl;
    return false;
}
