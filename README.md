# assertX_16-by-16-Sudoku-Solver
In this project, you are going to use recursion to solve a variant of Sudoku puzzles.  Rather than the standard 9-by-9 version, your program will solve 16-by-16 (hexadecimal) Sudoku.

# The Input File
•The input file is a plain text file (filename: puzzle.txt).

•There is only one Sudoku puzzle stored in the input file.  However, the puzzle may have more than one solution.

•The input file contains 16 lines of 16 characters each.  A dot ('.') is used to indicate a blank cell.  Therefore, the sample puzzle would be stored in the input file

The puzzle.txt I added has 28 solutions, the output file will display these solutions. The puzzle_no_solution.txt file will produce a blank output file because there are no solutions. I generated a console message to the user to tell them that the puzzle has no solutions.


# The Output File
•The output file is a plain text file (filename: solutions.txt).

•Your program will write all the solutions of the puzzle in the input file to the output file.

•Each solution should be presented the same as in the input file with all the blanks appropriately filled.

•Each solution needs to be numbered (e.g., "Solution 1", "Solution 2", and so on).


## Other notes

I tried two different ways to solve the puzzle. 

## Array Method
In this method I created an array of 16 characters, 0 to 9 and a to f. The logic is to loop through the array and check each cell to see if it will work. If not, make the cell blank ("."). I prefer this method because it's less code, cleaner, and to me easier to understand. It also ran slightly faster than the No Array Method with the puzzle's that had a higher solution count.

## No Array Method
In this method there is no arrays needed. The logic is to loop using the characters 0 to 9, check to see if it will work, If not, make the cell blank ("."). Then we loop using the characters a to f, check to see if it will work, If not, make the cell blank ("."). This method ran faster than the Array Method with Puzzle's that have 1 or no solutions. To some, this method might be easier to read, but it takes up more space.

Either method can be used and will run. The speed between the two is very small. If you care about a 20 to 40 millisecond difference, then the Array Method will be good.
