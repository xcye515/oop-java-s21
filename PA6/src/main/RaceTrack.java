/**
* This program constructs a RaceTrack class with parameters and methods that help us to run a simulation of a race
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Apr 18, 2021
* COSI 12B PA6
*/

package main;

public class RaceTrack {

	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events. For more see the assignment PDF / documentation for TrackLoggerB.java
	 */
	private TrackLoggerC logger;
	public Car[] cars; // upcasting the subclasses objects to Car objects for the storing them in the array of same type
	public FinishLine line;
	public PitStop pit;
	public int place = 0;
	
	/**
	 * This constructor creates a default RaceTrack object by initializing a pitstop and a logger object
	 */
	public RaceTrack() {
		logger = new TrackLoggerC(); // DO NOT REMOVE THIS LINE
		this.pit = new PitStop(2);
	}
	
	/**
	 * This method receives a array of Car objects that and enters them or part of them into the game
	 * @param cars Array of Car objects
	 */
	public void setCars(Car[] cars) {
		if(cars.length <= 10) {
			this.cars = cars;
		} else {
			this.cars = new Car[10];
			for(int i = 0; i < 10; i++) {
				this.cars[i] = cars[i];
			}
		}
		this.line = new FinishLine(this.cars.length);
	}
	
	/**
	 * This method receives two arrays of RaceCar and FormulaOne objects that and enters them or part of them into the game
	 * @param racecars Array of RaceCar objects
	 * @param formulaOnes Array of FormulaOne objects
	 */
	public void setCars(RaceCar[] racecars, FormulaOne[] formulaOnes) {
		if(racecars.length + formulaOnes.length <= 10) {
			int l = racecars.length + formulaOnes.length;
			this.cars = new Car[l];
			for(int i = 0; i < racecars.length; i++) {
				this.cars[i] = racecars[i];
			}
			for(int i = racecars.length; i < l; i++) {
				this.cars[i] = formulaOnes[i-racecars.length];
			}
		} else {
			this.cars = new Car[10];
			if(racecars.length < 10) {
				for(int i = 0; i < racecars.length; i++) {
					this.cars[i] = racecars[i];
				}
				for(int i = racecars.length; i < 10; i++) {
					this.cars[i] = formulaOnes[i-racecars.length];
				}
			} else {
				for(int i = 0; i < 10; i++) {
					this.cars[i] = racecars[i];
				}
			}
		}
		this.line = new FinishLine(this.cars.length);
	}
	
	
	/**
	 * This method simulates one tick on the race track by moving all cars to the places that they are supposed to be
	 * If the car is not damaged and not finishing the race, its location will be updated based on its speed
	 * If the car is damaged, then it will be moved to the pit once it passes the 75.0 unit
	 * If the car is passes the finish line at the 1000.00 unit, then it will be moved to the finish line
	 * Finally, when all cars' location and status are updated, it calls checkCollision() method to check whether cars collide and get damaged
	 */
	public void tick() {
		this.logger.logNewTick();
		if(this.cars != null) {
			for(int i = 0; i < this.cars.length; i++) { //for all race cars entering the game
				Car c = this.cars[i];
				if(c.ifExit()) { //if the car was exiting the pit, then update it to not exiting the next tick
					c.setExit(false);
				}
				if(!c.getFinished()) {
					if(!c.inPit()) {
						double last = c.getUnits();
						c.setLocation(c.getLocation()+c.getSpeed());
						if(c.isDMG() && last <= 75.0 && last+c.getSpeed() >= 75.0 ) { //if a damaged car is passing the 75.0 unit on the track
							this.pit.enterPitStop(c);
							this.logger.logEnterPit(c);
						}
					} else { // if the car is currently in the pit, then we update the waiting rounds in the pit
						c.setTicksInPit();
						if(c.getTicksInPit() == 0) { // if the car's remaining rounds waiting in the pit is zero, then it should exit the pit stop
							this.pit.exitPitStop(c);
							this.logger.logExitPit(c);
							c.setLocation(c.getLocation()+c.getSpeed());
						}
					}
					if(c.getLocation() >= 1000.0) {
						this.line.enterFinishLine(c);
						this.place++;
						this.logger.logFinish(c, place);
					}
				}
			}
		}
		checkCollision();
	}
	
	/**
	 * This method checks whether two cars on the track collide, meaning that they are at the same position on the track
	 */
	public void checkCollision() {
		if(this.cars != null) {
			for(int i = 0; i < this.cars.length; i++) { // checking whether two race cars collide
				Car a = this.cars[i];
				for(int j = i+1; j < this.cars.length; j++) {
					Car b = this.cars[j];
					if(!a.getFinished() && !b.getFinished() && !a.inPit() && !b.inPit()) {
						if(a.getUnits() == b.getUnits()) {
							if(!(a.ifExit() && b.ifExit())) {
								if(a.isDMG() && !b.isDMG()) {
									b.setDMG(true);
									this.logger.logDamaged(b);
								} else if (b.isDMG() && !a.isDMG()) {
									a.setDMG(true);
									this.logger.logDamaged(a);
								} else if (!a.isDMG() && !b.isDMG()) {
									a.setDMG(true);
									this.logger.logDamaged(a);
									b.setDMG(true);
									this.logger.logDamaged(b);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * This method simulating a race by calling tick() methods until all cars have finished the race. Then, it logs the record to the logger
	 */
	public void run() {
		int ticks = 0;
		while(!this.line.finished()) {
			tick();
			ticks++;
		}
		this.logger.logScore(this.calculatorScore(ticks));
	}
	
	/**
	 * This method receives an int of the number of the ticks the game has done and calculate the game's score based on the number of cars and ticks in the game
	 * @param ticks int the total number of ticks in the game
	 * @return score int the score of the game
	 */
	public int calculatorScore(int ticks) {
		int score = 1000 - 20*ticks;
		for(int i = 0; i < this.cars.length; i++) {
			score += this.cars[i].getScore();
		}
		return score;
	}
	
	/**
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	public TrackLoggerC getLogger() {
		return logger;
	}

}
