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

public final class ThreesTwo {

    /******************************************************************
     * Private constructor so an instance cannot be created.
     *****************************************************************/
    private ThreesTwo() { }

    /***********************************************************
     * The main method.  This is the entry point for the game.
     * @param args Command line args.
     ***********************************************************/
    public static void main(final String[] args) {
        // This is going to be my main class for our group project.
        SwingUtilities.invokeLater(() -> ThreesTwoGUI.playGame());
    }
}
