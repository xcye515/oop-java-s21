/* Author: Xingchen Ye
 * Date: Feb 16, 2021
 * Homework 1
 * Problem 5
 */

import java.util.*;

public class Problem5{
    public static void main(String args[]){
    	System.out.println("Please enter a positive integer");
    	Scanner console = new Scanner(System.in);
    	int in = console.nextInt();
    	console.close();
    	seperate(in);
    } 
    
    public static void seperate(int num) {
    	int temp = num;
    	int d = 1;
    	while(temp > 0) {
    		temp = temp / 10;
    		d *= 10;
    	}
    	d = d/10;
    	while(d > 0) {
    		int r = num / d;
    		System.out.println(r);
    		num = num - d * r;
    		d = d / 10;
    	}
    }
   
}