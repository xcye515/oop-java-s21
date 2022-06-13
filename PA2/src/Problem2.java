/* Author: Xingchen Ye
 * Date: Feb 26, 2021
 * PA2
 * Problem 1
 * Description: This program allows you to play a game of reverse hangman.
 */

import java.util.*;

public class Problem2 {
	
	public static int num;
	public static int countRight = 0;
	public static int countWrong = 0;
	public static String dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String word;
	public static String printWord = "";
	
	/**
	 * In this main method, a scanner is created to read system input
	 * By calling introduce method, the program will inform the player how to play the game
	 * Then, the scanner takes the word the player thinks of and the number of letters in the word
	 * Guess method is called to guess the letters in the word, and the countRight and countWrong will be augmented according if the program guessed right or wrong
	 * Print method is called to display the shape of hangman
	 * The game should end when the times that the program guesses wrong exceeds 7 or the program wins. Conclude method is called to display win or lose message
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		
		introduce();
		Scanner console = new Scanner(System.in);
		System.out.println("How many letters are in your word?");
		num = console.nextInt();
		System.out.println("Please enter the word for me to guess (letters only):");
		word = console.next();
		
		boolean win = false;//sets the boolean value indicating if the program wins to default false
		
		
		for(int i = 0;i < num;i++) {//this for-loop sets the seperated word to default dashes
			printWord += "-";
		}
		
		while(countWrong < 7 && !win) {//this while loop ends if the program wins the game or the man is hang (guessed wrong for seven times)
			print(countWrong);//print the hangman diagram
			if(guess(rand, console)) {
				System.out.println("How many of that letter are in the word? ");
				countRight += console.nextInt();	
				if(countRight == num) {
					win = true;
				}
			} else {
				countWrong++;
			}
		}
		
		conclude(countWrong, win);//conclude the game by printing win or lose messages
		console.close();
	}
	
	/**
	 * Introduce the game of reverse hangman by printing out two messages
	 * @void no return
	 */
	public static void introduce() {
		System.out.println("This program plays a game of reverse hangman.");
		System.out.println("You think up a word (by typing it on the computer) and I'll try to guess the letters.");
	}
	
	/**
	 * Guess the letters in the word by generating random letters
	 * @param rand Random object generates pseudorandom number
	 * @param s scanner that read from the system input
	 * @return correct boolean value indicating if the program guesses correct or not
	 */
	public static boolean guess(Random rand, Scanner s) {
		boolean correct = false;
		
		System.out.println("I've got " + countRight + " of the " + num + " letters so far.");	
		int random = rand.nextInt(dict.length());
		char guess = dict.charAt(random);
		
		System.out.println("I guess: " + guess);
		System.out.println("Is that letter in the word?");
		String answer = s.next().substring(0,1);
		
		for(int i = 0;i < num;i++) {
			if(word.toUpperCase().charAt(i) == guess) {
				printWord = printWord.substring(0, i) + word.charAt(i) + printWord.substring(i+1);
			}
		}
		
		if(answer.equals("y")) {
			correct = true;
		} 
		if(random == dict.length() - 1) { // update dict to eliminate the possibility of the program guessing the same letter
			dict = dict.substring(0, random); 
		} else {
			dict = dict.substring(0, random) + dict.substring(random + 1);
		}
		
		return correct;
	}
	
	/**
	 * Conclude the game by printing win or lose messages
	 * @param count integer the number of guesses made in the last game
	 * @param result boolean value indicating if the program wins or not
	 * @void no return
	 */
	public static void conclude(int count, boolean result){
		if(result) {
			print(count);
			System.out.println("I win the game!");
		} else {
			print(count);
			System.out.println("You beat me this time.");
		}
		
	}
	
	/**
	 * Print the diagrams of reverse hangman
	 * @param count integer the number of guesses made in the last game
	 * @void no return
	 */
	public static void print(int count) {
		if(count == 0) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("+-----");
		}
		if(count == 1) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|  0");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("+-----");
		}
		if(count == 2) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|  0");
			System.out.println("|  |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("+-----");
		}
		if(count == 3) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|  0");
			System.out.println("|  |");
			System.out.println("|   \\");
			System.out.println("|");
			System.out.println("+-----");
		}
		if(count == 4) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|  0");
			System.out.println("|  |");
			System.out.println("| / \\");
			System.out.println("|");
			System.out.println("+-----");
		}
		if(count == 5) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|  0");
			System.out.println("|  |\\");
			System.out.println("| / \\");
			System.out.println("|");
			System.out.println("+-----");
		}
		if(count >= 6) {
			System.out.println(printWord);
			System.out.println("+--+");
			System.out.println("|  |");
			System.out.println("|  0");
			System.out.println("| /|\\");
			System.out.println("| / \\");
			System.out.println("|");
			System.out.println("+-----");
		}
		
	}

}
