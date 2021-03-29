#include <iostream>
#include "AVLTree.h"
#include "Node.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
void searchNode(Node*);

int main(int argc, char** argv) {
	int nodeKey[6] = {8, 5, 1, 3, 6, 7};
	AVLTree a;
	bool test = false;
	for (int i = 0; i < 6; i++) {
		Node * b = new Node(nodeKey[i]);
		a.insertAVL(a.getHeadNode(), b, test, *a.getHeadNode());
	}
	//searchNode(a.searchNode(6, *a.getHeadNode()));
	return 0;
}

void searchNode(Node * searchResult) {
	if (searchResult == nullptr) cout << "Key unable to be found.";
	else cout << "Key " << searchResult->getKeyValue() << " found.";
	cout << endl;
}
