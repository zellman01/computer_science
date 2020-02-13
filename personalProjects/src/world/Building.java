package world;

public class Building {
	private int floors;
	private boolean[] restrictedFloors, publicAccess, elevatorAccess;
	private boolean elevator, restricted;
	private String name;
	
	public Building(String buildingName, int floorCount, boolean hasElevator) {
		name = buildingName;
		floors = floorCount;
		elevator = hasElevator;
		restrictedFloors = new boolean[floors];
		publicAccess = new boolean[floors];
		elevatorAccess = new boolean[floors];
		restricted = false;
	}
	
	public Building(String buildingName, int floorCount, boolean hasElevator, boolean isRestricted) {
		name = buildingName;
		floors = floorCount;
		elevator = hasElevator;
		restrictedFloors = new boolean[floors];
		publicAccess = new boolean[floors];
		elevatorAccess = new boolean[floors];
		restricted = isRestricted;
	}
	
	public void restrictedBuilding() { restricted = !restricted; }
	
	public void allRestricted() {
		for (int i = 0; i < floors; i++) {
			restrictFloor(i);
		}
	}
	
	public void restrictFloor(int floorNum) {
		if (floorNum > floors)
			throw new Error("Floor number " + floorNum + " does not exist on " + buildingName() + ".");
		if (!restricted) {
			throw new Error("The building " + buildingName() + " is not restricted.");
		}
		
		restrictedFloors[floorNum] = true;
		publicAccess[floorNum] = false;
	}
	
	public void restrictFloors(int[] floorNums) {
		for (int i = 0; i < floorNums.length; i++) {
			restrictFloor(floorNums[i]);
		}
	}
	
	public void elevatorAccess(int floorNum) {
		if (floorNum > floors)
			throw new Error("Floor number " + floorNum + " does not exist on " + buildingName() + ".");
		if (!elevator) 
			throw new Error("There is no elevator in " + buildingName() + ".");
		
		elevatorAccess[floorNum] = true;
	}
	
	public void elevatorAccessFloors(int[] floorNums) {
		for (int i = 0; i < floorNums.length; i++) {
			elevatorAccess(floorNums[i]);
		}
	}
	
	public boolean isRestricted() { return this.restricted; }
	public int floorCount() { return this.floors; }
	public boolean[] restrictedFloors() { return this.restrictedFloors; }
	public boolean hasElevator() { return this.elevator; }
	public boolean[] publicAccessFloors() { return this.publicAccess; }
	public boolean[] elevatorAccessFloors() { return this.elevatorAccess; }
	public String buildingName() { return this.name; }
	
	public String toString() {
		return "Not constructed yet";
	}
}
