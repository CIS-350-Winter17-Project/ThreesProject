package threesTwo;

public class Cell {
	
	private boolean empty;
	private int value;
	// comment on Github. 
	
	public Cell(boolean empty, int value){
		
		// I think that instead of having an empty value, the board cell should be 
		// set to null
		this.empty = empty;
		this.value = value;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
