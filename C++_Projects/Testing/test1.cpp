#include <iostream>

using namespace std;

class test1 {
public:
    void modifyHp(int);
    // const tells the compiler that it does not modify any variables
    int getHp() const;
private:
    int hp;
};

void test1::modifyHp(int health) {
    hp += health;
}

// test1:: sets the function to be in the test1 class
int test1::getHp() const {
    return hp;
}
