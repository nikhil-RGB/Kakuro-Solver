package util;
import main.KakuroBoard;
public final class TesterClass {

	public static void main(String[] args) 
	{
		String[][] table=KakuroBoard.createFilledTable("0",5,3);
		table[0][0]="-1";
		table[0][1]="0 23";
		table[0][2]="0 30";
		table[1][1]="9";
		table[2][1]="2";
		KakuroBoard.setInput(table);
		KakuroBoard.main(null);

	}

}
