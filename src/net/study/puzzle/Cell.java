package net.study.puzzle;

public class Cell {
	private int name;
	private boolean isMovable;
	
	public Cell(){
		
	}
	
	public Cell(int name){
		this.name = name;
	}
	
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public boolean isMovable() {
		return isMovable;
	}
	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}
	
	
}
