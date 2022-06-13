/* Author: Xingchen Ye
 * Date: Feb 16, 2021
 * Homework 1
 * Problem 3
 */

import java.util.*;

public class Problem3 {

	/*
	 * The main method that runs through the program
	 * @param args is the command line parameter required for the signature of the method
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Your message?");
		String inputString = console.nextLine();
		System.out.println("Encoding key?");
		int inputNum = console.nextInt();
		console.close();
		encode(inputString, inputNum);
	}
	
	/*
	 * The method encodes a string following the rule 
	 * @param str is the phrase we want to encode, num is the encoding key
	 * @void no return
	 */
	public static void encode(String input, int num) {
		String dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char incharUpper;
		String outString = "";
		char inchar = ' ';
		char outchar = ' ';
		int outindex;
		for(int i = 0; i < input.length(); i++) {
			inchar = input.charAt(i);
			if(inchar == ' ') {
				outString += " ";
			}else{
				incharUpper = Character.toUpperCase(inchar);
				for(int j = 0; j < 26; j++) {
					if(incharUpper == dict.charAt(j)) {
						outindex = j + num;
						if(outindex > 25) {
							outindex -= 26;
						}
						outchar = dict.charAt(outindex);
					}
			}
			outString += outchar;
			}
		}
		
		System.out.println("Your message: " + outString);
	}
}
