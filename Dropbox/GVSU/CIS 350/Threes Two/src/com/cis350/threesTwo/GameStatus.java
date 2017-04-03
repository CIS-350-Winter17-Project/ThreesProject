package com.cis350.threesTwo;

/**********************************************************************
* This is an enumerated class type that tells the status of the game.
* When the game is in GAME_OVER, the game is finished and it resets
* itself.
*
* @author Anthony Blanton
* @author Scott Lumsden
* @author Dylan Shoup
*
*@version 0.1 March 3, 2017
*********************************************************************/

public enum GameStatus {

    /************************************************
     * Will decide if the game is in progress or not.
     ************************************************/
    IN_PROGRESS, GAME_OVER;
}
