/**
* This program allows you to play a simple war game with the computer.
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Mar 24, 2021
* COSI 12B PA4
*/

import java.util.*;

public class Casino {
	public static int budget = 100000; //total money given to the player
	public static int min; //bidding minimum
	public static int max; //bidding maximum
	public static boolean keepPlaying = true;
	public static int round = 0;
	public static String mode; //difficulty of the game
	
	/**
	 * This main method creates a Deck object to generate two piles of cards, one is the discarded cards pile, the other one is the pile on the deck.
	 * Then, it allows player to select the difficulty of the game by creating a Scanner object to read the player's response.
	 * It calls game() method to allow player to play one round of the game.
	 * After each game, the money remained in the player is re-calculated. Player can choose whether or not to keep playing by calling the resume method.
	 * When the player no longer wants to play or he/she has lost all the money, the game results are displayed by calling the conclude() method
	 * @param args String array read from the console
	 */
	public static void main(String[] args) {
		Deck deck = new Deck();
		Scanner s = new Scanner(System.in);
		init(s);
		while(keepPlaying == true) {
			round++;
			System.out.println("---- " + mode + ": Round "+ round + " ----");
			game(deck,s);
			if(keepPlaying == true) {
				System.out.println("Do you want to play one more round? (Please enter 'Yes' or 'No')");
				String c = s.next().toUpperCase();
				while(!c.substring(0,1).equals("Y") && !c.substring(0,1).equals("N")) {
					System.out.println("Please enter 'Yes' or 'No'.");
					c = s.next().toUpperCase();
				}
				resume(c);
			}
		}
		conclude();
		s.close();	
	}
	
	/**
	 * This method receives a scanner object and initializes a game. It introduces the rule of the game and allow the player to select the level of difficulty/risk.
	 * @param s Scanner read input from the console
	 */
	public static void init(Scanner s) {
		System.out.println("Welcome to the Casino! Let's play a simple war game.");
		System.out.println("First, you make a bet, and I will call your bet. Then, we both draw a card from the deck's pile. ");
		System.out.println("If your card has larger value than mine, you win the pot; otherwise, you lose your bet.");
		System.out.println("After each round, the cards are discarded. When the pile of cards on the deck is empty, we shuffle the cards.");
		System.out.println("You can stop playing whenever you want, or if you run out of money, you have to quit the game.");
		System.out.println("Let's get started! Please select a level of difficulty to proceed.");
		System.out.println("1. Easy 2. Normal 3. Hard (Please enter 1/2/3 to proceed)");
		int r = s.nextInt();
		if(r == 1) {
			min = 0;
			max = 10000;
			mode = "Easy Mode";
			System.out.println("You have selected Easy Mode. You are given 100,000 dollars, with bidding minimum 0 dollars and maxmimum up 10,000 dollars.");
		} else if (r == 2) {
			min = 0;
			max = 50000;
			mode = "Normal Mode";
			System.out.println("You have selected Normal Mode. You are given 100,000 dollars, with bidding minimum 0 dollars and maxmimum up 50,000 dollars.");
		} else if (r == 3) {
			min = 10000;
			max = 100000;
			mode = "Hard Mode";
			System.out.println("You have selected Hard Mode. You are given 100,000 dollars, with bidding minimum 10000 dollars and maxmimum up 100,000 dollars.");
		} else { //if the player enters a number other than 1,2,3, then he/she plays easy mode by default
			min = 0;
			max = 10000;
			mode = "Easy Mode";
			System.out.println("You are set to default Easy Mode. You are given 100,000 dollars, with bidding minimum 0 dollars and maxmimum up 10,000 dollars.");
		}
	}
	
	/**
	 * This method receives a Deck object and a Scanner object to allow the player play one round of the game
	 * @param d Deck with two piles of cards
	 * @param s Scanner read input from the console
	 */
	public static void game(Deck d, Scanner s) {
		int temp; //customize the bidding maximum for the player
		if(budget > max) { 
			temp = max;
		} else {
			temp = budget;
		}
		System.out.println("You have " + budget + " dollars. Please make a bet. (Enter an integer between " + min + " and " + temp + ")");
		int bet = s.nextInt();
		while(bet > budget || bet < min || bet > max) { //check if the bid is valid
			if(bet > budget) {
				System.out.println("You do not have enough money.");
			}
			if(bet < min) {
				System.out.println("Your bet is lower than the bidding minimum");
			}
			if(bet > max) {
				System.out.println("Your bet is higher than the bidding maximum");
			}
			System.out.println("Please enter a valid amount of bet. (Enter an integer between " + min + " and " + temp + ")");
			bet = s.nextInt();
		}
		System.out.println("Let's draw a card from the pile on the deck.");
		Card user = d.drawNextCard();
		Card computer = d.drawNextCard();
		System.out.println("Your card is " + user.toString());
		System.out.println("My card is " + computer.toString());
		if(computer.getValue() > user.getValue()) {
			System.out.println("Haha, I win!");
			budget -= bet;
			if(budget < 0) { //if the player runs out of the budget, then the game stops
				budget = 0;
				keepPlaying = false;
				System.out.println("You have lost all your money.");
			} else {
				System.out.println("You have remaining " + budget + " dollars.");
			}
		} else {
			System.out.println("Haha, you win!");
			budget += bet;
			System.out.println("You have remaining " + budget + " dollars.");
		}
	}
	
	/**
	 * This method receives a String the response from the player to determine whether the game should continue 
	 * @param c String starts with Y or N indicating player's response to whether or not keep playing
	 */
	public static void resume(String c) { 
		if(c.charAt(0) == 'Y') {
			keepPlaying = true;
		} else if(c.charAt(0) == 'N') {
			keepPlaying = false;
		}
	}
	
	/**
	 * This method prints the result of the game, containing the total rounds played, the money won/lost and the mode the player selects first.
	 */
	public static void conclude() {
		System.out.println("---- RESULT ----");
		System.out.println( mode + " : You have played " + round + " rounds in total. You have " + budget + " dollars.");
		if(budget > 100000) {
			int win = budget-100000;
			System.out.print("You have won " + win + " dollars in total. Congratulations!");
		} else {
			int lose = 100000 - budget;
			System.out.print("You have lost " + lose + " dollars in total.");
		}
	}
	
}
