package es.rachelcarmena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameBoardOddSizeTest {

	private GameBoard gameBoard;

	@Before
	public void before() {
		gameBoard = new GameBoard(3);
	}

	@Test
	public void printGameBoard() {
		assertEquals(" -  -  - \n -  -  - \n -  -  - \n", gameBoard.toString());
	}

	@Test
	public void printWithPlay() {
		gameBoard.play(2, 1);
		gameBoard.play(1, 0);
		assertEquals(" -  O  - \n -  -  X \n -  -  - \n", gameBoard.toString());
	}

	@Test
	public void isWinnerInVertical() {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(0, 1));
		assertFalse(gameBoard.play(1, 1));
		assertTrue(gameBoard.play(0, 2));
	}

	@Test
	public void isWinnerInHorizontal() {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(0, 1));
		assertTrue(gameBoard.play(2, 0));
	}

	@Test
	public void isWinnerInUpDownDiagonal() {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(1, 1));
		assertFalse(gameBoard.play(1, 2));
		assertTrue(gameBoard.play(2, 2));
	}

	@Test
	public void isWinnerInDownUpDiagonal() {
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(1, 1));
		assertFalse(gameBoard.play(1, 0));
		assertTrue(gameBoard.play(2, 0));
	}

	@Test
	public void isWinnerInDownUpDiagonalCenterAtTheEnd() {
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(2, 0));
		assertFalse(gameBoard.play(1, 0));
		assertTrue(gameBoard.play(1, 1));
	}

	@Test
	public void playerChangedIfAnotherPlay() {
		char player = gameBoard.getPlayer();

		gameBoard.play(0, 2);
		assertTrue(player != gameBoard.getPlayer());
		gameBoard.play(1, 2);
		assertEquals(player, gameBoard.getPlayer());
	}

	@Test
	public void notPlayerChangedIfTheSamePlay() {
		char player = gameBoard.getPlayer();

		gameBoard.play(0, 2);
		assertTrue(player != gameBoard.getPlayer());
		gameBoard.play(0, 2);
		assertTrue(player != gameBoard.getPlayer());
	}

	@Test
	public void notPlayerChangedIfWrongPlay() {
		char player = gameBoard.getPlayer();

		gameBoard.play(0, 2);
		assertTrue(player != gameBoard.getPlayer());
		gameBoard.play(0, 5);
		assertTrue(player != gameBoard.getPlayer());
	}

	@Test
	public void isNotFull() {
		assertTrue(gameBoard.isNotFull());
	}

	@Test
	public void isFull() {
		int size = gameBoard.getSize();
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
				gameBoard.play(x, y);

		assertFalse(gameBoard.isNotFull());
	}
}
