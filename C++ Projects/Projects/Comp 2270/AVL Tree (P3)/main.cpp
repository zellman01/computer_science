#include <iostream>
#include <fstream>
#include <string>
#include "AVLTree.h"
#include "Node.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
void searchNode(Node*);
void addData(AVLTree&, string); // Adds data from a given file into the tree
void insertData(AVLTree&, int); // Adds a single int into the tree (used with addData)
void menuOptions();

int main(int argc, char** argv) {
	AVLTree a;
	int menuOption = 0;
	while (menuOption != 6) {
		menuOptions();
		cin >> menuOption;
		switch (menuOption) {
			case 1: {
				string fileName = "";
				cout << "Please enter the file name of the data you want to insert: ";
				cin >> fileName;
				cin.ignore();
				addData(a, fileName);
				break;
			}
			case 2: {
				int key = 0;
				cout << "What is the key value you want to insert into the tree?: ";
				cin >> key;
				cin.ignore();
				insertData(a, key);
				break;
			}
			case 3: {
				int key = 0;
				cout << "What is the value of the key you would like to delete?: ";
				cin >> key;
				cin.ignore();
				a.nodeDelete(key, a.getHeadNode());
				break;
			}
			case 4: {
				int key = 0;
				cout << "What key value would you like to search for?: ";
				cin >> key;
				cin.ignore();
				searchNode(a.searchNode(key, *a.getHeadNode()));
				break;
			}
			case 5:
				a.clearTree();
				cout << "Tree cleared." << endl;
			case 6:
				break;
			default:
				cout << "That is not a reconized menu option number." << endl;
				break;
		}
		cout << endl;
	}
	return 0;
}

void searchNode(Node * searchResult) {
	if (searchResult != nullptr) cout << "Key " << searchResult->getKeyValue() << " found.";
	else cout << "Key unable to be found.";
	cout << endl;
}

void addData(AVLTree & avl, string fileName) {
	ifstream test(fileName);
	if (test) {
		int info = 0;
		string input, abcd; // abcd only used for waiting for input
		while(getline(test,input)) {
			info = stoi(input);
			insertData(avl, info);
			cout << endl << "Press enter to display the next stage of the growth" << endl;
			getline(cin,abcd);
		}
		test.close();
	} else {
		cout << "File could not be found." << endl;
	}
}

void insertData(AVLTree & avl, int info) {
	Node * b = new Node(info);
	bool fake = false; // Needed to start the function
	avl.insertAVL(avl.getHeadNode(), b, fake, *avl.getHeadNode());
	avl.display(*avl.getHeadNode());
}

void menuOptions() {
	cout << "Options:" << endl;
	cout << "1: Build a tree from a given file" << endl;
	cout << "2: Insert a key into the tree" << endl;
	cout << "3: Delete a key from the tree" << endl;
	cout << "4: Search for a key in the tree" << endl;
	cout << "5: Clear the tree" << endl;
	cout << "6: Exit the program" << endl;
}
