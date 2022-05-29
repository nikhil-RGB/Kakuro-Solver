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
 <br>
 <p>We see that the number ’15’ is oriented/facing downwards, with 5 empty cells to fill. This means that each of these grid cells must be filled with a number between 1 and 9 (both limits inclusive) such that their sum is equivalent to 15, with no repetitions allowed.<br><br>Going by this pattern, the solution of the board used as an example above is:</p>
 <br>
 <image src="https://user-images.githubusercontent.com/68727041/170890385-0eb7e3a5-50bc-4016-b7ee-0fcde91cba35.png" alt="alternate" width="200" height="200">
 <br>
 <p>Looking at this board, you can see how subsequently the numbers are filled into empty cells by using the master cells’ numbers’ as a summation guideline and  
respecting the constraint of every digit in the summation sequence is unique, single-digit and non-zero.<br><br><strong>The Solving Algorithm:<br></strong>Firstly, let’s see how we can calculate the solutions to fill in with respect to a particular master cell:<br><br>Here’s a method is written in JAVA which in short is my choice of programming language:

<br><br>

```java
public static ArrayList<Long> permute(int sum,int gboxes)
{
	ArrayList<Long> arr=new ArrayList<>(0);
	if(sum==0||gboxes<2)
	{return arr;}
	String min="1";
	String max="9";
	for(int k=0;k<gboxes-1;++k)
	{
		min+=0;
		max+=9;
	}
	long mini=Long.parseLong(min);
	long maxi=Long.parseLong(max);
	for(;mini<=maxi;++mini)
	{
		if(isUnique(mini)&&(summate(mini)==sum))
		{
			arr.add(mini);
		}
	}
return arr;
}  
```
This method basically returns a set of solutions for a particular number of empty cells and a master cell sum. For example, if the master cell had a sum value of 4 and the number of empty cells 2, we would get the following values in return 13,31. These are possible solution strings that we can fill into the empty cells as such:</p>
 
 <image src="https://user-images.githubusercontent.com/68727041/170891754-7ee94d4b-0816-4f51-bd8f-1a384afa74d1.png">
  <br>
  And now, in order to complete, a method to only select solution strings for the empty cells which will conform to some of the filled cells solutions.<br>
  
  ```java
  //Returns all numerical combinations which comply with the constraint so provided
public static ArrayList<Long> constraintSort(ArrayList<Long> solns,String constraint)
{
ArrayList<Long> sorted=new ArrayList<>(0);
for(Long cnum:solns)
{
String s=cnum.toString();
for(int k=0;k<s.length();++k)
{
char og=s.charAt(k);
char n=constraint.charAt(k);
if(!((og!=n)&&(n!=’0′)))
{sorted.add(cnum);}
}
}
return sorted;
}
```
<br>
Now we may use the <a href="https://github.com/nikhil-RGB/sudoku/blob/main/README.md">sudoku solver</a> technique to mix and match solutions till we get the correct solution configuration.




