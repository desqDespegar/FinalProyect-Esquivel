package minesweeper;

import java.util.Scanner;

public class Main {

	public static void main (String [] args){
		Integer row,column,kMine,rowN,columnN;
		Character action;
		Scanner scanner = new Scanner (System.in);
		String answer="y";
		while (answer.equals("y")){
		System.out.println ("\n Choose the difficulty \n(1_Beginner; 2_Intermediate; 3_Hard; 0_Personalized)");
		switch (scanner.nextInt()){
		case 0:
			System.out.println ("\n Enter the number of Rows:");
			row=scanner.nextInt();
			System.out.println ("\n Enter the number of Columns:");
			column=scanner.nextInt();
			System.out.println ("\n Enter the number of Mines (less than "+row*column+"):");
			kMine=scanner.nextInt();
			break;
		case 1:
			row=4;
			column=4;
			kMine=4;
			break;
		case 2:
			row=8;
			column=8;
			kMine=12;
			break;
		case 3:
			row=8;
			column=10;
			kMine=30;
			break;
		default:
			System.out.println ("Wrong option!");
			row=0;
			column=0;
			kMine=0;
			break;
			
		}
		System.out.println ("\n There is three different actions:\n If you want to Uncover a mine, enter 'u' \n If you want to Put a flag, enter 'f' ,\n If you want to Clear a flag, enter 'c')");
		System.out.println ("\n When you enter the action, then select a row and a column");
		MinesweeperImpl game = new MinesweeperImpl(row,column,kMine);       
		game.display();
		/*System.out.println ("\n Display internal");
		game.displayInternal();		I only use this if i needed, for debug purposes
		System.out.println ("\nDisplay raw");
		game.displayRaw();*/
		while (!game.isGameOver()){
			System.out.println ("\n What do you want to do?('u','f' or 'c'): ");
			action= scanner.next().charAt(0);
			System.out.println ("\n Enter the row (between 0 and "+(row-1)+"): ");
			rowN= scanner.nextInt();
			System.out.println ("\n Enter the column (between 0 and "+(column-1)+"): ");
			columnN= scanner.nextInt();
			switch (action){
			case ('u'):
				game.uncover(rowN, columnN);
				break;
			case ('f'):
				game.flagAsMine(rowN, columnN);
				break;
			case ('c'):
				game.clearFlag(rowN, columnN);
				break;
			default: 
				System.out.println ("\n Wrong option! \n");
			}
			game.display();
		}
		if (game.isWinningGame()){
			System.out.println ("\n CONGRATULATION! YOU WIN! :D");
		}
		else{
				System.out.println ("\n HA HA! LOSER! \n");	
				game.displayInternal();
			}
		System.out.println ("\n\n Do you want to play again?(y/n): ");
		answer= scanner.next();
		}
		
	}//End main
}//End class
