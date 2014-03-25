package minesweeper;

import java.util.*;
public class MinesweeperImpl implements Minesweeper{
	
	public Cell[][] matrixCell;
	public Integer[][] binaryMatrix;
	
	private Boolean thereIsMine (Integer row, Integer column){
		return this.matrixCell[row][column].getValue().equals(-1) ? true: false;
	}
	
	private void createMatrix(Integer row,Integer column){
		int i,j;
		for (i=0; i<row;i++){
			for (j=0;j<column;j++){
				this.matrixCell[i][j]= new Cell();
			}
		}
	}
	
	private void createBinaryMatrix(){
		int i,j;
		for (i=0;i<this.matrixCell.length;i++){
			for (j=0;j<this.matrixCell[0].length;j++){
				if (this.matrixCell[i][j].getValue().equals(-1)){
					this.binaryMatrix[i][j]=0;
				}
				else{
					this.binaryMatrix[i][j]=1;
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
	
	private Set<Matrix2DCellPosition> newSetPosition (Set<Matrix2DCellPosition> setPosition, int i, int j){
		Matrix2DCellPosition position= new Matrix2DCellPosition();
		position.setColumn(i);
		position.setRow(j-1);
		setPosition.add(position);
		position.setColumn(i);
		position.setRow(j+1);
		setPosition.add(position);
		position.setColumn(i-1);
		position.setRow(j);
		setPosition.add(position);
		position.setColumn(i+1);
		position.setRow(j);
		setPosition.add(position);
		position.setColumn(i-1);
		position.setRow(j-1);
		setPosition.add(position);
		position.setColumn(i-1);
		position.setRow(j+1);
		setPosition.add(position);
		position.setColumn(i+1);
		position.setRow(j-1);
		setPosition.add(position);
		position.setColumn(i+1);
		position.setRow(j+1);
		setPosition.add(position);
		
		return setPosition;
	}
	
	private Boolean validPosition (Integer row, Integer column, Integer rowLimit, Integer columnLimit){
		return (row>-1 && row<rowLimit && column>-1 && column<columnLimit) ? true:false;
	}

	private void assignValueToEmptyCell (Integer row, Integer column){
		int i,j;
		for (i=0;i<row;i++){
			for (j=0;j<column;j++){
				Set<Matrix2DCellPosition> setPosition = new HashSet<Matrix2DCellPosition>();
				for (i=0;i<row;i++){
					for (j=0;j<column;j++){
						setPosition= newSetPosition(setPosition,i,j); 
						
						for (Matrix2DCellPosition t: setPosition){
							if (validPosition(t.getRow(),t.getColumn(),row,column)){
								if (thereIsMine(t.getRow(),t.getColumn())){
									this.matrixCell[i][j].setValue(this.matrixCell[i][j].getValue()+1); //if is a valid position and hasn't got a mine, add 1 to the value of the cell
								}
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
		
		
	}

	public void flagAsMine(int row, int col) {
		this.matrixCell[row][col].setHasAFlag(true);
	}

	public void clearFlag(int row, int col) {
		this.matrixCell[row][col].setHasAFlag(false);
	}

	public boolean isGameOver() {
		return false;
	}

	public boolean isWinningGame() {
		return false;
	}

	public void display() {
		int i,j;	
		for (i=0;i<this.matrixCell.length;i++){
			System.out.println ("\n");
			for (j=0;j<this.matrixCell[0].length;j++){
				if (this.matrixCell[i][j].getIsCover()){
					System.out.println ("-\t");
				}
				else{
					if (thereIsMine (i,j)){
						System.out.println ("M\t");
					}
					else{
						System.out.println (this.matrixCell[i][j].getValue()+"\t");
					}
				}
			}	
		}
	}

	public void displayInternal() {
		int i,j;	
		for (i=0;i<this.matrixCell.length;i++){
			System.out.println ("\n");
			for (j=0;j<this.matrixCell[0].length;j++){
				if (thereIsMine (i,j)){
					System.out.println ("M\t");

				}
				else{
					System.out.println (this.matrixCell[i][j].getValue()+"\t");
				}
			}	
		}
	}

	public void displayRaw() {
		int i,j;	
		for (i=0;i<this.binaryMatrix.length;i++){
			System.out.println ("\n");
			for (j=0;j<this.binaryMatrix[0].length;j++){
					System.out.println (this.binaryMatrix[i][j]+"\t");
			}
		}	
	}


	
	
}//End class
	
	  




}//End Class