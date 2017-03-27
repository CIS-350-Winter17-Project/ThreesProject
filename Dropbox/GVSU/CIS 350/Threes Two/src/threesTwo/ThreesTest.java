package threesTwo;

import static org.junit.Assert.*;  // This is the preferred way per junit docs

import org.junit.Test;

public class ThreesTest {

    @Test
	public void cellBooleanTest(){
		Cell test = new Cell(false, 0);
		assertEquals(test.isEmpty(), false);
		test.setEmpty(true);
		assertEquals(test.isEmpty(), true);

		test = new Cell(true, 0);
		assertEquals(test.isEmpty(), true);
		test.setEmpty(false);
		assertEquals(test.isEmpty(), false);
	}

	@Test
	public void cellValueTest() {

		Cell test = new Cell(false, 0);
		assertEquals(test.getValue(), 0);
		test.setValue(3);
		assertEquals(test.getValue(), 3);

		test = new Cell(true, 2);
		assertEquals(test.getValue(), 2);
		test.setValue(0);
		assertEquals(test.getValue(), 0);
	}

	@Test
	public void gameStatusTest(){
		ThreesTwoGame game = new ThreesTwoGame();
		assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
		Cell test = new Cell(false, 1);
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				game.setCells(row, col, test);
			}
		}
		assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
	}


	@Test
	public void gameStatusTest2() {
		ThreesTwoGame game = new ThreesTwoGame();
		Cell test = new Cell(false, 3);
		for (int row = 0; row < 4; row ++) {
			for (int col = 0; col < 4; col ++) {
				game.setCells(row, col, test);
			}
		}
		assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());

		test = new Cell(false, 2);
		for (int row = 0; row < 4; row ++) {
			for (int col = 0; col < 4; col ++) {
				game.setCells(row, col, test);
			}
		}
		assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
	}

	@Test
	public void resetTest() {

		ThreesTwoGame game = new ThreesTwoGame();
		Cell test = new Cell(false, 2);
		for (int row = 0; row < 4; row ++) {
			for (int col = 0; col < 4; col ++) {
				game.setCells(row, col, test);
			}
		 }
		assertEquals(GameStatus.GAME_OVER, game.getGameStatus());
		game.resetMain();
		assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
	}

	@Test
	public void nextCellTest() {

		ThreesTwoGame game = new ThreesTwoGame();
		assertFalse(game.getNextCell().getValue() > 3);
		assertTrue(game.getNextCell().getValue() <= 3);
	}

	@Test
	public void testScore() {

		ThreesTwoGame game = new ThreesTwoGame();
		assertEquals(127, game.getHighScore());
		Cell test = new Cell (false, 2);
		for (int row = 0; row < 4; row ++){
			for (int col = 0; col < 4; col ++){
				game.setCells(row, col, test);
			}
		}
		game.score();
		assertEquals(32, game.getScore());
	}
}
