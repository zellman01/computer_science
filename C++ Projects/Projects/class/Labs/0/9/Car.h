#include <string>

#ifndef CAR_H
#define CAR_H
class Car {
	public:
		std::string getName();
		float getMass();
		float getVelocity();
		float getForce();
		void setMass(float);
		void setVelocity(float);
		void setName(std::string);
	private:
		std::string name;
		float mass, velocity;
};

#endif
