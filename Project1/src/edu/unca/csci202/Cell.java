package edu.unca.csci202;

public class Cell {

	private String mine;
	public boolean hidden;
	public boolean isMine;
	public boolean found;
	
	public String getMine() {
		return mine;
	}

	public void setMine(String mine) {
		this.mine = mine;
	}

	public Cell() {
		mine = "-";
		hidden = true;
		isMine = false;
		found = false;
	}
	
}
