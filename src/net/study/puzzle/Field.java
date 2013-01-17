package net.study.puzzle;

public class Field {
	private Cell [] field;
	private int side = 4;
	private int fieldSize;
	
	public Field (){
		fieldSize = side * side;
		field = new Cell[fieldSize];
		init();
	}
	
	private void init(){
		int [] allowedPuzzles = new int [fieldSize];
		
		for (int i = 0; i < fieldSize; i++){
			allowedPuzzles[i] = i;
		}
		
		for (int i = fieldSize; i > 0; i--){
			int index = (int) Math.round(Math.random() * 1000) % i;
			
			field[i - 1] = new Cell(allowedPuzzles[index]);
			allowedPuzzles[index] = allowedPuzzles[i - 1];
		}
		
		checkMovableCells();
	}
	
	// Returns -1 if not Movable or index where puzzle can be moved
	private int isMovableTo(int name){
		// Empty cell
		if (name == 0){
			return -1;
		}

		int canMoveTo = -1;
		int puzzleIndex = getIndex(name);
				
		// Not Left border
		if (puzzleIndex - 1 >= 0 && (puzzleIndex - 1) % side != side -1){
			if(field[puzzleIndex - 1].getName() == 0){
				canMoveTo = puzzleIndex - 1;
			}
		}
		
		// Not Right border
		if ((puzzleIndex + 1) % side != 0){
			if(field[puzzleIndex + 1].getName() == 0){
				canMoveTo = puzzleIndex + 1;
			}
		}

		// Not Top border
		if (puzzleIndex - side >= 0){
			if(field[puzzleIndex - side].getName() == 0){
				canMoveTo = puzzleIndex - side;
			}
		}

		// Not Bottom border
		if (puzzleIndex + side < fieldSize){
			if(field[puzzleIndex + side].getName() == 0){
				canMoveTo = puzzleIndex + side;
			}
		}
		return canMoveTo;
	}
	
	public boolean canSwap(int from, int to){
		boolean bSwap = false;
		int diff = Math.abs(from - to);
		
		if (diff == side || (diff == 1 && (from / side) == (to / side))){
			bSwap = true;
		}
		return bSwap;
	}
	
	public int getIndex(int name){
		for(int i = 0; i < field.length; i++){
			if(field[i].getName() == name)
				return i;
		}
		return -1;
	}
	
	public boolean isVictory(){
		for(int i = 0; i < field.length - 1; i++){
			if(field[i].getName() != i+1){
				return false;
			}
		}
		return true;
	}
	
	public int getSide(){
		return side;
	}
	
	public int getFieldSize() {
		return fieldSize;
	}

	public Cell [] getField(){
		return field;
	}
	
	public boolean move(int name){
		boolean moved = false;
		int emptyIndex = getIndex(0);
		int puzzleIndex = getIndex(name);

		if (field[puzzleIndex].isMovable()){
			field[puzzleIndex].setName(0);
			field[emptyIndex].setName(name);
			moved = true;
			checkMovableCells();
		}
		
		return moved;
	}

	public void shuffle(){
		init();
	}
	
	private void checkMovableCells(){
		int emptyIndex = getIndex(0);
		
		for(Cell cell : field){
			int puzzleIndex = getIndex(cell.getName());
	
			if (canSwap(puzzleIndex, emptyIndex) == true){
				cell.setMovable(true);
			} else {
				cell.setMovable(false);
			}
		}
	}
}
