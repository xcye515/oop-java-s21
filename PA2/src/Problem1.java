/* Author: Xingchen Ye
 * Date: Feb 26, 2021
 * PA2
 * Problem 1
 * Description: This program allows you to play a simple guess game.
 */

import java.util.*;


public class Problem1 {

	/**
	 * In this main method, a scanner is created to read system input
	 * By calling introduce method, the program will inform the play the range of integers that the player should input
	 * The range of integers can be modified by changing the assignment value in this main method.
	 * Then, we call the next_game method to determine if the player wants to continue playing. 
	 * By calling guess method until the player does not want to play the game, the program guesses the integer that the player comes up with
	 * Then, when the player stops playing, the main method calls printResults to display the general information of the rounds
	 */
	public static void main(String[] args) {
		int lower = 1; // the lower bound of the integer guessed is set to 1
		int upper = 100; // the upper bound of the integer guessed is set to 1
		Random rand = new Random();
		int games = 1; // counts the number of games played
		int totalguesses = 0; // counts the total number of guesses in the games
		int max_guesses = 0; // records the max number of guesses made in a single round 
		
		introduce(lower,upper); // introduce the rule of the game and the bounds of the integer that the player should input
		
		// plays the first round of the game
		Scanner console = new Scanner(System.in);
		int temp_count = guess(rand, lower, upper, console); // counts the number of guesses in the first round of playing
		totalguesses += temp_count; 
		max_guesses = temp_count;
		
		String next_game = console.next(); // determine if the player wants to play another game by the input
		boolean next = next_game(next_game);
		while(next == true) { // this while loop ends if the player no longer wants to play
			games++;
			temp_count = guess(rand, lower, upper, console);
			totalguesses += temp_count;
			if(temp_count > max_guesses) {
				max_guesses = temp_count;
			}
			next_game = console.next();
			next = next_game(next_game);
		}
		
		printResults(games, totalguesses, max_guesses); // display the results of the games
	}
	
	/**
	 * Use the lower and upper bound set in the main method to print strings
	 * @param lo integer the lower bound of the number guessed
	 * @param up integer the upper bound of the number guessed
	 * @void no return
	 */
	public static void introduce(int lo, int up) {
		System.out.println("This program allows you to play a guessing game");
		System.out.println("Think of a number between " + lo + " and " + up);
		System.out.println("and I will guess until I get it.");
		System.out.println("For each guess, tell me if the");
		System.out.println("right answer is higher or lower than your guess, or if it is correct.");
	}
	
	
	/**
	 * Uses a scanner object, random object, and lower and upper bound to play one round of the guessing game
	 * @param rand Random object generates pseudorandom number
	 * @param lo integer the lower bound of the number guessed
	 * @param up integer the upper bound of the number guessed
	 * @param s a scanner read from the system input
	 * @return count integer the number of guesses made in this single game
	 */
	public static int guess(Random rand, int lo, int up, Scanner s){
		System.out.println("Think of a number...");
		int count = 1;
		int guess = rand.nextInt(up-lo)+lo; // generates random integer in the range of up and lo inclusive
		System.out.println("My guess:" + guess);
		String line = s.next();
		while(!line.equals("correct")) { // the while loop ends when the program guesses the correct integer
			if(line.equals("lower")) {
				up = guess; // set the upper bound to be the last number guessed
			} else if(line.equals("higher")) {
				lo = guess + 1; // set the lower bound to be the last number guessed plus one. The program would not repeat guessing the same integer
			} else { // if the player enter something else than "lower" and "upper", the program gives a second chance of entering the response
				System.out.println("Please answer with higher or lower or correct");
				line = s.next();
			}
			if(lo == up) { // if the player is not answering the program truthfully then the display a message
				System.out.println("Are you sure you are honest in answering me?");
				break;
			} else { 
			guess = rand.nextInt(up-lo)+lo; 
			System.out.println("My guess:" + guess);
			count++;
			line = s.next();
			}
		}
		
		System.out.println("I got it right in "+ count +" guesses"); 
		System.out.println("Do you want to play again?");
		return count;
	}
	
	
	/**
	 * Decides if the player wants to play another game
	 * @param str String read from the system input
	 * @return next boolean indicates whether the players wants to play another game or not
	 */
	public static boolean next_game(String str) {
		boolean next = false;
		if(str.substring(0,1).equals("y") || str.substring(0,1).equals("Y")) {
			next = true;
		}
		else if (str.substring(0,1).equals("n") || str.substring(0,1).equals("N")) {
			next = false;
		}
		return next;
	}
	
	/**
	 * Display an overview of the games
	 * @param games integer the number of games played in a sequence
	 * @param totalguesses integer the total guesses the program makes in the games
	 * @param max_guesses integer the maximum number of guesses made in one single game
	 * @void no return
	 */
	public static void printResults(int games, int totalguesses, int max_guesses){
		System.out.println("Overall results:");
		System.out.println("total games = " + games);
		System.out.println("total guesses =  " + totalguesses);
		System.out.println("guesses/game = " + totalguesses/games);
		System.out.println("max guesses = " + max_guesses);
	}
}
