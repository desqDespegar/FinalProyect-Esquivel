package minesweeper;

import java.util.*;

import com.despegar.highflight.utils.MatrixUtils;
public class MinesweeperImpl implements Minesweeper{
	
	public Cell[][] matrixCell;
	public int[][] binaryMatrix;
	
	private Boolean thereIsMine (Integer row, Integer column){
		return this.matrixCell[row][column].getValue().equals(-1) ? true: false;
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
			while (thereIsMine(rRow,rColumn)){
				rRow= rand.nextInt(row);
				rColumn= rand.nextInt(column);
			}
			this.matrixCell[rRow][rColumn].setValue(-1);
		}
	}
	
	private Set<Matrix2DCellPosition> newSetPosition (int i, int j){
		Matrix2DCellPosition position= new Matrix2DCellPosition();
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
	
	private Boolean validPosition (Integer row, Integer column, Integer rowLimit, Integer columnLimit){
		return (row>-1 && row<rowLimit && column>-1 && column<columnLimit);
	}

	private void assignValueToEmptyCell (Integer row, Integer column){
		int i,j;
		for (i=0;i<row;i++){
			for (j=0;j<column;j++){
				if (!this.matrixCell[i][j].getValue().equals(-1)){	
					Set<Matrix2DCellPosition> setPosition =newSetPosition(i,j);
					for (Matrix2DCellPosition t: setPosition){
						if (validPosition(t.getRow(),t.getColumn(),row,column)){
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
		createMatrix(row,column);
		putMines (row,column,kMines);
		assignValueToEmptyCell (row,column);
		createBinaryMatrix();
		
	}
	
	public void uncover(int row, int col) {
		if (this.matrixCell[row][col].getIsCover()){
			this.matrixCell[row][col].setIsCover(false);
			if (this.matrixCell[row][col].getValue().equals(0)){
				Set<com.despegar.highflight.utils.Matrix2DCellPosition> set= MatrixUtils.cascade(this.binaryMatrix,row,col);
				for (com.despegar.highflight.utils.Matrix2DCellPosition t: set){
						this.uncover(t.getRow(),t.getColumn());
				
				}
			}
		}	
	}

	public void flagAsMine(int row, int col) {
		this.matrixCell[row][col].setHasAFlag(true);
	}

	public void clearFlag(int row, int col) {
		this.matrixCell[row][col].setHasAFlag(false);
	}

	private Boolean thereIsAnUncoverMine (){
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
	}
	
	public boolean isGameOver() {
		return (allCellsWithoutMinesAreUncover() || thereIsAnUncoverMine()) ? true: false;
	}

	public boolean isWinningGame() {
		return (allCellsWithoutMinesAreUncover() && !thereIsAnUncoverMine()) ? true:false;
	}

	public void display() {
		int i,j;	
		for (i=0;i<this.matrixCell.length;i++){
			System.out.print ("\n");
			for (j=0;j<this.matrixCell[0].length;j++){
				
				if (this.matrixCell[i][j].getIsCover() && !this.matrixCell[i][j].getHasAFlag()){
					System.out.print ("-\t");
				}
				else{	
					if (this.matrixCell[i][j].getHasAFlag()){
						System.out.print ("F\t");
					}
					else{
						if (thereIsMine (i,j)){
							System.out.print ("M\t");
						}
						else{
							System.out.print (this.matrixCell[i][j].getValue()+"\t");
						}
					}
				}
			}//End second for	
		}//End first for
	}

	public void displayInternal() {
		int i,j;	
		for (i=0;i<this.matrixCell.length;i++){
			System.out.print ("\n");
			for (j=0;j<this.matrixCell[0].length;j++){
				if (thereIsMine (i,j)){
					System.out.print ("M\t");

				}
				else{
					System.out.print (this.matrixCell[i][j].getValue()+"\t");
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
	