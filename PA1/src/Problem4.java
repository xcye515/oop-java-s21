/* Author: Xingchen Ye
 * Date: Feb 16, 2021
 * Homework 1
 * Problem 4
 */

import java.util.*;

public class Problem4 {
	
	/*
	 * The main method that runs through the program
	 * @param args is the command line parameter required for the signature of the method
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("What's your first name?");
		String first = console.next();
		System.out.println("What's your last name?");
		String last = console.next();
		console.close();
		toPigLatin(first);
		System.out.print(' ');
		toPigLatin(last);
	}
	
	/*
	 * The method converts a phrase to pig latin
	 * @param str is the phrase we want to convert
	 * @void no return
	 */
	public static void toPigLatin(String str) {
		str = str.toLowerCase();
		str = str.substring(1) + str.charAt(0);
		str = str.substring(0, 1).toUpperCase() + str.substring(1);;
		System.out.print(str + "ay");
	}

}
