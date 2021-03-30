#include <iostream>
#include <fstream>
#include <string>
#include "AVLTree.h"
#include "Node.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
void searchNode(Node*);
void addData(AVLTree&, string);
void insertData(AVLTree&, int);

int main(int argc, char** argv) {
	int nodeKey[5] = {8, 5, 1, 3, 6};//, 7};
	AVLTree a;
	bool test = false;
	for (int i = 0; i < 5; i++) {
		Node * b = new Node(nodeKey[i]);
		a.insertAVL(a.getHeadNode(), b, test, *a.getHeadNode());
		a.display(*a.getHeadNode());
		cout << endl;
	}
	//searchNode(a.searchNode(7, *a.getHeadNode()));
	a.clearTree();
	return 0;
}

void searchNode(Node * searchResult) {
	if (searchResult == nullptr) cout << "Key unable to be found.";
	else cout << "Key " << searchResult->getKeyValue() << " found.";
	cout << endl;
}

void addData(AVLTree & avl, string fileName) {
	ifstream test(fileName);
	if (test) {
		int info = 0;
		string input;
		while(getline(test,input)) {
			info = stoi(input);
			insertData(avl, info);
		}
	} else {
		cout << "File could not be found." << endl;
	}
}

void insertData(AVLTree & avl, int info) {
	Node * b = new Node(info);
	bool fake = false; // Needed to start the function
	string abcd; // Only used for waiting for the user to press enter to advance
	avl.insertAVL(avl.getHeadNode(), b, fake, *avl.getHeadNode());
	avl.display(*avl.getHeadNode());
	cout << endl << "Press enter to display the next stage of the growth" << endl;
	cin.getline(abcd,1);
}
