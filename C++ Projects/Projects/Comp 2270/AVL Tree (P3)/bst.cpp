#include "bst.h"
#include <iostream>

using namespace std;

BinarySearchTree::BinarySearchTree() {
	headNode = nullptr;
}

BinarySearchTree::~BinarySearchTree() {
	delete headNode;
}

// Update BFs and pass a taller boolean passed by address into this
void BinarySearchTree::insertNode(Node & node, Node & treeRoot) {
    if (getRootNode() == nullptr) { // In case the root of the tree is non-existant, put a node as the tree root
        headNode = &node;
        return;
    }
    
	if (node.getKeyValue()<treeRoot.getKeyValue()) {
    	if (treeRoot.getLeftPointer() == nullptr) {
    		treeRoot.updateLeft(node);
    		return; // To ensure that the function leaves
		} else {
			insertNode(node, *treeRoot.getLeftPointer());
		}
	}
	
	if (node.getKeyValue()>treeRoot.getKeyValue()) {
		if (treeRoot.getRightPointer() == nullptr) {
			treeRoot.updateRight(node);
			return; // To ensure that the function leavesz
		} else {
			insertNode(node, *treeRoot.getRightPointer());
		}
	}
	//display(*getRootNode());
}

// Returns the root of the new subtree
Node * BinarySearchTree::deleteNode(int key, Node * root) {
	if (root == nullptr) {
		return root;
	}
	if (key < root->getKeyValue()) root->updateLeft(*deleteNode(key, root->getLeftPointer()));
	else if (key > root->getKeyValue()) root->updateRight(*deleteNode(key, root->getRightPointer()));
	else {
		if (root->getLeftPointer() == nullptr) {
			Node * temp = root->getRightPointer();
			delete root;
			return temp;
		} else if (root->getRightPointer() == nullptr) {
			Node * temp = root->getLeftPointer();
			delete root;
			return temp;
		}
		
		Node * temp = getPred(*root);
		root->updateKey(temp->getKeyValue());
		root->updateRight(*deleteNode(temp->getKeyValue(), root->getRightPointer()));
	}
	//display(*getRootNode());
}

Node * BinarySearchTree::getRootNode() {
	return headNode;
}

// Copy over to AVL tree
Node * BinarySearchTree::searchNode(int key, Node & root) {
	if (key == root.getKeyValue()) {
		return &root;
	}
	
	if (root.getLeftPointer() == nullptr && root.getRightPointer() == nullptr) {
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

Node * BinarySearchTree::getPred(Node & root) {
	if (root.getRightPointer() == nullptr) return nullptr;
	Node * roota = root.getRightPointer();
	while (roota->getLeftPointer() != nullptr) roota = roota->getLeftPointer();
	return roota;
}

// Needs to display the height of the tree with each node (copy over to AVL tree)
void BinarySearchTree::display(Node & root) {
	if (!root.hasChildren()) {
		cout << root.getKeyValue() << " ";
		return;
	}
	
	if (root.getLeftPointer() != nullptr) display(*root.getLeftPointer());
	
	cout << root.getKeyValue() << " ";
	
	if (root.getRightPointer() != nullptr) display(*root.getRightPointer());
}
