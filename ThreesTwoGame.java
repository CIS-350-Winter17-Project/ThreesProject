
public class ThreesTwoGame {
	private Cell[][] board;
	private GameStatus status;
	private int highScore;
	private final static int boardSize = 4;

	public ThreesTwoGame() {
		board = new Cell[boardSize][boardSize];
		// TODO some code to retrieve the high score from a text file.
		status = GameStatus.IN_PROGRESS;
	}

	public void Move(Direction direction) {
		if (moveAvailable(direction)) {
			if (direction == Direction.UP) {
				for (int row = 1; row < boardSize; row++) {
					for (int col = 0; col < boardSize; col++) {
						if (board[row - 1][col].isEmpty()) {
							board[row - 1][col].setValue(
									board[row][col].getValue());
							board[row][col].setValue(0);
							board[row][col].setEmpty(true);
						}

						if (board[row - 1][col]
								.getValue() == board[row][col]
										.getValue()
								&& board[row][col].getValue() != 1
								|| board[row][col].getValue() != 2) {
							board[row - 1][col].setValue(
									board[row - 1][col].getValue() * 2);
						}

					}
				}

			}

			if (direction == Direction.DOWN) {

			}

			if (direction == Direction.RIGHT) {

			}

			if (direction == Direction.LEFT) {

			}
		} else {
			// error, move not allowed
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

}
