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

public class FormulaOne extends Car{
	
	/**
	 * Invokes the constructor in its super class and sets the formula one's speed and strength to a default 50 and 4.
	 */
	public FormulaOne() {
		super(50, 4);
	}
	
	/**
	 * Overloads the constructor method with given speed and strength, two int parameters
	 * @param speed int the speed of the formula one car
	 * @param strength int the strength of the formula one car
	 */
	public FormulaOne(int speed, int strength) {
		super(speed, strength);
		this.checkSpeed(30, 70);
		this.checkStrength(3, 5);
	}
	
	@Override
	public int getScore() {
		return 100;
	}
	
	@Override
	public String toString() {
		String s = "FormulaOne" + this.initSpeed + "/" + this.strength;
		return s;
	}
}
