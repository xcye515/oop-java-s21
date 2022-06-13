/* Author: Xingchen Ye
 * Date: Feb 16, 2021
 * Homework 1
 * Problem 2
 */

import java.util.*;

public class Problem2 {
	/*
	 * The main method that runs through the program
	 * @param args is the command line parameter required for the signature of the method
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner console = new Scanner(System.in);
		System.out.println("Please enter a positive integer less than 4999:");
		int num = console.nextInt();
		console.close();
		if(num > 4999 || num < 1) {
			System.out.println("Input invalid.");
		} else {
		toRoman(num);
		}
	}
	
	/*
	 * The method converts a integer to roman number
	 * @param num an integer that we want to convert
	 */
	public static void toRoman(int num) {
		String s = "";
		while(num >= 1000) {
				s += "M";
				num -= 1000;
			}
			if(num >= 900) {
				s += "CM";
				num -= 900;
			}
			if(num >= 500) {
				s += "D";
				num -= 500;
			}
			if(num >= 400) {
				s += "CD";
				num -= 400;
			}
			while(num >= 100) {
				s += "C";
				num -= 100;
			}
			if(num >= 90) {
				s += "XC";
				num -= 90;
			}
			if(num >= 50) {
				s += "L";
				num -= 50;
			}
			if(num >= 40) {
				s += "XL";
				num -= 40;
			}
			if(num >= 10) {
				s += "X";
				num -= 10;
			}
			if(num >= 9) {
				s += "IX";
				num -= 9;
			}
			if(num >= 5) {
				s += "V";
				num -= 5;
			}
			if(num >= 4) {
				s += "IX";
				num -= 4;
			}
			while(num > 0) {
				s += "I";
				num--;
			}
		System.out.println(s);
	}
}