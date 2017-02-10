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
		private final String FILENAME = "high_score.txt";
		private final static int boardSize = 4;

		public ThreesTwoGame() {
			board = new Cell[boardSize][boardSize];
			// TODO some code to retrieve the high score from a text file.
			// change some code. This is a change.
			
			setHighScore();
			
			Random rand = new Random();
			
			// populate the board with 9 cells
			for (int i=0; i<9; i++) {
				
				// the value for the cell
				int val = rand.nextInt(2) + 1;
				
				// where the cell will be located
				int row = rand.nextInt(4);
				int col = rand.nextInt(4);
				
				if (board[row][col] == null) {
					board[row][col] = new Cell(false, val);
				} 
				
				
			}
			
			// testing
			for (int i=0; i<board.length; i++) {
				
				if (i == 0)
					System.out.println("+---+---+---+---+");
				
				for (int j=0; j<board[i].length; j++) {
					if (board[i][j] != null)
						System.out.print("| " + board[i][j].getValue() + " ");
					else if (j < 3)
						System.out.print("|   ");
					else
						System.out.print("|   |");
				}
				
				System.out.println("\n+---+---+---+---+");
			}
			
			status = GameStatus.IN_PROGRESS;
			
			// this below was for testing
			int score = 42;
			
			System.out.println(highScore);
			
			saveHighScore(score);
				
		}

		public void Move(Direction direction) {
			if (moveAvailable(direction)) {
				if (direction == Direction.UP) {
					for (int row = 1; row < boardSize; row++) {
						for (int col = 0; col < boardSize; col++) {
							if (board[row - 1][col].isEmpty()) {
								board[row - 1][col].setValue(
										board[row][col].getValue());
								board[row - 1][col].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row - 1][col]
									.getValue() == board[row][col]
											.getValue()
									&& board[row][col].getValue() != 1
									|| board[row][col].getValue() != 2) {
								board[row - 1][col].setValue(
										board[row - 1][col].getValue() * 2);
								board[row - 1][col].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row - 1][col].getValue() == 1
									&& board[row][col].getValue() == 2
									|| board[row - 1][col].getValue() == 2
											&& board[row][col]
													.getValue() == 1) {
								board[row - 1][col].setValue(3);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}
						}
					}
					
					Random rand = new Random();
					Boolean newCell = false;
					int number = rand.nextInt(1) + 1;
					while (!newCell){
						int up = rand.nextInt(3);
						if (board[3][up].isEmpty()){
							board[3][up].setValue(number);
							newCell = true;
						}
					}
				}

				if (direction == Direction.DOWN) {
					for (int row = 2; row > -1; row--) {
						for (int col = 0; col < boardSize; col++) {
							if (board[row + 1][col].isEmpty()) {
								board[row + 1][col].setValue(
										board[row][col].getValue());
								board[row + 1][col].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row + 1][col]
									.getValue() == board[row][col]
											.getValue()
									&& board[row][col].getValue() != 1
									|| board[row][col].getValue() != 2) {
								board[row + 1][col].setValue(
										board[row + 1][col].getValue() * 2);
								board[row + 1][col].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row + 1][col].getValue() == 1
									&& board[row][col].getValue() == 2
									|| board[row + 1][col].getValue() == 2
											&& board[row][col]
													.getValue() == 1) {
								board[row + 1][col].setValue(3);
								board[row + 1][col].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}
						}
					}
					
					Random rand = new Random();
					int number = rand.nextInt(1) + 1;
					Boolean newCell = false;
					while (!newCell){
						int down = rand.nextInt(3);
						if (board[0][down].isEmpty()){
							board[0][down].setValue(number);
							newCell = true;

					}
				}
			}

				if (direction == Direction.RIGHT) {
					for (int row = 0; row < boardSize; row++) {
						for (int col = 2; col > -1; col--) {
							if (board[row][col + 1].isEmpty()) {
								board[row][col + 1].setValue(
										board[row][col].getValue());
								board[row][col + 1].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row][col + 1]
									.getValue() == board[row][col]
											.getValue()
									&& board[row][col].getValue() != 1
									|| board[row][col].getValue() != 2) {
								board[row][col + 1].setValue(
										board[row][col + 1].getValue() * 2);
								board[row][col + 1].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row][col + 1].getValue() == 1
									&& board[row][col].getValue() == 2
									|| board[row][col + 1].getValue() == 2
											&& board[row][col]
													.getValue() == 1) {
								board[row][col + 1].setValue(3);
								board[row][col + 1].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}
						}
					}
					
					Random rand = new Random();
					int number = rand.nextInt(1) + 1;
					Boolean newCell = false;
					while (!newCell){
						int right = rand.nextInt(3);
						if (board[right][0].isEmpty()){
							board[right][0].setValue(number);
							newCell = true;

					}
				}
			}

				if (direction == Direction.LEFT) {
					for (int row = 0; row < boardSize; row++) {
						for (int col = 1; col < boardSize; col++) {
							if (board[row][col - 1].isEmpty()) {
								board[row][col - 1].setValue(
										board[row][col].getValue());
								board[row][col - 1].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row][col - 1]
									.getValue() == board[row][col]
											.getValue()
									&& board[row][col].getValue() != 1
									|| board[row][col].getValue() != 2) {
								board[row][col - 1].setValue(
										board[row][col].getValue() * 2);
								board[row][col - 1].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}

							else if (board[row][col - 1].getValue() == 1
									&& board[row][col].getValue() == 2
									|| board[row][col - 1].getValue() == 2
											&& board[row][col]
													.getValue() == 1) {
								board[row][col - 1].setValue(3);
								board[row][col - 1].setEmpty(false);
								board[row][col].setValue(0);
								board[row][col].setEmpty(true);
							}
						}
					}
					
					Random rand = new Random();
					int number = rand.nextInt(1) + 1;
					Boolean newCell = false;
					while (!newCell){
						int left = rand.nextInt(3);
						if (board[left][0].isEmpty()){
							board[left][0].setValue(number);
							newCell = true;

						}
					}
				}
			}
		}

		private boolean moveAvailable() {
			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize; col++) {
					if (board[row][col].isEmpty()) {
						return true;
					}
				}
			}

			for (int row = 0; row < boardSize - 1; row++) {
				for (int col = 0; col < boardSize; col++) {
					if (board[row][col].getValue() == board[row + 1][col]
							.getValue() && board[row][col].getValue() != 1
							|| board[row][col].getValue() != 2)
						return true;

					else if (board[row][col].getValue() == 1
							&& board[row + 1][col].getValue() == 2
							|| board[row][col].getValue() == 2
									&& board[row + 1][col].getValue() == 1)
						return true;
				}
			}

			for (int row = 0; row < boardSize; row++) {
				for (int col = 0; col < boardSize - 1; col++) {
					if (board[row][col].getValue() == board[row][col + 1]
							.getValue() && board[row][col].getValue() != 1
							|| board[row][col].getValue() != 2)
						return true;

					else if (board[row][col].getValue() == 1
							&& board[row][col + 1].getValue() == 2
							|| board[row][col].getValue() == 2
									&& board[row][col + 1].getValue() == 1)
						return true;
				}
			}

			return false;
		}

		private boolean moveAvailable(final Direction direction) {
			if (direction == Direction.UP) {
				for (int row = 1; row < boardSize; row++) {
					for (int col = 0; col < boardSize; col++) {
						if (board[row][col]
								.getValue() == board[row - 1][col]
										.getValue()
								&& board[row][col].getValue() != 1
								|| board[row][col].getValue() != 2)
							return true;
						else if (board[row - 1][col].isEmpty())
							return true;
						else if (board[row - 1][col].getValue() == 1
								&& board[row][col].getValue() == 2
								|| board[row - 1][col].getValue() == 2
										&& board[row][col].getValue() == 1)
							return true;
					}
				}
			}
			if (direction == Direction.DOWN) {
				for (int row = 2; row > -1; row--) {
					for (int col = 0; col < boardSize; col++) {
						if (board[row][col]
								.getValue() == board[row + 1][col]
										.getValue()
								&& board[row][col].getValue() != 1
								|| board[row][col].getValue() != 2)
							return true;
						else if (board[row + 1][col].isEmpty())
							return true;
						else if (board[row + 1][col].getValue() == 1
								&& board[row][col].getValue() == 2
								|| board[row + 1][col].getValue() == 2
										&& board[row][col].getValue() == 1)
							return true;
					}
				}
			}

			if (direction == Direction.RIGHT) {
				for (int row = 0; row < boardSize; row++) {
					for (int col = 2; col > 0; col--) {
						if (board[row][col]
								.getValue() == board[row][col + 1]
										.getValue()
								&& board[row][col].getValue() != 1
								|| board[row][col].getValue() != 2)
							return true;

						else if (board[row][col + 1].isEmpty())
							return true;

						else if (board[row][col + 1].getValue() == 1
								&& board[row][col].getValue() == 2
								|| board[row][col + 1].getValue() == 2
										&& board[row][col].getValue() == 1)
							return true;
					}
				}
			}
			if (direction == Direction.LEFT) {
				for (int row = 0; row < boardSize; row++) {
					for (int col = 1; col < boardSize; col++) {
						if (board[row][col]
								.getValue() == board[row][col - 1]
										.getValue()
								&& board[row][col].getValue() != 1
								|| board[row][col].getValue() != 2)
							return true;

						else if (board[row][col - 1].isEmpty())
							return true;

						else if (board[row][col - 1].getValue() == 1
								&& board[row][col - 1].getValue() == 2
								|| board[row][col - 1].getValue() == 2
										&& board[row][col - 1]
												.getValue() == 1)
							return true;
					}
				}
			}
			return false;
		}
		
		private void setHighScore() {
			
			//System.out.println("setHighScore");
			
			Path file = FileSystems.getDefault().getPath(FILENAME);
			
	        
	        try (BufferedReader reader = Files.newBufferedReader(file)) {
	        	
	            highScore = Integer.parseInt(reader.readLine());

	        } catch (IOException e) {
	            // File does not exist, so high score is zero
	        	//System.err.format("IOException: %s%n", e);
	        	highScore = 0;
	        }

	    }
		
		private void saveHighScore(int newScore) {
			
			
			Path file = FileSystems.getDefault().getPath(FILENAME);
				
			//System.out.println("saveHighScore");
			
			String str = Integer.toString(newScore);

			try (BufferedWriter writer = Files.newBufferedWriter(file)) {
					
				writer.write(str);
				
				//System.out.println("saved");
					
			} catch (IOException e) {
					
				System.err.format("IOException: %s%n", e);
					
			}
			
		}
		

}

