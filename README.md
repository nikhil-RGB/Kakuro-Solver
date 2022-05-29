# Kakuro-Solver
This repository contains code for a standard issue kakuro-board solver.<br>
This project is currently heavy in development, and is not yet ready for viewing or deployment.<br>
The "main" branch has stable, tested code for the project, check the "development-branch" for code which hasn't yet<br>
been tested.<br>
<br>
<p>Kakuro or Kakkuro is a combinatorial logic puzzle that we consider as the mathematical analogue of the crossword. Kakuro puzzles rely on the summation of numbers from 1-9 to obtain a 1-digit/2-digit sum while sticking to a set of constraints, it’s a regular in most newspapers with puzzle sections, I myself started devising an algorithm to solve this board only after seeing it in the ‘Mumbai Mirror-Sunday Edition’. What many people don’t know is that kakuro in itself is second only to Sudoku in numerical combinatorial puzzles and that while Sudoku doesn’t really have any other synonyms, Kakuro was renamed to cross-sums in some newspapers. But its original name ‘Kakuro’ remains the most popular name to date.<br><br><strong>The Board:</strong></p>
<br>
<image src="https://user-images.githubusercontent.com/68727041/170875536-7dda73b2-ec64-473c-88c3-902a38b67123.png" alt="Kakuro alt-view">
<br>
<p>The Kakuro board’s structure is analogous to a Crossword board, but we have numbers here instead of letters.<br><br>To make explaining the board and by extension, the solving algorithm simpler, I will be defining some terms. Note that these terms are not official definitions but simply a means to reach our target of understanding this board.<br><br>CELL: Any grid box within the Kakuro Board.<br>MASTER CELL: Any cell with a diagonal split between the numbers inside the cell.<br>EMPTY CELL: An empty cell that is yet to be filled with a number.<br>BLANK CELL: An empty cell that is NOT supposed to be filled.<br>Consider the following master cell (15/29):</p>
<image src="https://user-images.githubusercontent.com/68727041/170877739-62999f5d-c381-4cd7-b68c-987f416c0248.png" alt="Cell-Alternative">
<br>
<p>The 4 empty cells to the right of ’29’ must consist of a digit from 1-9 each, no repetitions allowed. Finally, the sum of all these digits should be 29.<br><br>Similarly, for the same master cell</p>
<image src="https://user-images.githubusercontent.com/68727041/170877870-d2f75fc8-527f-4f21-bf22-26f6c5dbb995.png" alt="cell-display" width="50" height="150"><br>
 
  
  




