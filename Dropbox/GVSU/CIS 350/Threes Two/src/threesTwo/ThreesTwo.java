package threesTwo;

import javax.swing.SwingUtilities;

public class ThreesTwo {

	/**********************************************************************
	 * Main "Threes" class. Used only for testing purposes.
	 * 
	 *
	 * @author Scott Lumsden
	 * @author Dylan Shoup
	 * @author Anthony Blanton
	 * @version 0.1
	 *********************************************************************/

	public static void main(String[] args) {
		// This is going to be my main class for our group project.
		SwingUtilities.invokeLater(() -> ThreesTwoGUI.playGame());
	}
}
