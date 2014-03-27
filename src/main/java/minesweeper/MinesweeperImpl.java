package minesweeper;

import java.util.*;

import com.despegar.highflight.utils.MatrixUtils;
public class MinesweeperImpl implements Minesweeper{
	
	public Cell[][] matrixCell;
	public int[][] binaryMatrix;
	private int numberOfFlags=0;//I use this properties for the display
	private int numberOfMines; //and calculate the unflagged mines
	private Integer numberCellsWithoutMines;
	private Boolean thereIsAnUncoverMine=false;
	
 	private Boolean thereIsMine (Integer row, Integer column){
		return this.matrixCell[row][column].getValue().equals(-1);
	}
	
	private void createMatrix(Integer row,Integer column){
		int i,j;
		this.matrixCell= new Cell[row][column];
		for (i=0; i<row;i++){
			for (j=0;j<column;j++){
				this.matrixCell[i][j]= new Cell();
			}
		}
	}
	
	private void createBinaryMatrix(){
		int i,j;
		this.binaryMatrix= new int[this.matrixCell.length][this.matrixCell[0].length];
		for (i=0;i<this.matrixCell.length;i++){
			for (j=0;j<this.matrixCell[0].length;j++){
				if (this.matrixCell[i][j].getValue().equals(-1)){
					this.binaryMatrix[i][j]=1;
				}
				else{
					this.binaryMatrix[i][j]=0;
				}
			}
		}
	}
	
	private void putMines (Integer row, Integer column, Integer kMines){
		Random rand = new Random();
		int i,rRow,rColumn;
		for (i=0;i<kMines;i++){
			rRow= rand.nextInt(row);
			rColumn= rand.nextInt(column);
			while (thereIsMine(rRow,rColumn)){ //If the cell already has a mine, choose another random numbers
				rRow= rand.nextInt(row);
				rColumn= rand.nextInt(column);
			}
			this.matrixCell[rRow][rColumn].setValue(-1);
		}
	}
	
	private Set<Matrix2DCellPosition> newSetPosition (int i, int j){
		Matrix2DCellPosition position= new Matrix2DCellPosition();//this method returns a set of the adjacent coordinates
		Set<Matrix2DCellPosition> setPosition = new HashSet<Matrix2DCellPosition>();
		position.setRow(i);
		position.setColumn(j-1);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i);
		position.setColumn(j+1);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i-1);
		position.setColumn(j);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i+1);
		position.setColumn(j);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i-1);
		position.setColumn(j-1);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i-1);
		position.setColumn(j+1);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i+1);
		position.setColumn(j-1);
		setPosition.add(position);
		position= new Matrix2DCellPosition();
		position.setRow(i+1);
		position.setColumn(j+1);
		setPosition.add(position);
		
		return setPosition;
	}
	
	private Boolean isAValidPosition (Integer row, Integer column, Integer rowLimit, Integer columnLimit){
		return (row>-1 && row<rowLimit && column>-1 && column<columnLimit);
	}

	private void assignValueToEmptyCell (Integer row, Integer column){
		int i,j;
		for (i=0;i<row;i++){
			for (j=0;j<column;j++){
				if (!this.matrixCell[i][j].getValue().equals(-1)){	
					Set<Matrix2DCellPosition> setPosition =newSetPosition(i,j);
					for (Matrix2DCellPosition t: setPosition){
						if (isAValidPosition(t.getRow(),t.getColumn(),row,column)){
							if (thereIsMine(t.getRow(),t.getColumn())){
								this.matrixCell[i][j].setValue(this.matrixCell[i][j].getValue() + 1); //if is a valid position and hasn't got a mine, add 1 to the value of the cell
							}
						}
					}		
				}
			}
		}
	}
	
	public MinesweeperImpl (Integer row, Integer column, Integer kMines){
		this.numberOfMines=kMines;
		this.numberCellsWithoutMines=(row*column)-kMines;
		createMatrix(row,column);
		putMines (row,column,kMines);
		assignValueToEmptyCell (row,column);
		createBinaryMatrix();
		
	}
	
	public void uncover(int row, int col) {
		if (this.matrixCell[row][col].getIsCover()){
			if (thereIsMine(row,col)){
				thereIsAnUncoverMine=true;
			}
			else{
				this.matrixCell[row][col].setIsCover(false);
				this.numberCellsWithoutMines--;
			if (this.matrixCell[row][col].getValue().equals(0)){
				Set<com.despegar.highflight.utils.Matrix2DCellPosition> set= MatrixUtils.cascade(this.binaryMatrix,row,col);
				for (com.despegar.highflight.utils.Matrix2DCellPosition t: set){
					if (this.matrixCell[t.getRow()][t.getColumn()].getIsCover()){
					this.matrixCell[t.getRow()][t.getColumn()].setIsCover(false);
					this.numberCellsWithoutMines--;
					}
				}
			}
			}
		}	
	}

	public void flagAsMine(int row, int col) {
		if (this.matrixCell[row][col].getIsCover()){
		this.matrixCell[row][col].setHasAFlag(true);
		this.numberOfFlags++;
		}
	}

	public void clearFlag(int row, int col) {
		this.matrixCell[row][col].setHasAFlag(false);
		this.numberOfFlags--;
	}

	/*private Boolean thereIsAnUncoverMine (){
		int i,j;
		Boolean var=false;
		for (i=0;i<this.matrixCell.length;i++){
			for (j=0;j<this.matrixCell[0].length;j++){
				if (this.matrixCell[i][j].getValue().equals(-1) && this.matrixCell[i][j].getIsCover().equals(false)){
					var=true;
				}
			}
		}
		return var;
	}
	
	private Boolean allCellsWithoutMinesAreUncover(){
		int i,j;
		Boolean var=true;
		for (i=0;i<this.matrixCell.length;i++){
			for (j=0;j<this.matrixCell[0].length;j++){
				if (!this.matrixCell[i][j].getValue().equals(-1) && this.matrixCell[i][j].getIsCover().equals(true)){
					var=false;
				}
			}
		}
		return var;
	}*/
	
	public boolean isGameOver() {
		//return (allCellsWithoutMinesAreUncover() || thereIsAnUncoverMine());
		//If ALL the cells without mines are uncovers OR there are is ANY mine uncover, the game is over
		return (this.numberCellsWithoutMines.equals(0) || this.thereIsAnUncoverMine);
	}

	public boolean isWinningGame() {
		//return (allCellsWithoutMinesAreUncover() && !thereIsAnUncoverMine());
		//If ALL the cells without mines are uncovers AND there ISN'T an uncover mine, you winning the game
		return (this.numberCellsWithoutMines.equals(0) && !this.thereIsAnUncoverMine);
	}

	public void display() {
		int i,j;	
		System.out.println ("Unflagged mines: "+(this.numberOfMines-this.numberOfFlags)+"\n ");
		System.out.print ("\t ");
		for(j=0;j<this.matrixCell[0].length;j++){
		System.out.print ("   "+j);
		}
		System.out.print ("\n");
		for (i=0;i<this.matrixCell.length;i++){
			System.out.print ("\n\t"+i+"|  ");
			for (j=0;j<this.matrixCell[0].length;j++){
				if (this.matrixCell[i][j].getIsCover() && !this.matrixCell[i][j].getHasAFlag()){
					System.out.print ("-   ");
				}
				else{	
					if (this.matrixCell[i][j].getHasAFlag()){
						System.out.print ("F   ");
					}
					else{
						if (thereIsMine (i,j)){
							System.out.print ("M   ");
						}
						else{
							System.out.print (this.matrixCell[i][j].getValue()+"   ");
						}
					}
				}
			}//End second for	
		}//End first for
	}

	public void displayInternal() {
		int i,j;	
		System.out.print ("\t");
		for(j=0;j<this.matrixCell[0].length;j++){
			System.out.print ("   "+j);
		}
		System.out.print ("\n");
		for (i=0;i<this.matrixCell.length;i++){
			System.out.print ("\n\t"+i+"|  ");
			for (j=0;j<this.matrixCell[0].length;j++){
				if (thereIsMine (i,j)){
					System.out.print ("M   ");

				}
				else{
					System.out.print (this.matrixCell[i][j].getValue()+"   ");
				}
			}	
		}
	}

	public void displayRaw() {
		int i,j;	
		for (i=0;i<this.binaryMatrix.length;i++){
			System.out.print ("\n");
			for (j=0;j<this.binaryMatrix[0].length;j++){
					System.out.print (this.binaryMatrix[i][j]+"\t");
			}
		}	
	}


	
	
}//End class
	