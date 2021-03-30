#include "AVLTree.h"
#include <iostream>

using namespace std;

AVLTree::AVLTree() {
	headNode = nullptr;
}

void AVLTree::rotateLeft(Node & root) {
	Node * rightChild = nullptr;
	if (&root == nullptr) {
		cout << "Rotation cannot happen on an empty subtree";
		return;
	}

	if (root.getRightPointer() == nullptr) {
		cout << "An empty subtree cannot be the root.";
		return;
	}
	if (&root == headNode) {
		rightChild = headNode->getRightPointer();
		headNode->updateRight(*rightChild->getLeftPointer());
		rightChild->updateLeft(headNode);
		headNode = rightChild;
	} else {
		rightChild = root.getRightPointer();
		root.updateRight(rightChild->getLeftPointer());
		rightChild->updateLeft(root); // Right child is still 6 here
		root = *rightChild; // Sets root and the previous node to the same address
		// Right child is an endless loop at this point
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
	if (&root == nullptr) return nullptr;
	
	if (key == root.getKeyValue()) {
		return &root;
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
		case 1:
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
					root.updateBF(0);
					r->updateBF(-1);
					break;
				case 0:
					root.updateBF(0);
					r->updateBF(0);
					break;
				case 1:
					root.updateBF(1);
					r->updateBF(0);
					break;
			}
			w->updateBF(0);
			rotateLeft(*r); // Endless loop created here
			root.updateLeft(*r);// Erasing 6 right here?
			rotateRight(root);
			treeRoot.updateLeft(root);
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

bool AVLTree::_clearTree(Node * root) {
	if (root == nullptr) return false;
	
	if (_clearTree(root->getLeftPointer())) root->updateLeft(nullptr);
	if (_clearTree(root->getRightPointer())) root->updateRight(nullptr);
	delete root;
	return true;
}

void AVLTree::clearTree() {
	_clearTree(headNode);
	headNode = nullptr;
}

Node * AVLTree::nodeDelete(int key, Node * root) { // root = pointer
	if (root == nullptr) return root;
	
	if (key < root->getKeyValue()) {
		root->updateLeft(nodeDelete(key, root->getLeftPointer()));
		switch (root->getBF()) {
			case 1: {
				root->updateBF(0);
				break;
			}
			case 0: {
				root->updateBF(-1);
				break;
			}
			case -1: {
				bool tall = false;
				rightBalance(*root, tall, *root);
				break;
			}
		}
	} else if (key > root->getKeyValue()) {
		root->updateRight(nodeDelete(key, root->getRightPointer()));
		switch (root->getBF()) {
			case 1: {
				bool tall = false;
				leftBalance(*root, tall, *root);
				break;
			}
			case 0: {
				root->updateBF(1);
				break;
			}
			case -1: {
				root->updateBF(0);
				break;
			}
		}
	}
	else {
		if (root->getLeftPointer() != nullptr && root->getRightPointer() != nullptr) {
			Node * temp = immedeatePred(root->getRightPointer());
			root->updateKey(temp->getKeyValue());
			root->updateRight(nodeDelete(temp->getKeyValue(),root->getRightPointer()));
		} else {
			bool left = false;
			Node * temp = nullptr;
			if (root->getLeftPointer() != nullptr) {
				temp = root->getLeftPointer();
				left = true;
			}
			else temp = root->getRightPointer();
			
			if (temp == nullptr) { // If it is root/leaf (fails)
				temp = root;
				root = nullptr;
			} else {
				root->updateKey(temp->getKeyValue());
			}
			delete temp;
		}
		return root;
	}
}

Node * AVLTree::immedeatePred(Node * root) { // Assume it is already on the right path
	if (root->getLeftPointer() == nullptr) {
		return root; // In case there is no more left to go
	}
	
	return immedeatePred(root->getLeftPointer());
	
}

Node * AVLTree::getHeadNode() {
	return headNode;
}

void AVLTree::display(Node & root, int level) { // Problem
	if (!root.hasChildren()) {
		cout << root.getKeyValue() << "(level: " << level << ") ";
		return;
	}

	if (root.getLeftPointer() != nullptr) {
	    display(*root.getLeftPointer(), level+1);
	}

	cout << root.getKeyValue() << "(level: " << level << ") ";

	if (root.getRightPointer() != nullptr) display(*root.getRightPointer(), level+1);
}
