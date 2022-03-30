/*Name: Zachary Wellman
 * File Name: prog1.cpp
 * Date: 25 August, 2020
 * Program Description: Prints out numbers of a checkerboard, from given start, end, and step numbers.
*/
#include <iostream>
using namespace std;

void getBoardSize(int&, int&);
void printInitialBoard(int, int);
int getStartPosition();
int getEndPosition();
int getStepSize();
void printFinalBoard(int, int, int, int, int);

int main() {
    int x, y;
    getBoardSize(x, y);
    printInitialBoard(x, y);
    int startPos = getStartPosition(), endPos = getEndPosition(), step = getStepSize();
    printFinalBoard(x, y, startPos, endPos, step);
    return 0;
}

/**********************************************************
 * Name: getBoardSize
 * Description: It sets up the board dimensions to what the user wants
 * Input: x = width of the board, y = height of the board
 * Output: No return
 **********************************************************/
void getBoardSize(int& x, int& y) {
    cout << "Enter the board size (rows columns): ";
    cin >> x >> y;
    while (x <= 0 || y <= 0) { // In case of an impossible case (like a negative length, or size too big)
        cout << "The board size cannot be negative or zero, enter again (rows columns): ";
        cin >> x >> y;
    }
}

/**********************************************************
 * Name: printInitialBoard
 * Description: Prints out the initial board of which the program uses throughout the rest of the runtime
 * Input: x = width of board, y = height of board
 * Output: No return
 **********************************************************/
void printInitialBoard(int x, int y) {
    int number = 0;
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
            if (number < 10) {
                cout << 0 << number << " ";
            } else {
                cout << number << " ";
            }
            number++;
        }
        cout << endl;
    }
}

/**********************************************************
 * Name: getStartPosition
 * Description: Retrieves the start position from user input to start modifying the board.
 * Input: No inputs
 * Output: Start position to modify board
 **********************************************************/
int getStartPosition() {
    int startTemp = 0;
    cout << "Enter the start position: ";
    cin >> startTemp;
    while(startTemp < 0) {
        cout << "The start position must be non-negative, enter again: ";
        cin >> startTemp;
    }
    return startTemp;
}

/**********************************************************
 * Name: getEndPosition
 * Description: Same as getStartPosition, but for the end of where to modify the board.
 * Input: No inputs
 * Output: End position to modify board
 **********************************************************/
int getEndPosition() {
    int endTemp = 0;
    cout << "Enter the end position: ";
    cin >> endTemp;
    while (endTemp < 0) {
        cout << "The end position must be non-negative, enter again: ";
        cin >> endTemp;
    }
    return endTemp;
}

/**********************************************************
 * Name: getStepSize
 * Description: Retrieves the amount of iterations to go through to replace with "**" before getting another number
 * Input: No input
 * Output: Step size
 **********************************************************/
int getStepSize() {
    int stepTemp = 0;
    cout << "Enter the step size: ";
    cin >> stepTemp;
    while (stepTemp <= 0) {
        cout << "The step size must be greater than zero, enter again: ";
        cin >> stepTemp;
    }
    return stepTemp;
}

/**********************************************************
 * Name: printFinalBoard
 * Description: Prints the final board, using the given height, width, start position, end position, and step amount.
 * Input: x = width, y = height, startPos = starting position, endPos = ending position, stepSize = amount of step size
 * Output: No output
 **********************************************************/
void printFinalBoard(int x, int y, int startPos, int endPos, int stepSize) {
    int number = 0, stepTemp = 0;
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
            if (stepTemp <= 0 && number >= startPos && number <= endPos) {
                if (number < 10) {
                    cout << 0 << number << " ";
                } else {
                    cout << number << " ";
                }
                stepTemp = stepSize-1;
            } else {
                cout << "** ";
                stepTemp--;
            }
            number++;
        }
        cout << endl;
    }
}
