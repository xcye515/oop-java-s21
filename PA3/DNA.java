/* Author: Xingchen Ye
 * xingchenye@brandeis.edu
 * Mar 7, 2021
 * PA3
 * Description: This program receives a input file of DNA nucleotides,
 * and it computes the information about the given nucleotides 
 * and stores them in a new file
 * Known Bugs: None
 */

import java.io.*;
import java.util.*;

public class DNA {
	public static int numCodons = 5;
	public static int percDefault = 30;
	public static int uniqueNu = 4;
	public static int nuPerCodon = 3;
	
	/**	This main method receives the input file name and output file name from the console.
	 * It creates a Scanner object to read from the input file and and a PrintStream object to produce the output file.
	 * Then, it calls a method to produce the output file.
	 * @exception FileNotFoundException if the input file is not found 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("This program reports information about DNA"
				+ " nucleotide sequences that may encode proteins.");
		System.out.println("Input file name? ");
		String inputFile = console.nextLine();
		System.out.println("Output file name? ");
		String outputFile = console.nextLine();
		console.close();
		Scanner dna = new Scanner(new File (inputFile));
		PrintStream output = new PrintStream (new File (outputFile));
		output(dna, output);
	}
	
	/**
	 * Receives a Scanner object and PrintStream object to produce the output file.
	 * @param s Scanner read from the input file
	 * @param o PrintStream create the output file
	 * @void no return
	 */
	public static void output(Scanner s, PrintStream o) {
		while(s.hasNext()) { // this while loop allows the program to read the entire input file
			o.println("Region Name: " + s.nextLine());
			String nuc = s.nextLine();
			nuc = nuc.toUpperCase();
			o.println("Nucleotides: " + nuc);
			int[] counts = getNucCounts(nuc);
			o.println("Nuc. Counts: " + Arrays.toString(counts));
			double total = getTotalMass(counts, nuc);
			double[] perc = getMassPerc(counts, total);
			o.println("Total Mass%: " + Arrays.toString(perc) + " of " + total);
			String[] codons = getCodonTrips(counts, nuc);
			o.println("Codons List: " + Arrays.toString(codons));
			o.println("Is Protein?: " + isProtein(codons, perc));
			o.println("");
		}
	}
	
	/**
	 * Receives a String and generates an int array that counts the character in the string.
	 * @param str String the chain of nucleotides
	 * @return counts int[] counts of each nucleotide in the chain of nucleotides
	 */
	public static int[] getNucCounts(String str) {
		int[] counts = new int[uniqueNu];
		for(int i = 0; i < str.length(); i++ ) {
			if(str.charAt(i) == 'A') {
				counts[0] += 1;
			}
			if(str.charAt(i) == 'C') {
				counts[1] += 1;
			}
			if(str.charAt(i) == 'G') {
				counts[2] += 1;
			}
			if(str.charAt(i) == 'T') {
				counts[3] += 1;
			}
		}
		return counts;
	}
	
	/**
	 * Receives a String and an int array and generates a double  
	 * that indicates the total mass of the chain of nucleotides
	 * @param counts int[] counts of each nucleotide in the chain of nucleotides
	 * @param str String the chain of nucleotides
	 * @return total double the total mass of the chain of nucleotides
	 */
	public static double getTotalMass(int[] counts, String str) {
		double total = 0.0;
		
		// calculates the total mass of the chain by the number of each nucleotide and the number of junk
		total += counts[0]*135.128 
				+ counts[1]*111.103 
				+ counts[2]*151.128 
				+ counts[3]*125.107
				+ (str.length()-counts[0]-counts[1]-counts[2]-counts[3])*100.000;
		
		// round the number to one-digit
		total = (double)Math.round(total*10)/10;
		return total;
	}
	
	/**
	 * Receives an int array and a double and generates a double array 
	 * that indicates the mass percentage of each nucleotide
	 * @param counts int[] counts of each nucleotide in the chain of nucleotides
	 * @param total double the total mass of the chain of nucleotides
	 * @return massPerc double[] the mass percentage of each nucleotide in the chain of nucleotides
	 */
	public static double[] getMassPerc(int[] counts, double total) {	
		double[] massPerc = new double[uniqueNu];
		
		// computes the percentage and round the number to one-digit
		for(int i = 0; i < uniqueNu; i++ ) {
			if(i == 0) {
				massPerc[i] = (double)Math.round((counts[i]*135.128/total)*1000)/10;
			} else if(i == 1) {
				massPerc[i] = (double)Math.round((counts[i]*111.103/total)*1000)/10;
			} else if(i == 2) {
				massPerc[i] = (double)Math.round((counts[i]*151.128/total)*1000)/10;
			} else if(i == 3) {
				massPerc[i] = (double)Math.round((counts[i]*125.107/total)*1000)/10;
			}
		}
		
		return massPerc;
	}
	
	/**
	 * Receives a String and an int array and generates a String array 
	 * that lists the codons in the the chain of nucleotides 
	 * @param counts int[] counts of each nucleotide in the chain of nucleotides
	 * @param str String the chain of nucleotides
	 * @return codonTrips String[] the codons in the chain of nucleotides
	 */
	public static String[] getCodonTrips(int[] counts, String str) {
		int totalCounts = 0;
		
		// counts the total number of nucleotides in the chain, and it avoids counting the junk; 
		for(int i = 0; i < uniqueNu; i++) {
			totalCounts += counts[i];
		}
		String[] codonTrips = new String[totalCounts/3];
		
		// generates a new string that excludes the junk
		for(int i = str.length() -1 ; i >= 0 ; i--) {
			while (str.charAt(i) == '-') {
				if(i == 0) {
					str = str.substring(i+1);
				} else {
					str = str.substring(0,i) + str.substring(i+1);
				}
			}
		}
		
		// every three nucleotides in the new chain compose a codon
		for(int i = 0; i < totalCounts/3; i++) {
			codonTrips[i] = str.substring(3*i, 3*i+3);
		}
		return codonTrips;
	}
	
	/**
	 * Receives a String and a double array and generates a String 
	 * that indicates whether or not the chain of nucleotides is a protein 
	 * @param codons String[] the codons in the chain of nucleotides
	 * @param perc the mass percentage of each nucleotide in the chain of nucleotides
	 * @return p String indication of whether the input dna is a protein
	 */
	public static String isProtein(String[] codons, double[] perc) {
		String p;
		boolean protein = true;
		// a protein-coding gene has to begin with a start codon
		if(!codons[0].equals("ATG")) {
			protein = false;
		} 
		
		// a protein-coding gene has to end with a stop codon
		if (!codons[codons.length-1].equals("TAA")) {
			if (!codons[codons.length-1].equals("TGA")) {
				if (!codons[codons.length-1].equals("TAG")) {
					protein = false;
				}
			}
		} 
		
		// a protein-coding gene contains at least numCodons (default with 5) total codons
		if (codons.length < numCodons) {
			protein = false;
		} 
		
		// a protein-coding gene has Cytosine and Guanine combined account 
		// for at least percDefault (default with 30) percentage of its total mass
		if ((perc[1] + perc[2]) < percDefault) {
			protein = false;
		}
		
		if(protein == false) {
			p = "NO";
		} else {
			p = "YES";
		}
		
		return p;
	}
	
}
