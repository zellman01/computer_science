#include "bst.h"
#include <iostream>

BinarySearchTree::BinarySearchTree() {
	headNode = nullptr;
}

BinarySearchTree::~BinarySearchTree() {
	delete headNode;
}

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
}

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
}

Node * BinarySearchTree::getRootNode() {
	return headNode;
}

Node * BinarySearchTree::searchNode(int key, Node & root) {
	if (key == root.getKeyValue()) {
		return &root;
	}
	
	if (root.getLeftPointer() == nullptr && root.getRightPointer() == nullptr) {
		return nullptr; // If not able to be found
	}
	
	if (key<root.getKeyValue()) {
		return searchNode(key,*root.getLeftPointer());
	}
	
	if (key>root.getKeyValue()) {
		return searchNode(key,*root.getRightPointer());
	}
}

Node * BinarySearchTree::getPred(Node & root) {
	if (root.getRightPointer() == nullptr) return nullptr;
	Node * roota = root.getRightPointer();
	while (roota->getLeftPointer() != nullptr) roota = roota->getLeftPointer();
	return roota;
	
}
