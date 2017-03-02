package threesTwo;

import javax.swing.SwingUtilities;

/**********************************************************************
 * Main "Threes" class. Used to initiate the board, and ultimately 
 * run the game. 
 * 
 *
 * @author Scott Lumsden
 * @author Dylan Shoup
 * @author Anthony Blanton
 * @version 0.1 March 3, 2017
 *********************************************************************/

public class ThreesTwo {
	
	/******************************************************************
	 * Begins the Threes game when run. 
	 * @param args NA
	 *****************************************************************/

	public static void main(String[] args) {
		// This is going to be my main class for our group project.
		SwingUtilities.invokeLater(() -> ThreesTwoGUI.playGame());
	}
}
