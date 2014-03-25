package minesweeper;

public class Cell{	
	private Boolean isCover;
	private String value;
	private Boolean hasAFlag;
	
	public Cell (){
		isCover= Boolean.valueOf(true);
		value=null;
		hasAFlag= Boolean.valueOf(false);
	}
	
	

}
