/*Name: Zachary Wellman
 * File Name: prog3.cpp
 * Date: 29 September, 2020
 * Program Description: Converts HTML to text
*/

#include <iostream>
#include <fstream>
using namespace std;

int readHtmlFile(char[], char[]);
int convertHtmlFile(char[], char[], int);
void outputTxtFile(ostream&, char[], int);

int main() { // Dynamically
	char * htmlArray = nullptr; char * textArray = nullptr; char * inputFile = nullptr; char * outputFile = nullptr;
	htmlArray = new char[600], textArray = new char[600], inputFile = new char[20], outputFile = new char[20];
	int choice = 0;
	
	cout << "1) Display TXT to Console" << endl << "2) Write TXT fo file" << endl << "Enter your choice: ";
	cin >> choice;
	
	cout << "Enter html filename: ";
	cin >> inputFile;
	
	int htmlSize = readHtmlFile(inputFile, htmlArray);
	int textSize = convertHtmlFile(htmlArray, textArray, htmlSize);
	
	if (choice == 2) {
		cout << "Enter txt filename: ";
		cin >> outputFile;
		ofstream output(outputFile);
		outputTxtFile(output, textArray, textSize);
		cout << "Output writted to txt file." << endl;
	} else
		outputTxtFile(std::cout, textArray, textSize);
		
	delete [] htmlArray; delete [] textArray; delete [] inputFile; delete [] outputFile;
	return 0;
}

/***********************************************************
* Name: readHtmlFile
* Description: Reads the information of a file, and then applies it to a character array
* Input: fileName, the name of the file. inputArray, the array that the contents of the file will go into.
* Output: The size of the inputArray
**********************************************************/
int readHtmlFile(char fileName[], char inputArray[]) {
	ifstream readFile(fileName);

	char c = ' ';
	int pos = 0;
	while (readFile.get(c)) {
		inputArray[pos] = c;
		pos++;
	}
	return pos;
}

/***********************************************************
* Name: convertHemlFile
* Description: Converts the HTMl file into plain text, without the html tags
* Input: htmlInput, the HTML input. conversion, the output of the plaintext. numChars, the amount of characters in the htmlInput
* Output: The number of characters in the conversion array
**********************************************************/
int convertHtmlFile(char htmlInput[], char conversion[], int numChars) {
	int pos = 0;
	for (int i = 0; i < numChars; i++) {
		bool brFound = false;
		while (htmlInput[i] == '<') {
			  if (htmlInput[i+1] == 'b' && htmlInput[i+2] == 'r') {
			  	htmlInput[i] = '\n';
			  	brFound = true;
			  }
			  while(htmlInput[i] != '>') {
			  i++;
			  }
			  i++;
		}
		if (i < numChars) {
			if (htmlInput[i] == '\n' && !brFound) {
			} else {
				conversion[pos] = htmlInput[i];
				pos++;
			}
		}
	}
	return pos;
}

/***********************************************************
* Name: outputTxtFile
* Description: Outputs the converted character array
* Input: outFile, the stream to print it out to. textOutput, the converted HTML text. size, the size of the converted arrray.
* Output: The size of the inputArray
**********************************************************/
void outputTxtFile(ostream& outFile, char textOutput[], int size) {
	outFile << textOutput;
}










