package com.cis350.threesTwo;

import javax.swing.SwingUtilities;

/**********************************************************************
 * Main "Threes" class. Used to initiate the board, and ultimately
 * run the game.
 *
 * @author Scott Lumsden
 * @author Dylan Shoup
 * @author Anthony Blanton
 *
 * @version 0.2 April 19, 2017
 *********************************************************************/

public final class ThreesTwo {

    /******************************************************************
     * Private constructor so an instance cannot be created outside
     * of this class.
     *****************************************************************/
    private ThreesTwo() { }

    /***********************************************************
     * The main method.  This is the entry point for the game.
     * @param args Command line args.
     ***********************************************************/
    public static void main(final String[] args) {
        // This is going to be my main class for our group project.
        SwingUtilities.invokeLater(() -> new ThreesTwoGUI().playGame());
    }
}
