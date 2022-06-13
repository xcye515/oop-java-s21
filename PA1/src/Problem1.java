/* Author: Xingchen Ye
 * Date: Feb 16, 2021
 * Homework 1
 * Problem 1
 */

import java.util.*;

public class Problem1 {
	
	/*
	 * The main method that runs through the program
	 * @param args is the command line parameter required for the signature of the method
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		int num = console.nextInt();
		console.close();
		System.out.println("Initial value is: " + num);
		if(num < 1) {
			System.out.println("Error");
			System.exit(0);
		}
		
		int c = 0;
		while(num > 1) {
			if(num % 2 == 0) {
				num = num/2;
				c++;
			}
			else {
				num = num*3 + 1;
				c++;
			}
			System.out.println("Next value is: " + num);
		}
	
		System.out.print("Final Value " + num);
		System.out.print(", number of operations performed " + c);
	}
}