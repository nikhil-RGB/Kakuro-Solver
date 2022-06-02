package main;

//NOTE: A board configuration is always "RIGHT_SUM DOWN_SUM"
import java.util.*;
public final class KakuroBoard 
{
	private static String[][] board;//This board consists of all kakuro combination cells.
	public static final String BLANK="0";//This marks which cell has no digit in it.
    public static final String BLOCK="-1";//This marks which cell should be marked as blocked.
    //Constructs a Kakuro Board of dimensions X*Y:
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
    	for(int i=x+1;x<board.length;++i)
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
    //main method only for testing purposes- testing
    public static void main(String[] args)
    {
    	String[][] table=KakuroBoard.createFilledTable("0",5,3);
        KakuroBoard kb=new KakuroBoard(table);
        kb.block(0, 0);
        kb.insertAt(0, 23, 0, 1);
        kb.insertAt(0,30,0,2);
        kb.printBoard();
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
    
}
