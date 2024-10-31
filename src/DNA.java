/**
 * Kirsten Tapalla
 * ktapalla@brandeis.edu
 * March 8, 2021
 * Programming Assignment 3
 * Program Explanation: 
 * - Opens/reads a file - takes in lines of text (regional names and sequences) and creates a 
 *   file with information about the sequences in it 
 *   - Info includes name of the sequence, the sequence, an array of individual nucleotide counts,
 *   an array of individual nucleotide mass percentages, an array of codons within the sequence, 
 *   and states whether it is a protein-coding gene
 * Notes: 
 * - When I included the files in the program, I had to do it kind of weirdly (had to put one of each in 2 different locations) 
 * 	 - This was b/c when I left it in the project in general, the terminal couldn't run/find it in the folder I was in,
 *     then when I left it in the src file, the console on Eclipse couldn't run/find it
 *       - I compromised by just putting them in both areas so that it would work for both when I ran it rather than showing an error
 *  
 */

import java.io.*;
import java.util.*;

public class DNA {
	/**
	 * Four class constants: minimum number of codons required, valid percent mass,
	 * unique nucleotides, nucleotides per codon 
	 * - Values obtained from instructions
	 */
	public static int minNumCod = 5;
	public static int validPercentMass = 30;
	public static final int uniNuc = 4;
	public static final int nucPerCod = 3;

	/**
	 * Creates array from lines within the file - First creates larger array with
	 * null values - Then creates smaller array with exact number of lines in file -
	 * Essentially reads input file
	 */
	public static String[] fileStrArr(Scanner input) {
		String[] largeFileArr = new String[100];
		int count = 0;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			largeFileArr[count] = line;
			count++;
		}
		String[] actFileArr = new String[count];
		for (int i = 0; i < actFileArr.length; i++) {
			actFileArr[i] = largeFileArr[i];
		}
		return actFileArr;
	}

	/**
	 * Counts number of times a character appears in a nucleotide sequence
	 * (A,C,G,T,-)
	 */
	public static int charCount(String seq, char nuc) {
		int count = 0;
		for (int i = 0; i < seq.length(); i++) {
			if (seq.charAt(i) == nuc) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Returns array of number of individual nucleotides in a sequence
	 */
	public static int[] nucCountArr(String seq) {
		int[] nucCountArr = new int[uniNuc];
		char[] nuc = { 'A', 'C', 'G', 'T' };
		for (int i = 0; i < uniNuc; i++) {
			nucCountArr[i] = charCount(seq, nuc[i]);
		}
		return nucCountArr;
	}

	/**
	 * Creates/returns an array containing the total masses of the nucleotides and
	 * the dashes
	 */
	public static double[] indMassArr(int[] nucCountArr, String seq) {
		double[] nucMassOfOne = { 135.128, 111.103, 151.128, 125.107, 100.0 };
		int lastMassInd = nucMassOfOne.length - 1;
		double[] indMassArr = new double[uniNuc + 1]; // +1 for dashes
		double indMassVal = 0.0;
		for (int i = 0; i < nucCountArr.length; i++) {
			indMassVal = nucCountArr[i] * nucMassOfOne[i];
			indMassArr[i] = indMassVal;
		}
		indMassVal = nucMassOfOne[lastMassInd] * charCount(seq, '-');
		indMassArr[lastMassInd] = indMassVal;
		return indMassArr;
	}

	/**
	 * Uses indMassArr to return a double of the total mass Used to calculate mass
	 * percents of nucleotides
	 */
	public static double getTotalMass(double[] indMassArr) {
		double total = 0.0;
		for (int i = 0; i < indMassArr.length; i++) {
			total += indMassArr[i];
		}
		return total;
	}

	/**
	 * Creates an array of doubles which are percentages of each 
	 * different nucleotide's calculated masses
	 * - Rounded to the first decimal using Math.round 
	 */
	public static double[] massCalc(double[] indMassArr, double total) {
		double[] massCalcArr = new double[uniNuc];
		for (int i = 0; i < uniNuc; i++) {
			double percent = (indMassArr[i] / total) * 100;
			massCalcArr[i] = Math.round(percent * 10.0) / 10.0;
		}
		return massCalcArr;
	}

	/**
	 * Creates an array of 'region names' from file array
	 */
	public static String[] regNameArr(String[] fileArr) {
		String[] regName = new String[fileArr.length / 2];
		int rNInd = 0;
		while (rNInd != regName.length) {
			for (int fAInd = 0; fAInd < fileArr.length; fAInd += 2) {
				regName[rNInd] = fileArr[fAInd];
				rNInd++;
			}
		}
		return regName;
	}

	/**
	 * Creates an array of nucleotide sequences from file array
	 */
	public static String[] seqArr(String[] fileArr) {
		String[] seqArr = new String[fileArr.length / 2];
		int sAInd = 0;
		while (sAInd != seqArr.length) {
			for (int fAInd = 1; fAInd < fileArr.length; fAInd += 2) {
				String seq = fileArr[fAInd];
				seqArr[sAInd] = seq.toUpperCase();
				sAInd++;
			}
		}
		return seqArr;
	}

	/**
	 * Returns an altered string of sequences without the dashes
	 */
	public static String removeDashes(String seq) {
		String altSeq = seq;
		for (int i = altSeq.length() - 1; i > 0; i--) {
			char c = altSeq.charAt(i);
			if (c == '-') {
				altSeq = altSeq.substring(0, i) + altSeq.substring(i + 1);
			}
		}
		return altSeq;
	}

	/**
	 * Creates and returns an array made up of the codons from a sequence
	 */
	public static String[] codons(String altSeq) {
		int len = altSeq.length() / 3;
		String[] codons = new String[len];
		int codInd = 0;
		while (altSeq.length() != 0 && codInd != len) {
			String codon = altSeq.substring(0, nucPerCod);
			altSeq = altSeq.substring(nucPerCod);
			codons[codInd] = codon;
			codInd++;
		}
		return codons;
	}

	/**
	 * Compares two codons
	 *  - Used to see if there is a valid start codon and a valid stop codon
	 */
	public static boolean codonCompTest(String codon, String compTo) {
		boolean truOrFal = false;
		for (int i = 0; i < nucPerCod; i++) {
			if (codon.charAt(i) != compTo.charAt(i)) {
				return false;
			} else {
				truOrFal = true;
			}
		}
		return truOrFal;
	}

	/**
	 * Predicts whether or not the sequence is a protein-coding gene and returns a boolean 
	 * - true = yes 
	 * - false = no
	 */
	public static boolean proteinTest(String[] codons, double[] massCalc) {
		boolean truOrFal = false;
		int codLen = codons.length;
		String[] possStopCod = { "TAA", "TAG", "TGA" };
		String valStartCod = "ATG";
		String firstCod = codons[0];
		String lastCod = codons[codLen - 1];
		double cAndGTotal = massCalc[1] + massCalc[2];
		if ((codonCompTest(firstCod, valStartCod) == true) && (codons.length >= minNumCod)
				&& (cAndGTotal >= (double) validPercentMass)
				&& (codonCompTest(lastCod, possStopCod[0]) == true || codonCompTest(lastCod, possStopCod[1]) == true
						|| codonCompTest(lastCod, possStopCod[2]) == true)) {
			truOrFal = true;
		} else {
			truOrFal = false;
		}
		return truOrFal;
	}

	/**
	 * Returns string corresponding to whether it is a protein-codind gene 
	 * - true returns yes 
	 * - false returns no
	 */
	public static String pTString(boolean proteinTest) {
		String pTString = "";
		if (proteinTest == true) {
			pTString = "YES";
		} else {
			pTString = "NO";
		}
		return pTString;
	}

	/**
	 * prints the output file under the name provided by the user
	 */
	public static void print(PrintStream printFile, String regName, String altSeq, int[] nucCountArr, double[] massCalc, double totalMass, String[] codons, String pTString) {
		printFile.format("%-13s%s\n", "Region Name: ", regName);
		printFile.printf("%-13s%s\n", "Nucleotides: ", altSeq);
		printFile.printf("%-13s%s\n", "Nuc. Counts: ", Arrays.toString(nucCountArr));
		printFile.printf("%-13s%s%4s%.1f\n", "Total Mass%: ", Arrays.toString(massCalc), " of ", totalMass);
		printFile.printf("%-13s%s\n", "Codons List: ", Arrays.toString(codons));
		printFile.printf("%-13s%s\n", "Is Protein?: ", pTString);
		printFile.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		// Print opening explanation
		String openingMessage = "This program reports information about DNA" + '\n'
				+ "nucleotide sequences that may encode proteins.";
		System.out.println(openingMessage);
		// Creates scanner object to read file names
		Scanner console = new Scanner(System.in);
		System.out.print("Input file name? ");
		String inFile = console.next();
		// Reads input file; file lines to array of strings
		File inputFile = new File(inFile);
		Scanner input = new Scanner(inputFile);
		String[] fileArr = fileStrArr(input);
		// Requests output file to print
		System.out.print("Output file name? ");
		String outFile = console.next();
		File outputFile = new File(outFile);
		PrintStream printFile = new PrintStream(outputFile);
		// Splits fileArr into an array of region names and an array of sequences
		String[] regNameArr = regNameArr(fileArr);
		String[] seqArr = seqArr(fileArr);
		// Runs through seqArr and regNameArr to produce individual sequences/names and run methods
		int ind = 0;
		while (ind != seqArr.length) {
			String regName = regNameArr[ind];
			String seq = seqArr[ind];
			// Mass percentage calculation using original sequence (dashes needed) 
			int[] nucCountArr = nucCountArr(seq);
			double[] indMassArr = indMassArr(nucCountArr, seq);
			double totalMass = getTotalMass(indMassArr);
			double[] massCalc = massCalc(indMassArr, totalMass);
			// New strings created and passed without the dashes; used for the rest of the program 
			String altSeq = removeDashes(seq);
			String[] codons = codons(altSeq);
			// Tests whether it is a protein then returns associated string/answer 
			boolean proteinTest = proteinTest(codons, massCalc);
			String pTString = pTString(proteinTest);
			//Prints the results/output in the new file named by user
			print(printFile, regName, altSeq, nucCountArr, massCalc, totalMass, codons, pTString);
			ind++;
		}

	}

}
