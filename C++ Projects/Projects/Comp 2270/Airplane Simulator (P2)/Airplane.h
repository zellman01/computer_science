#ifndef AIRPLANE_H
#define AIRPLANE_H

class Airplane
{
	public:
		Airplane(int, int);
		Airplane();
		void update(bool);
		int fuel();
		int waited();
		bool isCrashed();
		int planeNum();
	private:
		int fuelLeft, timeWaiting, airplaneNumber;
		bool crashed;
};

#endif
