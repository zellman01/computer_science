#include <iostream>
#include <cmath>
#include "Queue.h"
#include "Airplane.h"

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char** argv) {
	Queue a(6);
	Airplane b;
	Airplane c;
	a.list();
	std::cout << std::endl;
	a.insertNode(b, 1);
	a.insertNode(c, 2);
	a.list();
	std::cout << std::endl;
	a.deleteNode();
	a.list();
	a.deleteNode();
	std::cout << std::endl;
	a.list();
	return 0;
}

int PoissonRandom(float exValue) {
	// srand needs to be seeded already
	//Uses rank()
	float limit = 0.0F;
	int count = 0;
	float max = 0.0F;
	float product = 0.0F;
	max = 6 + 1.0; // 4 is a constant given the max value rand will return
	limit = exp(-exValue);
	product = rand()/max; // 0 <= product < 1
	while (product > limit) {
		count++;
		product *= rand()/max;
	}
	return count;
}
