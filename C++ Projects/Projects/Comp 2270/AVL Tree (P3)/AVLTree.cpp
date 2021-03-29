#include "AVLTree.h"
#include <iostream>

using namespace std;

AVLTree::AVLTree() {
	headNode = nullptr;
}

void AVLTree::rotateLeft(Node & root) {
	Node * rightChild = nullptr;
	Node * rootTest = &root; // To check for null
	if (rootTest == nullptr) {
		cout << "Rotation cannot happen on an empty subtree";
		return;
	}

	if (root.getRightPointer() == nullptr) {
		cout << "An empty subtree cannot be the root.";
		return;
	}
	if (rootTest == headNode) {
		rightChild = headNode->getRightPointer();
		headNode->updateRight(*rightChild->getLeftPointer());
		rightChild->updateLeft(headNode);
		headNode = rightChild;
	} else {
		rightChild = root.getRightPointer();
		root.updateRight(rightChild->getLeftPointer());
		rightChild->updateLeft(root);
		root = *rightChild; // Sets root and the previous node to the same address
	}
}

void AVLTree::rotateRight(Node & root) {
	Node * leftChild = nullptr;
	if (&root == nullptr) {
		cout << "Rotation cannot happen on an empty subtree";
		return;
	}

	if (root.getLeftPointer() == nullptr) {
		cout << "An empty subtree cannot be the root.";
		return;
	}

	if (&root == headNode) {
		leftChild = headNode->getLeftPointer();
		headNode->updateLeft(*leftChild->getRightPointer());
		leftChild->updateRight(headNode);
		headNode = leftChild;
	} else {
		leftChild = root.getLeftPointer();
		root.updateLeft(*leftChild->getRightPointer());
		leftChild->updateRight(root);
		root = *leftChild;
	}
}

Node * AVLTree::searchNode(int key, Node & root) {
	if (key == root.getKeyValue()) {
		return &root;
	}

	if (&root == nullptr) {	//root.getLeftPointer() == nullptr && root.getRightPointer() == nullptr) {
		return nullptr; // If not able to be found
	}

	if (key<root.getKeyValue()) {
		cout << "Key checked: " << root.getKeyValue() << endl;
		return searchNode(key,*root.getLeftPointer());
	}

	if (key>root.getKeyValue()) {
		cout << "Key checked: " << root.getKeyValue() << endl;
		return searchNode(key,*root.getRightPointer());
	}
}

// Assume to be working
void AVLTree::rightBalance(Node & root, bool & taller, Node & treeRoot) {
	Node * r, * w; // If * does not work, change to &
	r = root.getRightPointer();
	switch(r->getBF()) {
		case -1:
			root.updateBF(0);
			r->updateBF(0);
			rotateLeft(root);
			taller = false;
			break;
		case 0:
			cout << "Impossible case for right balance";
			break;
		case 1: // Update BF numbers
			w = r->getLeftPointer();
			switch (w->getBF()) {
				case -1:
					root.updateBF(1);
					r->updateBF(0);
					break;
				case 0:
					root.updateBF(0);
					r->updateBF(0);
					break;
				case 1:
					root.updateBF(0);
					r->updateBF(-1);
					break;
			}
			w->updateBF(0);
			rotateRight(*r);
			root.updateRight(*r);
			rotateLeft(root);
			if (&treeRoot != headNode) treeRoot.updateLeft(root);
			taller = false;
			break;
	}
}

// Assume working
void AVLTree::leftBalance(Node & root, bool & taller, Node & treeRoot) {
	Node * r, * w;
	r = root.getLeftPointer();
	switch(r->getBF()) {
		case 1:
			root.updateBF(0);
			r->updateBF(0);
			rotateRight(root);
			taller = false;
			break;
		case 0:
			cout << "Impossible case for left balance";
			break;
		case -1:
			w = r->getRightPointer();
			switch (w->getBF()) {
				case -1:
					root.updateBF(1);
					r->updateBF(0);
					break;
				case 0:
					root.updateBF(0);
					r->updateBF(0);
					break;
				case 1:
					root.updateBF(0);
					r->updateBF(-1);
					break;
			}
			w->updateBF(0);
			rotateLeft(*r);
			root.updateLeft(*r);
			cout << "Root: " << root.getKeyValue() << endl << "Left: " << root.getLeftPointer()->getKeyValue() << endl << "Left left: " << root.getLeftPointer()->getLeftPointer()->getKeyValue() << endl;
			rotateRight(root);
			if (&treeRoot != headNode) treeRoot.updateRight(root);
			taller = false;
			break;
	}
}

void AVLTree::insertAVL(Node * root, Node * newNode, bool & taller, Node & treeRoot) { // treeRoot is the root of the subtree. root is the current node
	if (headNode == nullptr) {
		headNode = newNode;
		headNode->updateBF(0);
		taller = true;
		return;
	}
	bool tallerSubtree = false;
	if (root == nullptr) {
		root = newNode;
		root->updateBF(0);
		if (root->getKeyValue() < treeRoot.getKeyValue()) treeRoot.updateLeft(root);
		else treeRoot.updateRight(root);
		taller = true;
		return;
	}

	if (newNode->getKeyValue() < root->getKeyValue()) {
		insertAVL(root->getLeftPointer(), newNode, tallerSubtree, *root);
		if (tallerSubtree) {
			switch (root->getBF()) {
				case 1:
					leftBalance(*root, tallerSubtree, treeRoot);
					break;
				case 0:
					root->updateBF(1);
					taller = true;
					break;
				case -1:
					root->updateBF(0);
					taller = false;
					break;
			}
		} else taller = false;
	} else if (newNode->getKeyValue() > root->getKeyValue()) {
		insertAVL(root->getRightPointer(), newNode, tallerSubtree, *root);
		if (tallerSubtree) {
			switch (root->getBF()) {
				case 1:
					root->updateBF(0);
					taller = false;
					break;
				case 0:
					root->updateBF(-1);
					taller = true;
					break;
				case -1:
					rightBalance(*root, tallerSubtree, treeRoot);
					break;
			}
		} else taller = false;
	}
}

Node * AVLTree::getHeadNode() {
	return headNode;
}

void AVLTree::display(Node & root) { // Problem
	/*if (!root.hasChildren()) {
		cout << endl << root.getKeyValue() << " ";
		return;
	}

	if (root.getLeftPointer() != nullptr) {
	    display(*root.getLeftPointer());
	}

	cout << root.getKeyValue() << " ";

	if (&root.getRightPointer() != nullptr) display(*root.getRightPointer());*/
	cout << endl << endl << endl << root.hasChildren();
}
