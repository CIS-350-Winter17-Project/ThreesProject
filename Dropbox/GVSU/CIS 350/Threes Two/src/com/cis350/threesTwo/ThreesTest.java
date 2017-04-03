package com.cis350.threesTwo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/***********************************************
 * These are the tests for the game.
 *
 * @author Anthony Blanton
 * @author Scott Lumsden
 * @author Dylan Shoup
 ***********************************************/

public class ThreesTest {

    /** For the Checkstyle warnings. */
    private final int three = 3;

    /** For the Checkstyle warnings. */
    private final int four = 4;

    /** For the Checkstyle warnings. */
    private final int one27 = 127;

    /** For the Checkstyle warnings. */
    private final int threeTwo = 32;

    /************************
     * Test is cell is empty.
     ************************/
    @Test
    public void cellBooleanTest() {
        Cell test = new Cell(false, 0);
        assertEquals(test.isEmpty(), false);
        test.setEmpty(true);
        assertEquals(test.isEmpty(), true);

        test = new Cell(true, 0);
        assertEquals(test.isEmpty(), true);
        test.setEmpty(false);
        assertEquals(test.isEmpty(), false);
    }

    /*************************************
     * Test is value is set appropriately.
     *************************************/
    @Test
    public void cellValueTest() {

        Cell test = new Cell(false, 0);
        assertEquals(test.getValue(), 0);
        test.setValue(three);
        assertEquals(test.getValue(), three);

        test = new Cell(true, 2);
        assertEquals(test.getValue(), 2);
        test.setValue(0);
        assertEquals(test.getValue(), 0);
    }

    /***********************************
     * Check the status of the game.
     ***********************************/
    @Test
    public void gameStatusTest() {
        ThreesTwoGame game = new ThreesTwoGame();
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
        Cell test = new Cell(false, 1);
        for (int row = 0; row < four; row++) {
            for (int col = 0; col < four; col++) {
                game.setCells(row, col, test);
            }
        }
        assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
    }

    /************************************************
     * Another method to test the status of the game.
     ************************************************/
    @Test
    public void gameStatusTest2() {
        ThreesTwoGame game = new ThreesTwoGame();
        Cell test = new Cell(false, three);
        for (int row = 0; row < four; row++) {
            for (int col = 0; col < four; col++) {
                game.setCells(row, col, test);
            }
        }
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());

        test = new Cell(false, 2);
        for (int row = 0; row < four; row++) {
            for (int col = 0; col < four; col++) {
                game.setCells(row, col, test);
            }
        }
        assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
    }

    /*************************************************
     * Test the reset feature of the game.
     *************************************************/
    @Test
    public void resetTest() {

        ThreesTwoGame game = new ThreesTwoGame();
        Cell test = new Cell(false, 2);
        for (int row = 0; row < four; row++) {
            for (int col = 0; col < four; col++) {
                game.setCells(row, col, test);
            }
        }
        assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
        game.resetMain();
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
    }

    /***************************************************
     * Make sure the next cell is created properly.
     ***************************************************/
    @Test
    public void nextCellTest() {

        ThreesTwoGame game = new ThreesTwoGame();
        assertFalse(game.getNextCell().getValue() > three);
        assertTrue(game.getNextCell().getValue() <= three);
    }

    /**************************************************
     * Test if the score is properly calculated.
     **************************************************/
    @Test
    public void testScore() {

        ThreesTwoGame game = new ThreesTwoGame();
        assertEquals(one27, game.getHighScore());
        Cell test = new Cell(false, 2);
        for (int row = 0; row < four; row++) {
            for (int col = 0; col < four; col++) {
                game.setCells(row, col, test);
            }
        }
        game.score();
        assertEquals(threeTwo, game.getScore());
    }
}
