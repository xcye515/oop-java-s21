/**
* This program constructs a PitStop class with parameters and methods that help us to run a simulation of cars entering, waiting, and exiting the pit stop 
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 4, 2021
* COSI 12B PA5
*/

package main;

public class PitStop {
	public int waits;
	
	/**
	 * This constructor receives the number of rounds that a car has to wait in a pit stop and constructs a PitStop object
	 * @param n int the number of rounds that a car has to wait in a pit stop
	 */
	public PitStop(int n) {
		this.waits = n;
	}
	
	/**
	 * This method receives a RaceCar object and resets its location and pit status
	 * @param c RaceCar entering the pit stop
	 */
	public void enterPitStop(Car car) {
		int laps;
		if(car.getUnits() >= 75) {
			laps = (int) car.getLocation()/100;
		} else {
			laps = (int)(car.getLocation()/100) - 1;
		}
		car.setLocation(laps * 100.0 + 75.0);
		car.setPit(true, this.waits);
	}
	
	/**
	 * This method receives a RaceCar object and resets its location and pit status
	 * @param c RaceCar exiting the pit stop
	 */
	public void exitPitStop(Car car) {
		car.setExit(true);
		car.setDMG(false);
		car.setPit(false, 0);
	}
	
}
