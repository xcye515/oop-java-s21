/**
* This program constructs a SportsCar class which inherits and overrides methods from the Car class. This class also implements the toString() and getScore() abstract methods.
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 18, 2021
* COSI 12B PA6
*/

package main;

public class SportsCar extends Car {
	
	/**
	 * Invokes the constructor in its super class and sets the sports car's speed and strength to a default 30 and 2.
	 */
	public SportsCar() {
		super(30, 2);
	}
	
	/**
	 * Overloads the constructor method with given speed and strength, two int parameters
	 * @param speed int the speed of the sports car
	 * @param strength int the strength of the sports car
	 */
	public SportsCar(int speed, int strength) {
		super(speed, strength);
		this.checkSpeed(20, 45);
		this.checkStrength(1, 3);
	}
	
	@Override
	public int getScore() {
		return 200;
	}
	
	@Override
	public String toString() {
		String s = "SportsCar" + this.initSpeed + "/" + this.strength;
		return s;
	}
}
