/**
* This program constructs a RaceCar class which inherits and overrides methods from the Car class. This class also implements the toString() and getScore() abstract methods.
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 18, 2021
* COSI 12B PA6
*/

package main;

public class RaceCar extends Car {
	
	/**
	 * Invokes the constructor in its super class and sets the race car's speed and strength to a default 40 and 3.
	 */
	public RaceCar() {
		super(40, 3);
	}
	
	/**
	 * Overloads the constructor method with given speed and strength, two int parameters
	 * @param speed int the speed of the race car
	 * @param strength int the strength of the race car
	 */
	public RaceCar(int speed, int strength) {
		super(speed, strength);
		this.checkSpeed(30, 55);
		this.checkStrength(2, 4);
	}
	
	@Override
	public int getScore() {
		return 150;
	}
	
	@Override
	public String toString() {
		String s = "RaceCar" + this.initSpeed + "/" + this.strength;
		return s;
	}
}
