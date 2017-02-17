package threesTwo;

import javax.swing.*;

public class ThreesTwo extends JFrame {

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
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			ThreesTwoGUI.playGame();
    		}
    	});
	}
}
