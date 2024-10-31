# README - COSI 12b Programming Assignment 3

The code provided in this repository contains the solutions to PA3 for COSI 12b - Advanced Programming Techniques in Java. The problem will be described below, as well as the instructions for how to install and run the code. 

Note: This assignment was done for a class, and we were limited to using only Java features we had covered in class thus far. This may make the assignment solutions more complicated/longer than they would have to be. The main purpose of this assignment was to demonstrate understanding of arrays and array traversals. 


## Installation and Execution 

Get the files from GitHub and in your terminal/console move into the src folder of the project. To compile all of the java files for the entire assignment, run the following line: 

``` javac *.java ```

After compiling, run the following line to run the solution to the assignment: 

``` java DNA ```

Note: Make sure to download dna.txt and exoli.txt as well, since these files contain the data needed for the program to run. 

After running the program, the computer will prompt the user for the input file name. The input file used for this assignment is the dna.txt file, which the user should have gotten from the repository. The input file should be typed into the console as follows: 

``` dna.txt ```

Then, the console will program will prompt the user for an output file name. The user select any name they like for this file, but they should make sure to add the '.txt' extension at the end. For example: 

``` output.txt ```

Once the user's desired output file name has been entered, the program will run and the results will be printed in a new file (in this case, 'output.txt'). Nothing will print to the user's console. If the user would like to view the file and read its contents, they can find the file on their computer and open it. It should be saved in the src file of the project. If they would like to have it printed to their console, they can also just run the following line in their console: 

``` cat output.txt ```

## Problem Description 

Since the main focus of this assignment was to show understanding of arrays and their traversals, arrays were the data structures used to store the various data needed for each sequence. Arrays and for-loops were also used to transform the data from one form to another as follows:

* From the original nucleotide sequence string to nucleotide counts 
* From nucleotide counts to mass percentages 
* From the original nucleotide sequence string to codon triplets 

To compute mass percentages, the following were used as the mass of each nucleotide (grams/mol): 

* Adenine (A): 135.128 
* Cytosine (C): 111.103 
* Guanine (G): 151.128 
* Thymine (T): 125.107 
* Junk (-): 100.000 

Note: The dashes representing "junk" regions are excluded from many parts of the computation, but do contribute mass to the total. 

The general logic behind the code works as follows: 

* Each protein's name and sequence of nucleotides are aquired 
* The nucleotide sequence is read and a count is kept in an array of size/length 4 to keep track of the number of As, Cs, Gs, and Ts 
* The counts are then converted into a new array of percentages of mass for each nucleotide using the nucleotide mass values mentioned above 
* After mass percentatge computation is completed, the sequence is broken apart into codons and examined 

Notes to keep in mind and assumptions made when completing the assignment: 

* The input file exists, is readable, and contains valid input, so the user will not be reprompted for any input or output file names 
* Each sequences number of nucleotides (without dashes) will be a multiple of 3
* Nucleotides on a line may be in lowercase, uppercase, or a combintation of both 
* The program should overwrite any existing data in the output file (this is default ``` PrintStream ``` behavior) 

Style Guidelines/Requirements: 
The following are requirements that had to be met for the assignment: 

* Four class constants required (as declared in my code):
    * minNumCod 
        * The minimum numer of codons a valid protein must have, 
        * Integer type variable 
        * Default of 5 - can be changed to change program behavior 
    *  validPercentMass 
        * The percentage of mass from C and G in order for a protein to be valid 
        * Integer type variable 
        * Default of 5 - can be changed to change program behavior 
    * uniNuc 
        * The number of unique nucleotides 
        * Integer type variable 
        * Value is always 4 (A, C, T, G) - never changes 
    * nucPerCod 
        * The number of nucleotides per codon 
        * Interger type variable 
        * Value is always 3 - never changes 
* Required to have at least four nontrivial static methods besides ``` main ``` 
    * Should use parameters and returns, including arrays, as appropriate 
    * Avoid redundancy 
    * No one methd should do too large of a share of the overall task 
* Required method to print all file output for a given potential protein 
    * All output to the file should be done through one method that is called on each nucleotide sequence from the input 
    * Computations are done through other methods and simply passed to this method for printing 
    * Information for each nucleotide printed includes: 
        * Name 
        * Nucleotide Sequence 
        * Nucleotide Counts 
        * Total Mass % 
        * Codon List 
        * Is it a protein? 


