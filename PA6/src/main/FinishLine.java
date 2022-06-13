/**
* This program constructs a FinishLine class with parameters and methods that help us to run a simulation of cars finishing the game
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 4, 2021
* COSI 12B PA5
*/

package main;

public class FinishLine {
	int count;
	int numOfCars;
	
	/**
	 * This constructor receives a int of the number of the total cars in the race, including RaceCar and FormulaOne cars.
	 * @param n int the total number of cars
	 */
	public FinishLine(int n) {
		this.numOfCars = n;
		this.count = 0;
	}

	/**
	 * This method receives a RaceCar object and sets its status to finished the race and counts the number of cars finishing the game
	 * @param c RaceCar finishing the game
	 */
	public void enterFinishLine(Car car) { 
		car.setFinished();
		this.count += 1;
	}
	
	/**
	 * This method gives a boolean value indicating whether all cars entering the race have finished the race
	 * @return true if all cars have finished the race, false otherwise
	 */
	public boolean finished() {
		if(this.numOfCars == this.count) {
			return true;
		} else {
			return false;
		}
	}
}

