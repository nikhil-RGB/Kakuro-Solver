package main;
import java.awt.Point;
//NOTE: A board configuration is always "RIGHT_SUM DOWN_SUM"
import java.util.*;
//TESTED marks a function as something which runs correctly after testing
//UNTESTED marks a function as untested
//INCORRECT marks a function as outputting incorrect values
import util.UtilityMethods;
public final class KakuroBoard 
{
	public static String[][] test_table1= {
			{"-1","0 15","0 21","0 13","0 15","0 30","-1"},{"34 0","0","0","0","0","0","0 35"},{"39 0","0","0","0","0","0","0"},
			{"4 0","0","0","-1","8 0","0","0"},{"7 0","0","0","0 13","9 9","0","0"},{"35 0","0","0","0","0","0","0"},
			{"-1","15 0","0","0","0","0","0"}
	};
	public static String[][] solving_table;//This is the board the solver will attempt to solve
	private java.awt.Point checkpoint; //Current solve point in the board
	private  String[][] board;//This board consists of all kakuro combination cells.
	public static final String BLANK="0";//This marks which cell has no digit in it.
    public static final String BLOCK="-1";//This marks which cell should be marked as blocked.
    public static void setInput(String[][] table)
    {
    	KakuroBoard.solving_table=table;
    }
    //Constructs a Kakuro Board of dimensions X*Y:
    {
    	this.checkpoint=new Point(0,0);
    }
    public KakuroBoard(int x,int y)
    {
    	board=new String[x][y];
    	KakuroBoard.fillStringTable(board,BLANK);
    }
    //Constructs a KakuroBoard using the board provided
    public KakuroBoard(String[][] board)
    {
    	this.board=board;
    }
    //Creates and returns an empty table (x*y) filled with filler
    public static String[][] createFilledTable(String filler,int x,int y)
    {
    	String[][] table=new String[x][y];
        for(int i=0;i<x;++i)
        {
        	for(int j=0;j<y;++j)
        	{
        		table[i][j]=filler;
        	}
        }
        return table;
    }
    //fills a String[][] table with a particular filler value
    public static void fillStringTable(String[][] table,String filler)
    {
    	for(int i=0;i<table.length;++i)
    	{
    		for(int j=0;j<table[i].length;++j)
    		{
    			table[i][j]=filler;
    		}
    	}
    }
    //Inserts a particular configuration at a certain cell in the board
    public void insertAt(int right,int down,int x,int y)
    {
    	this.board[x][y]=right+" "+down;
    }
    //blocks off a certain cell
    public void block(int x,int y)
    {
    	this.board[x][y]=KakuroBoard.BLOCK;
    }
    //marks a particular cell as open to be filled with any particular number
    public void markEmpty(int x,int y)
    {
    	this.board[x][y]=KakuroBoard.BLANK;
    }
    //reads the digits, right and down at a specefic spot.
    public String[] readAt(int x,int y)
    {   
    	String cell=this.board[x][y];
    	if(this.isBlocked(x, y)||this.isFillable(cell))
    	{return null;}
    	String[] toRet=new String[] {"",""};
    	//first reading right
    	LAB:
    	for(int j=y+1;j<board[x].length;++j)
    	{
    	 String text=this.board[x][j];
    	 if(!this.isFillable(text))
    	 {break LAB;}
    	 toRet[0]+=text;
    	}
    	//over here down-reading
    	LAB1:
    	for(int i=x+1;i<board.length;++i)
    	{
    		String text=this.board[i][y];
    		if(!this.isFillable(text))
    		{break LAB1;}
    		toRet[1]+=text;
    	}
    	 
    	return toRet;
    }
    //This method returns true if cell is a fill-able cell, false in all other cases
    public boolean isFillable(String th)
    {
     if(isPositiveNumber(th)) 
     {return true;}
     return false;
    }
 
    //This method fills in a value at a specefic index
	public void fill(String value,int x,int y)
	{   
		int val=Integer.parseInt(value);
		if(val<0)
		{throw new IllegalArgumentException("Invalid Argument");}
		this.board[x][y]=value;
	}
	//returns true if passed int is a number
    public static boolean isPositiveNumber(String num)
    {
      try
      {
    	  int nn=Integer.parseInt(num);
    	  if(nn<0)
    	  {
    		  throw new NumberFormatException();
    	  }
    	  return true;
      }
      catch(NumberFormatException ex)
      {return false;}
    }
    //This method checks to see if a particular block is blocked
    public boolean isBlocked(int x,int y)
    {
    	String cell=this.board[x][y];
    	if(cell.equals("-1"))
    	{return true;}
      return false;	
    }
    //main method for solving the board-call the setInput to set the board for solving before calling this method.
    public static void main(String[] args)
    {
    	//String[][] table=KakuroBoard.createFilledTable("0",5,3);
    	String[][] table=KakuroBoard.solving_table;
    	
    	KakuroBoard kb=new KakuroBoard(table);
       // KakuroBoard kb=new KakuroBoard(KakuroBoard.test_table1);
       // kb.block(0, 0);
       // kb.insertAt(0,23,0,1);
       //kb.insertAt(0,30,0,2);
       //kb.fill("9",1,1);
       //kb.fill("2",2,1);
       kb.printBoard();
       //String[] results=kb.readAt(1, 1);
        
        	//System.out.println("Right: "+results[0]);
        	//System.out.println("Down: "+results[1]);
     //  ArrayList<KakuroBoard> finals=kb.stepSolve(0, 1);
     //  for(KakuroBoard fin:finals)
      // {fin.printBoard();}
      // System.out.println(finals.size());
        KakuroBoard solved=kb.solveProcessStandard();
        solved.printBoard();
     }
    //This method can be used for printing all values in the board, i.e. testing
    public void printBoard()
    {
    	for(int i=0;i<this.board.length;++i)
    	{
    		for(int j=0;j<this.board[i].length;++j)
    		{
    			System.out.print(board[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
    //This method fills in a solution for a particular grid box, and creates clones for every possible solution
    //Calling this multiple m*n times, where m and n are the dimensions of the board.
    //Step solve was bugged because an incorrect variable was being passed as a constraint to the filter function.
    public ArrayList<KakuroBoard> stepSolve(int x,int y)
    {
    //No exception checking here- erroneous parameter input WILL cause an IndexOutOfBoundsException
     String[] cell_adjs=this.readAt(x,y); 
     this.checkpoint=new Point(x,y);
     if(cell_adjs==null)
      {return null;}
     String cell=this.board[x][y];
     Scanner reader =new Scanner(cell);
     String right=reader.next();
     
    // System.out.println("Right is"+right);
    // System.out.println("Right numbers is: "+cell_adjs[0]);
     
     String down=reader.next();
     
    // System.out.println("Down is "+down);
     //System.out.println("Down numbers are: "+cell_adjs[1]);
     
     reader.close();
     ArrayList<Long> right_sols=UtilityMethods.permute(Integer.parseInt(right), cell_adjs[0].length());
     right_sols=UtilityMethods.filter(right_sols, cell_adjs[0]);
     ArrayList<Long> down_sols=UtilityMethods.permute(Integer.parseInt(down),cell_adjs[1].length());
     down_sols=UtilityMethods.filter(down_sols, cell_adjs[1]);
     
    // System.out.println("Right solutions");
     //right solutions
    // for(Long soln:right_sols)
     //{System.out.println(soln);}
     //down solutions
     //System.out.println("Down solutions");
     //for(Long soln:down_sols)
     //{System.out.println(soln);}
     
     //ArrayList<Long> first=(right_sols.size()>0)?right_sols:down_sols;
     //boolean flag=(first==right_sols)?true:false;//true if first selected solution is the right-oriented String solution, 
     //false if it is a downward-oriented String solution
     // ArrayList<Long> second=(first==right_sols)?down_sols:right_sols;//second config to be filled in
     //commented out lines are unrequired.
     
     ArrayList<KakuroBoard> finals=new ArrayList<>(0);
     for(int i=0;i<right_sols.size();++i)
     {
    	 String current=right_sols.get(i).toString();
    	 KakuroBoard kb=this.clone();
    	 kb.fillSolution(current, x, y,"RIGHT");
    	 finals.add(kb);
       	 //fill solution in at specified location
     }
     if(finals.size()==0)
     {
    	 finals.add(this.clone());
     }
     ArrayList<KakuroBoard> final2=new ArrayList<>(0);
     for(int i=0;i<finals.size();++i)
     {
    	 KakuroBoard toView=finals.get(i);
    	 for(int j=0;j<down_sols.size();++j)
    	 {
    		 KakuroBoard toOperate=toView.clone();
    		 String soln=down_sols.get(j).toString();
    		 toOperate.fillSolution(soln, x, y,"DOWN");
    		 final2.add(toOperate);
    	 }
     }
     finals.clear();
     
     //for(KakuroBoard solve:final2)
     //{
    //	solve.printBoard();
     //}
     
     return final2;     
}
    
    //This method fills in the solution at specified grid box-can throw exception for erroneous parameters
    public void fillSolution(String solution,int x,int y,String orient)
    {
      if(orient.equals("RIGHT"))
      {
    	  for(int i=y+1,ch=0;ch<solution.length();++i,++ch)
    	  {
    		  this.fill(solution.charAt(ch)+"",x,i);
    	  }
      }
      else //downwards orientation
      {
    	  for(int i=x+1,ch=0;ch<solution.length();++i,++ch)
    	  {
    		  this.fill(solution.charAt(ch)+"",i, y);
    	  }
      }
    }
    @Override()
    public KakuroBoard clone()
    {
    	KakuroBoard kb=new KakuroBoard(cloneDDA(this.board));
    	return kb;
    }
    public static String[][] cloneDDA(String[][] table)
    {
    	String[][] DDA=new String[table.length][table[0].length];
    	for(int i=0;i<DDA.length;++i)
    	{
    		for(int j=0;j<DDA[i].length;++j)
    		{
    			DDA[i][j]=table[i][j];
    		}
    	}
    	return DDA;
    }
    
    public KakuroBoard solveProcessStandard()
    {
    	int x=0;
    	int y=0;
    	ArrayList<KakuroBoard> kbs=new ArrayList<>(0);
    	kbs.add(this);
    	SOLVER:
    	while(kbs.size()!=0&&(!kbs.get(0).isSolved()))
    	{
    		//Kakuro board step-by-step solving
    		KakuroBoard current=kbs.get(0);
    		//System.out.println("Step solve request values: x is "+x+"y is "+y);
    		ArrayList<KakuroBoard> kb=current.stepSolve(x,y);
    		if(kb!=null)
    		{
    		//System.out.println("Size= "+kb.size()+" x is "+x+" y is "+y);
    		kbs.addAll(kb);
    		if(kbs.size()==1)
    		{break SOLVER;}
    		kbs.remove(0);
    		}
    		y++;
    		if(y>(board[0].length-1))
    		{
    			y=0;
    			++x;
    			if(x>(board.length-1))
    			{
    			 break SOLVER;
    			}
    		}
    		this.checkpoint=new Point(x,y);
    	}
    	//System.out.println("Final size= "+kbs.size());
    	return kbs.get(kbs.size()-1);
    }
    //This method checks if the board has been solved
    public boolean isSolved()
    {
    	if(((this.checkpoint.x==(this.board.length-1)))&&(this.checkpoint.y==(this.board[0].length-1)))
    	{return true;}	
    	return false;	
    }
    //This method prints an Object SDA
    public void printSDA(String[] sda)
    {
    	for(String ss:sda)
    	{
    		System.out.print(ss+ " ");
    	}
    	System.out.println();
    }

}