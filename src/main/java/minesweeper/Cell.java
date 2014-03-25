package minesweeper;

public class Cell{	
	private Boolean isCover;
	private Integer value;
	private Boolean hasAFlag;
	
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
		this.isCover= Boolean.valueOf(true);
		this.value=0;
		this.hasAFlag= Boolean.valueOf(false);
	}
	
	

}
