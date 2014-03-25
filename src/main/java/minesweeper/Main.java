package minesweeper;

import java.util.Scanner;

public class Main {

	public static void main (String [] args){
		Integer row = null,column = null,kMine = null;
		Scanner scanner = new Scanner (System.in);
		System.out.println ("\n Elija el nivel de dificultad \n(1_Principiante; 2_Intermedio; 3_Dificil; 0_Personalizado");
		switch (scanner.nextInt()){
		case 0:
			System.out.println ("\n Ingrese la cantidad de Filas:");
			row=scanner.nextInt();
			System.out.println ("\n Ingrese la cantidad de Columnas:");
			column=scanner.nextInt();
			System.out.println ("\n Ingrese la cantidad de Minas:");
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
		MinesweeperImpl game = new MinesweeperImpl(row,column,kMine);
	
		
		
		
	}//End main
}//End class
