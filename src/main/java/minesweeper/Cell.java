package minesweeper;

public class Cell{	
	private Boolean isCover; //If the cell is cover, it is TRUE, else it is FALSE
	private Integer value;// It is the number of mines has around. But it is a Mine the value is -1
	private Boolean hasAFlag;//If the cell is flagged, it is TRUE, else it is FALSE
	//Getter, setter and the constructor
	public Boolean getIsCover() {
		return isCover;
	}

	public void setIsCover(Boolean isCover) {
		this.isCover = isCover;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Boolean getHasAFlag() {
		return hasAFlag;
	}

	public void setHasAFlag(Boolean hasAFlag) {
		this.hasAFlag = hasAFlag;
	}

	public Cell (){
		this.isCover= true;
		this.value=0;
		this.hasAFlag= false;
	}
	
	

}
