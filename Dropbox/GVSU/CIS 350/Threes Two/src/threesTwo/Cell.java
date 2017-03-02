package threesTwo;

/**********************************************************************
 * These Cells are what the game board is composed of, and contains the
 * int value of the pieces you are attempting to combine. 
 * 
 * @author Anthony Blanton
 * @author Scott Lumsden
 * @author Dylan Shoup
 * 
 *@version 0.1 March 3, 2017
 *********************************************************************/
public class Cell {
	
	/** Tells the Cell next to it if it is empty or not */
	private boolean empty;
	
	/** The value that is displayed on the screen in that Cell */
	private int value;
	
	/******************************************************************
	 * Constructor that creates each cell. 
	 * @param empty tells the board whether there is a cell there. If
	 * true, then it is empty, if false, then it has a cell.
	 * @param value the value that that cell contains. 
	 *****************************************************************/
	public Cell(boolean empty, int value){
		
		this.empty = empty;
		this.value = value;
	}
	
	/******************************************************************
	 * Returns the empty status. If true, then the Cell is empty. If
	 * false, then the cell is "active". 
	 * @return the status of whether it is empty or not. 
	 *****************************************************************/
	public boolean isEmpty() {
		return empty;
	}
	
	/******************************************************************
	 * Sets the cell to either empty, or not empty. 
	 * @param empty sets the cell to either empty or not empty. If
	 * empty, another cell can take it's place. 
	 *****************************************************************/
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	/******************************************************************
	 * Returns the value of the cell that appears on the screen. 
	 * @return the int value that is contained within that cell. 
	 *****************************************************************/
	public int getValue() {
		return value;
	}
	
	/******************************************************************
	 * Changes the value that is contained within that cell. 
	 * @param value the new value that will be contained within the
	 * cell.
	 *****************************************************************/
	public void setValue(int value) {
		this.value = value;
	}

}
