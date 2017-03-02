package threesTwo;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

	/**********************************************************************
	* This is the logic of the game. When the GUI receives direct input, 
	* this is what tells each cell how far to move, where to move, and 
	* whether or not to combine. 
	* 
	* @author Anthony Blanton
	* @author Scott Lumsden
	* @author Dylan Shoup
	* 
	*@version 0.1 March 3, 2017
	*********************************************************************/
	public class ThreesTwoGame {
		
		/** The board of Cells, each containing an int and boolean value */
		private Cell[][] board;
		
		/** Tells the game, whether it is over or not */
		private GameStatus status;
		
		/** The highest score the player has ever achieved */
		private int highScore;
		
		/** The current score the player has on this specific game */
		private int score;
		
		/** The next Cell that will enter onto the board after a move */
		private Cell nextCell;

		/** The file that is created to preserve the high score */
		private final String FILENAME = "high_score.txt";
		
		/** The size of the board. Set to stay at 4 */
		private final static int boardSize = 4;

		/******************************************************************
	 	* This is a test constructor. 
	 	* @param test used to differentiate between this and the real 
	 	* constructor. 
	 	*****************************************************************/
		public ThreesTwoGame(boolean test) {
			
			board = new Cell[4][4];
			
			for (int row=0; row<4; row++) {
				for (int col=0; col<4; col++) {
					board[row][col] = new Cell(true, 0);
				}
			}
		}
		
		/******************************************************************
	 	* Constructor used to make the 2D Array of Cells and set the
	 	* initial state of the board.
	 	*****************************************************************/
		public ThreesTwoGame() {
			
			board = new Cell[boardSize][boardSize];
			
			createNextCell();
			
			setHighScore();
						
			resetMain();
			
			
				
		}

		/******************************************************************
	 	* This method is what moves the Cells of the board, whatever
		* direction the player inputs.
	 	* @param direction
	 	*****************************************************************/
		public void Move(Direction direction) {
			Random rand = new Random();
			boolean mover = false;
			if (direction == Direction.UP) {
				mover = false;
				for (int row = 0; row < boardSize; row ++) {
					for (int col = 0; col < boardSize; col++) {
					
						if (moveAvailable(row, col, Direction.UP)) {
							
							int val = board[row][col].getValue() + board[row-1][col].getValue();
							
							board[row-1][col].setValue(val);
							board[row-1][col].setEmpty(false);
							
							board[row][col].setValue(0);
							board[row][col].setEmpty(true);
							mover = true;
						}
							
					}
					
				}
				if(mover==true){
					
					int chooseSpot = rand.nextInt(4);
					while(!board[3][chooseSpot].isEmpty()){
						chooseSpot = rand.nextInt(4);
				}
					board[3][chooseSpot] = nextCell;
					createNextCell();
					score();
				}
			
			}
					
			else if (direction == Direction.DOWN) {
				mover = false;		
				for (int row = 3; row >= 0; row--) {
					for (int col = 0; col < boardSize; col++) {
						
						if (moveAvailable(row, col, Direction.DOWN)) {
							
							int val = board[row][col].getValue() + board[row+1][col].getValue();
							
							board[row+1][col].setValue(val);
							board[row+1][col].setEmpty(false);
							
							board[row][col].setValue(0);
							board[row][col].setEmpty(true);
							
							mover = true;
						}
						
					}
				}
				if(mover==true){
					int chooseSpot = rand.nextInt(4);
					while(!board[0][chooseSpot].isEmpty()){
						chooseSpot = rand.nextInt(4);
					}
					board[0][chooseSpot] = nextCell;
					createNextCell();
					score();
				}
			
			}
			
			// this works correctly
			else if (direction == Direction.LEFT) {
				mover = false;
				for (int row = 0; row < boardSize; row++) {
					for (int col = 0; col < boardSize; col++) {
						
						if (moveAvailable(row, col, Direction.LEFT)) {
							
							int val = board[row][col].getValue() + board[row][col-1].getValue();
							
							board[row][col-1].setValue(val);
							board[row][col-1].setEmpty(false);
							
							board[row][col].setValue(0);
							board[row][col].setEmpty(true);
							
							mover = true;
						}
						
					}
				}
				if(mover==true){
					int chooseSpot = rand.nextInt(4);
					while(!board[chooseSpot][3].isEmpty()){
						chooseSpot = rand.nextInt(4);
					}
					board[chooseSpot][3] = nextCell;
					createNextCell();
					score();
				}
				
			}
			
			else if (direction == Direction.RIGHT) {
				mover = false;
				for (int row = 0; row < boardSize; row++) {
					for (int col = 3; col >= 0; col--) {
						
						if (moveAvailable(row, col, Direction.RIGHT)) {
							
							int val = board[row][col].getValue() + board[row][col+1].getValue();
							
							board[row][col+1].setValue(val);
							board[row][col+1].setEmpty(false);
							
							board[row][col].setValue(0);
							board[row][col].setEmpty(true);
							
							mover = true;
						}
						
					}
				}
				if(mover==true){
					int chooseSpot = rand.nextInt(4);
					while(!board[chooseSpot][0].isEmpty()){
						chooseSpot = rand.nextInt(4);
					}
					board[chooseSpot][0] = nextCell;
					createNextCell();
					score();
				}
				
			}
					
					
		}
			
		/******************************************************************
	 	* Checks whether or not a move for a specific cell is possible.
	 	* If it is, then the move is allowed in the Move method. If not,
	 	* then the piece remains where it is.
	 	* 
	 	* @param row The row of the specific cell you are testing
	 	* @param col The column of the specific cell you are testing
	 	* @param direction The direction the player is inputting
	 	* @return whether or not a move is available for that cell
	 	*****************************************************************/
		private boolean moveAvailable(int row, int col, final Direction direction) {
			
			if (direction == Direction.UP) {
					
					if (row == 0) return false;
						
					if (board[row][col].getValue() == 1) {
						
						if (board[row-1][col].getValue() == 2
								|| board[row-1][col].isEmpty()) 
							return true;
						
						return false;
						
					}
					
					if (board[row][col].getValue() == 2) {
						
						if (board[row-1][col].getValue() == 1
								|| board[row-1][col].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() >= 3){
						
						if (board[row-1][col].isEmpty() ||
								board[row][col].getValue() == board[row-1][col].getValue())
							return true;
						return false;
						
					}
				}
			
				if (direction == Direction.DOWN) {
					
					if (row == 3) return false;
					
					if (board[row][col].getValue() == 1) {
						
						if (board[row+1][col].getValue() == 2
								|| board[row+1][col].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() == 2) {
						
						if (board[row+1][col].getValue() == 1
								|| board[row+1][col].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() >= 3){
						
						if (board[row+1][col].isEmpty() ||
								board[row][col].getValue() == board[row+1][col].getValue())
							return true;
						
						return false;
						
					}
					
				}
				
				if (direction == Direction.LEFT) {
					
					if (col == 0) return false;
					
					if (board[row][col].getValue() == 1) {
						
						if (board[row][col-1].getValue() == 2
								|| board[row][col-1].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() == 2) {
						
						if (board[row][col-1].getValue() == 1
								|| board[row][col-1].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() >= 3){
						
						if (board[row][col-1].isEmpty() ||
								board[row][col].getValue() == board[row][col-1].getValue())
							return true;
						
						return false;
						
					}
					
				}
				
				if (direction == Direction.RIGHT) {
					
					if (col == 3) return false;
					
					if (board[row][col].getValue() == 1) {
						
						if (board[row][col+1].getValue() == 2
								|| board[row][col+1].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() == 2) {
						
						if (board[row][col+1].getValue() == 1
								|| board[row][col+1].isEmpty()) 
							return true;
						
						return false;
						
					} 
					
					if (board[row][col].getValue() >= 3){
						
						if (board[row][col+1].isEmpty() ||
								board[row][col].getValue() == board[row][col+1].getValue())
							return true;
						
						return false;
						
					}
					
				}
				return false;
			}
		
		/******************************************************************
	 	* Returns whether or not there is another move available. If there
	 	* is not, then the game is over and displays the appropriate 
	 	* message. 
	 	* @return The appropriate game status, depending on whether or not
	 	* there are moves available. 
	 	*****************************************************************/
		public GameStatus getGameStatus(){
			boolean stat=true;
			outerloop:
			for (int row = 0; row < 4; row++){
				for(int col = 0; col < 4; col++){
					if(((!moveAvailable(row, col, Direction.UP))&&
							(!moveAvailable(row, col, Direction.DOWN))&&
							(!moveAvailable(row, col, Direction.LEFT))&&
							(!moveAvailable(row, col, Direction.RIGHT)))){
						stat = false;
					}
					else{
						stat = true;
						break outerloop;
					}
					
				}
				
			}
			if(stat==false){
				status = GameStatus.GAME_OVER;
				System.out.println("Game Over");
				score();
				saveHighScore(setHighScore());
			}
			return status;
		}
			
			
		/******************************************************************
		* Determines the player's score by adding all of the int values 
	 	* contained in each cell. 
	 	* @return the score of that specific round of Threes. 
	 	*****************************************************************/
		public int score(){
			score = 0;
			for(int row = 0; row < 4; row++){
				for(int col = 0; col < 4; col++){
					score = score + board[row][col].getValue();
				}
			}
			return score;
		}
		
		/******************************************************************
	 	* Returns the current score of the game that is being played. 
	 	* @return the score of the game. 
		*****************************************************************/
		public int getScore(){
			return score;
		}
		
		/******************************************************************
	 	* Returns the highest score the player has ever achieved. 
	 	* @return the highest score the player has ever achieved.
	 	*****************************************************************/
		public int getHighScore(){
			return highScore;
		}
		
		/******************************************************************
	 	* Reads the high score off of the text file that was previously 
	 	* created.
	 	* @return the highest score made on that system. 
	 	*****************************************************************/
		public int readHighScore(){
			Path file = FileSystems.getDefault().getPath(FILENAME);
			
	        
	        try (BufferedReader reader = Files.newBufferedReader(file)) {
	        	
	            highScore = Integer.parseInt(reader.readLine());

	        } catch (IOException e) {
	        	
	            // File does not exist, so high score is zero
	        	highScore = 0;
	        }
	        return highScore;
		}	
		
		/******************************************************************
	 	* Sets the high score, if the current score is higher than the 
	 	* previous high score. 
	 	* @return the integer of the high score. 
	 	*****************************************************************/
		private int setHighScore() {
			
			Path file = FileSystems.getDefault().getPath(FILENAME);
			
	        
	        try (BufferedReader reader = Files.newBufferedReader(file)) {
	        	
	            highScore = Integer.parseInt(reader.readLine());

	        } catch (IOException e) {
	        	
	            // File does not exist, so high score is zero
	        	highScore = 0;
	        }
	        if(score>highScore){
	        	highScore = score;
	        }
	        return highScore;

	    }
		
		/******************************************************************
	 	* Saves the high score once the game has ended, or the player has
	 	* quit out of the game. 
	 	* @param newScore the score the player has achieved
	 	*****************************************************************/
		public void saveHighScore(int newScore) {
			
			
			Path file = FileSystems.getDefault().getPath(FILENAME);
			
			String str = Integer.toString(highScore);

			try (BufferedWriter writer = Files.newBufferedWriter(file)) {
					
				writer.write(str);
					
			} catch (IOException e) {
					
				System.err.format("IOException: %s%n", e);
					
			}
			
		}
		
		/******************************************************************
	 	* Returns the board as it is, used in conjunction with the GUI.
	 	* @return the board of 2D array of Cells. 
	 	*****************************************************************/
		public Cell[][] getDisplay(){
			return board;
		}
		
		/******************************************************************
	 	* This method is for testing. It allows us to test whether each
	 	* Cell is appropriately set. 
	 	* @param row The row position of this particular Cell.
	 	* @param col The column position of this particular Cell.
	 	* @param cell The cell state you are setting the Cell to. 
	 	*****************************************************************/
		public void setCells(int row, int col, Cell cell) {
			board[row][col] = cell;
		}
		
		/******************************************************************
	 	* A private helper method which creates the next Cell that will
	 	* be added to the board.
	 	*****************************************************************/
		private void createNextCell() {
			nextCell = new Cell(false, new Random().nextInt(3) + 1);
		}
		
		/******************************************************************
	 	* Returns the next cell that has been created and will be added to
	 	* the board, once the player inputs something.
	 	* @return the next cell. 
	 	*****************************************************************/
		public Cell getNextCell() {
			return nextCell;
		}
		
		/******************************************************************
	 	* Resets the board back to a new state, and resets the Game status
	 	* and the current score. 
	 	*****************************************************************/
		public void resetMain(){
			// create an empty board
						for (int i=0; i<boardSize; i++) {
							for (int j=0; j<boardSize; j++) {
								board[i][j] = new Cell(true, 0);
							}
						}
						
						// Start the game with 9 cells
						int startingCells = 0;
						Random rand = new Random();
						
						while (startingCells < 9) {
							int row = rand.nextInt(boardSize);
							int col = rand.nextInt(boardSize);
							
							int val = rand.nextInt(3) + 1;
							
							if (board[row][col].isEmpty()) {
								board[row][col] = new Cell(false, val);
								startingCells++;
							}
							
						}
						
						status = GameStatus.IN_PROGRESS;
						score = 0;
		}
		

}

