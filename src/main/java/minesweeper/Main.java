package minesweeper;

import java.util.Scanner;

public class Main {

	public static void main (String [] args){
		Integer row = null,column = null,kMine = null,rowN=null,columnN=null;
		char action=null;
		Scanner scanner = new Scanner (System.in);
		System.out.println ("\n Choose the difficulty \n(1_Beginner; 2_Intermediate; 3_Hard; 0_Personalized");
		switch (scanner.nextInt()){
		case 0:
			System.out.println ("\n Enter the number of Rows:");
			row=scanner.nextInt();
			System.out.println ("\n Enter the number of Columns:");
			column=scanner.nextInt();
			System.out.println ("\n Enter the number of Mines:");
			kMine=scanner.nextInt();
			break;
		case 1:
			row=8;
			column=8;
			kMine=10;
			break;
		case 2:
			row=16;
			column=16;
			kMine=50;
			break;
		case 3:
			row=16;
			column=30;
			kMine=100;
			break;
		}
		System.out.println ("\n There is three different actions:\n If you want to Uncover a mine, then enter 'u' \n If you want to Put a flag, then enter 'f' ,\n If you want to Clear a flag, then enter 'c'");
		System.out.println ("When you enter the action, then select a row and a column");
		MinesweeperImpl game = new MinesweeperImpl(row,column,kMine);
		game.display();
		while (!game.isGameOver()){
			System.out.println ("\n What do you want to do?(enter the corresponding letter): ");
			action= scanner.next();
			System.out.println ("\n Enter the row: ");
			rowN= scanner.nextInt();
			System.out.println ("\n Enter the column: ");
			columnN= scanner.nextInt();
			switch (action){
			case
			}
			
		}
		
		
		
	}//End main
}//End class
