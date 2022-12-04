package project_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws Exception {
    	
    	// Open the input file, create the output file, and open the scanner.
        FileInputStream inputFile = new FileInputStream("puzzle_7.txt");
        Scanner scanner = new Scanner(inputFile);
        
        // When you run this program again the main will overwrite this file.
    	// If we don't overwrite this file, then the solutions will appear again and again.
        FileOutputStream outputFile = new FileOutputStream("solution.txt", false); //  The false overwrites the output file.
        PrintWriter writer = new PrintWriter(outputFile);
       
        // Create a new puzzle.
        SudokuSolver puzzle = new SudokuSolver();
        
        // Read the puzzle from the input file.
        puzzle.loadData(scanner);
        
        // Then solve the puzzle
        puzzle.solve();
        
        // This next section will read the output file and outputs a message for the user.
        String line;
        int successNum = 1;
        
        // Open the Buffer Reader to read the output file.
        BufferedReader br = new BufferedReader(new FileReader("solution.txt"));  
        
        // If the output file is blank, this means no solutions.
		if (br.readLine() == null) {
		    System.out.println("This puzzle has no solutions! The text file will be blank. :(");
		}
		else { // IF solutions are found, notify the user.
			// This reads each line that has the word "Solution" then adds to the total. For our console statement.
			while ((line = br.readLine()) != null) { 
				if (line.startsWith("Solution")) {
					successNum++;
				}
			}
			System.out.println( successNum + " solution(s) were found! YAY! Check the \"solutions.txt\" file to see the output. :)");
		}
		// End of Buffer Reader.
		
        // Close the input and output file, close the writer, scanner, and buffer reader.
		br.close();
		writer.close();
    scanner.close();
    inputFile.close();
    outputFile.close();
        
    }
}


