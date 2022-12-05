package project_3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

	
public class SudokuSolver {
	// Data fields
    private final char[][] board;  // Stores the values in the puzzle.
    private static final int SIZE = 16;  // This is a 16-by-16 Sudoku puzzle.
    private static final char BLANK = '.';  // A character to represent the blank cell.
    private boolean solved; // (true) if the puzzle is solved and (false) for no solutions.
    private int successNum; // Counts the number of solutions for the puzzle.


   // Default Constructor
    public SudokuSolver() { 
    	board = new char[SIZE][SIZE]; }
    
    // Methods

    /** Loads a puzzle from an input file.
    @param scanner: a Scanner that is connected to the input file.
    */
    public void loadData(Scanner scanner) {
    	for (int row = 0; row < SIZE; row++) {
    		String currentRow = scanner.nextLine();
	        for (int col = 0; col < SIZE; col++) { board[row][col] = currentRow.charAt(col); }
	        }
	    }

	    /** Tests whether the current character already appears in the same row.
	        @param row: row to check
	        @param value: the character to search which is either a number or letter.
	        @return: (true) if (value) is found in (row); (false) otherwise.
	    */
	    private boolean appearInRow(int row, char value) {
	        for (int col = 0; col < SIZE; col++) {
	            if (value == board[row][col]) { return true; }
	        }
	        return false;
	    }

	    /** Tests whether the current character already appears in the same column.
	        @param col: column to check
	        @param value: the character to search which is either a number or letter.
	        @return: (true) if (value) is found in (col); (false) otherwise.
	    */
	    private boolean appearInCol(int col, char value) {
	        for (int row = 0; row < SIZE; row++) {
	            if (value == board[row][col]) { return true; }
	        }
	        return false;
	    }

	    /** Tests whether the character already appears in the same 3-by-3 grid.
	        @param row: row index of the current cell
	        @param col: column index of the current cell
	        @param value: character to search
	        @return: (true) if (value) is found in the 3-by-3 grid; (false) otherwise.
	    */
	    private boolean appearInGrid(int row, int col, char value) {
	        int gridStartRow = row / 4 * 4, gridStartCol = col / 4 * 4;
	        for (int i = gridStartRow; i < gridStartRow + 4; i++) {
	            for (int j = gridStartCol; j < gridStartCol + 4; j++) {
	                if (value == board[i][j]) { return true; }
	            }
	        }
	        return false;
	    }

	    /** Finds the row index of the next cell (in row-major order) in the 16-by-16 board.
	        @param row: row index of the current cell
	        @param col: column index of the current cell
	        @return: row index of the next cell (in row-major order) in the 16-by-16 board
	    */
	    private int nextRowIndex(int row, int col) {
	        if (row == SIZE - 1 && col == SIZE - 1) { return -1; }  // No next cell (already reached the last cell of the board)
	        if (col == SIZE - 1) { return row + 1; }  // Next cell should be the leftmost cell of the next row.
	        else { return row; }  // Next cell is still in the same row.
	    }

	    /** Finds the column index of the next cell (in row-major order) in the 16-by-16 board.
	        @param row: row index of the current cell
	        @param col: column index of the current cell
	        @return: column index of the next cell (in row-major order) in the 16-by-16 board
	    */
	    private int nextColIndex(int row, int col) {
	        if (row == SIZE - 1 && col == SIZE - 1) { return -1; }  // No next cell (already reached the last cell of the board)
	        if (col == SIZE - 1) { return 0; }  // Next cell should be the leftmost cell of the next row.
	        else { return col + 1; }  // Next cell is still in the same row.
	    }

	    /** Solves the sudoku puzzle.
	        @param row: row index of the starting cell
	        @param col: column index of the starting cell
	     * @throws IOException 
	    */
	    private void solve(int row, int col) throws IOException {
	    	
	    	if (row == -1 || col == -1) {
	    		
	    		successNum++;
	        	printSolution();
	            solved = false;
	            return;
	        }
	        
	    	// If the current cell is not blank, then directly go to the next cell.
	        if (board[row][col] != BLANK) { solve(nextRowIndex(row, col), nextColIndex(row, col)); }
	        
	        // Current cell is a blank.
	        else {  
	        	for (for (char number = '0'; number <= '9'; number++)) {
	                if (appearInRow(row, number)) { continue; }  // number already appears in the same row.
	                if (appearInCol(col, number)) { continue; }  // number already appears in the same column.
	                if (appearInGrid(row, col, number)) { continue; }  // number already appears in the 3-by-3 grid.
	                board[row][col] = number;  // Fill the cell with the number.
	                
	                // Go to the next cell.
	                solve(nextRowIndex(row, col), nextColIndex(row, col));  

	                // If no solution found, change the current cell back to blank and try the next character.
	                if (!solved) { board[row][col] = BLANK; }
	            }
            for (for (char letter = 'a'; letter <= 'f'; letter++)) {
	                if (appearInRow(row, letter)) { continue; }  // letter already appears in the same row.
	                if (appearInCol(col, letter)) { continue; }  // letter already appears in the same column.
	                if (appearInGrid(row, col, letter)) { continue; }  // letter already appears in the 3-by-3 grid.
	                board[row][col] = letter;  // Fill the cell with the letter.
	                
	                // Go to the next cell.
	                solve(nextRowIndex(row, col), nextColIndex(row, col));  

	                // If no solution found, change the current cell back to blank and try the next character.
	                if (!solved) { board[row][col] = BLANK; }
	            }
	        }
	    }

	    // Wrapper method, which starts at top left of the board. (Row 0, Col 0);
	    public void solve() throws IOException { solve(0, 0); }

	    // Writes all the possible solutions to the output file.
	    public void printSolution() throws IOException {
	    	// Create a new output file and append the text to that file.
	    	// The "true" in the FileOutputStream, allows the program to append to the file a new solution during each iteration after calling printSolution().
	    	FileOutputStream outputFile = new FileOutputStream("solution.txt", true);
	    	PrintWriter writer = new PrintWriter(outputFile);
	    		writer.printf("Solution %d \n\n", successNum);
	    		for (int row = 0; row < SIZE; row++) {
		            for (int col = 0; col < SIZE; col++) { writer.print(board[row][col]); }
		            writer.println();
		        }
	    		writer.println();
	    	
	    	// Close the writer and output file.
	        writer.close();
	        outputFile.close();
	   }
	    
}

