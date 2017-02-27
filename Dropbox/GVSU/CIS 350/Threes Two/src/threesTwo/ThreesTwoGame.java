package threesTwo;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

	public class ThreesTwoGame {
		private Cell[][] board;
		private GameStatus status;
		
		private int highScore;
		private int score;
		
		private Cell nextCell;

		private final String FILENAME = "high_score.txt";
		private final static int boardSize = 4;

		/* This constructor is for testing only */
		public ThreesTwoGame(boolean test) {
			
			board = new Cell[4][4];
			
			for (int row=0; row<4; row++) {
				for (int col=0; col<4; col++) {
					board[row][col] = new Cell(true, 0);
				}
			}
		}
		
		public ThreesTwoGame() {
			
			board = new Cell[boardSize][boardSize];
			
			createNextCell();
			
			setHighScore();
						
			resetMain();
			
			
				
		}

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
				}
				
			}
					
					
		}
			
		// change back to private visibility
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
				
			}
			return status;
		}
			
			
		
		private void setHighScore() {
			
			Path file = FileSystems.getDefault().getPath(FILENAME);
			
	        
	        try (BufferedReader reader = Files.newBufferedReader(file)) {
	        	
	            highScore = Integer.parseInt(reader.readLine());

	        } catch (IOException e) {
	        	
	            // File does not exist, so high score is zero
	        	highScore = 0;
	        }

	    }
		
		private void saveHighScore(int newScore) {
			
			
			Path file = FileSystems.getDefault().getPath(FILENAME);
			
			String str = Integer.toString(newScore);

			try (BufferedWriter writer = Files.newBufferedWriter(file)) {
					
				writer.write(str);
					
			} catch (IOException e) {
					
				System.err.format("IOException: %s%n", e);
					
			}
			
		}
		
		public Cell[][] getDisplay(){
			return board;
		}
		
		/* This method is for testing */
		public void setCells(int row, int col, Cell cell) {
			board[row][col] = cell;
		}
		
		
		private void createNextCell() {
			nextCell = new Cell(false, new Random().nextInt(3) + 1);
		}
		
		// later
		public Cell getNextCell() {
			return nextCell;
		}
		
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
		}
		

}

