package main;
import java.util.*;
public final class KakuroBoard 
{
	private static String[][] board;//This board consists of all kakuro combination cells
	public static final String BLANK="0";//This marks which cell has no digit in it.
    public static final String BLOCK="-1";//This marks which cell should be marked as blocked
    //Constructs a Kakuro Board of dimensions X*Y
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
    	String[] toRet=new String[2];
    	
    	return toRet;
    }
    
}
