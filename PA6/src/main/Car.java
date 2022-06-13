/**
* This program constructs a Car abstract class with some methods and abstract method. This class helps programmers to implement more car classes
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 18, 2021
* COSI 12B PA6
*/

package main;

public abstract class Car {
	public int initSpeed;
	public int speed;
	public int strength;
	public double location;
	public double units;
	public boolean isDMG;
	public boolean finished;
	public boolean inPit;
	public int ticksInPit;
	public boolean exiting;
	
	/**
	 * This constructor receives two parameters representing the car's speed and strength and construct a car object
	 * @param speed int speed of the car
	 * @param strength int strength of the car
	 */
	protected Car(int speed, int strength) {
		this.speed = speed;
		this.initSpeed = speed;
		this.strength = strength;
		this.location = 0.0;
		this.units = 0.0;
		this.isDMG = false;
		this.finished = false;
		this.inPit = false;
		this.exiting = false;
	}
	
	/**
	 * This method receives two int values representing the lower and upper bound of the speed for a particular kind of the car. 
	 * By calling this method, the program examines if the the speed entered by the programmer is within the bounds of the speed for this kind of the car.
	 * If the entered speed is not within the bounds, then the method sets the car's speed to the nearest bound
	 * @param lo int the lower bound of the speed for a particular kind of car 
	 * @param hi int the upper bound of the speed for a particular kind of car
	 */
	public void checkSpeed(int lo, int hi) {
		if(this.speed > hi) {
			this.speed = hi;
			this.initSpeed = hi;
		}
		if(speed < lo) {
			this.speed = lo;
			this.initSpeed = lo;
		}
		if(strength > 4) {
			this.strength = 4;
		}
		if(strength < 2) {
			this.strength = 2;
		}
	}
	
	/**
	 * This method receives two int values representing the lower and upper bound of the strength for a particular kind of the car. 
	 * By calling this method, the program examines if the the strength entered by the programmer is within the bounds of the speed for this kind of the car.
	 * If the entered strength is not within the bounds, then the method sets the car's strength to the nearest bound
	 * @param lo int the lower bound of the speed for a particular kind of car 
	 * @param hi int the upper bound of the speed for a particular kind of car
	 */
	public void checkStrength(int lo, int hi) {
		if(strength > hi) {
			this.strength = hi;
		}
		if(strength < lo) {
			this.strength = lo;
		}
	}
	
	/**
	 * This method gets the total units the car has ran in the race. This method is inherited by all subclasses 
	 * @return double the total units the car has ran
	 */
	public double getLocation() {
		return this.location;
	}
	
	/**
	 * This method gets the speed of the car
	 * @return int the speed of the car
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * This method receives a double value and updates the total units the car has ran in the race. This method is inherited by all subclasses 
	 * @param location double the units that we want to update to the car's location
	 */
	public void setLocation(double location) {
		this.location = location;
		this.units = this.location%100;
	}
	
	/**
	 * This method gets the units the car has ran in a single lap. This method is inherited by all subclasses 
	 * @return double the unit the car is at in a single lap
	 */
	public double getUnits(){
		return this.units;
	}
	
	/**
	 * This method gets a boolean value indicating whether the car is damaged. This method is inherited by all subclasses 
	 * @return boolean value true if the car is damaged, false otherwise
	 */
	public boolean isDMG() {
		return this.isDMG;
	}
	
	/**
	 * This method receives a boolean value and updates whether the car is damaged. This method is inherited by all subclasses 
	 * @param b boolean value
	 */
	public void setDMG(boolean b) {
		if(this.isDMG == false && b == true) { //if the car was initially damaged and is updated to not damaged
			this.isDMG = b;
			this.speed = this.initSpeed-(this.strength*5);
		}
		if(this.isDMG == true && b == false) { //if the car was initially not damaged and is updated to damaged
			this.isDMG = b;
			this.speed = this.initSpeed;
		}
	}
	
	/**
	 * This method gets whether or not the car has finished the race. This method is inherited by all subclasses 
	 * @return boolean value true if the car is finished, false otherwise
	 */
	public boolean getFinished() {
		return this.finished;
	}
	
	/**
	 * This method updates the status of the car in the game. This method is inherited by all subclasses 
	 */
	public void setFinished() {
		this.finished = true;
	}
	
	/**
	 * This method gets whether or not the car is in the pit. This method is inherited by all subclasses 
	 * @return boolean value true if the car is in the pit, false otherwise
	 */
	public boolean inPit() {
		return this.inPit;
	}
	
	/**
	 * This method gets the remaining ticks that the car has to wait in the pit. This method is inherited by all subclasses 
	 * @return int value the number of ticks that the car has to wait in the pit
	 */
	public int getTicksInPit() {
		return this.ticksInPit;
	}
	
	/**
	 * This method reduces the number of the ticks that the car has to wait in the pit by one. This method is inherited by all subclasses 
	 */
	public void setTicksInPit() {
		this.ticksInPit -= 1;
	}
	
	/**
	 * This method receives a boolean value and a int value and updates the status of the car waiting in the pit. This method is inherited by all subclasses 
	 * @param b boolean value indicating whether the car is supposed to be in the pit
	 */
	public void setPit(boolean b, int n) {
		if(b == true) {
			this.ticksInPit = n;
			this.inPit = true;
		} else {
			this.ticksInPit = n;
			this.inPit = false;
		}
	}
	
	/**
	 * This method gets whether the car is exiting the pit, in order to avoid collisions between the cars exiting the pits. This method is inherited by all subclasses 
	 * @return boolean value true if the car is exiting the pit this tick, false otherwise
	 */
	public boolean ifExit(){
		return this.exiting;
	}
	
	/**
	 * This method sets whether the car is exiting the pit. This method is inherited by all subclasses 
	 * @param b boolean value the value that we want to update to the car's status with the pit. 
	 */
	public void setExit(boolean b){
		this.exiting = b;
	}

	/**
	 * This abstract method gives the score for a particular kind of car entering the race. The implementation of this method will be provided in all the subclasses 
	 * @return int the score of a car of a particular kind entering the race
	 */
	public abstract int getScore();
	
	/**
	 * This abstract method gives the string representation for a particular kind of car. The implementation of this method will be provided in all the subclasses 
	 * @return String the string representation of a car
	 */
	public abstract String toString();
}
