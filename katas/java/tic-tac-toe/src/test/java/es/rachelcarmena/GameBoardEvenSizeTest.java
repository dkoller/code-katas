package es.rachelcarmena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameBoardEvenSizeTest {
	private GameBoard gameBoard;

	@Before
	public void before() {
		gameBoard = new GameBoard(4);
	}

	@Test
	public void printGameBoard() {
		assertEquals(" -  -  -  - %n -  -  -  - %n -  -  -  - %n -  -  -  - %n", gameBoard.toString());
	}

	@Test
	public void printWithPlay() {
		gameBoard.play(2, 1);
		gameBoard.play(1, 0);
		assertEquals(" -  O  -  - %n -  -  X  - %n -  -  -  - %n -  -  -  - %n", gameBoard.toString());
	}

	@Test
	public void isWinnerInVertical() {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(0, 1));
		assertFalse(gameBoard.play(1, 1));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(1, 2));
		assertTrue(gameBoard.play(0, 3));
	}

	@Test
	public void isWinnerInHorizontal() {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 1));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(2, 0));
		assertFalse(gameBoard.play(0, 3));
		assertTrue(gameBoard.play(3, 0));
	}

	@Test
	public void isWinnerInUpDownDiagonal() {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(1, 1));
		assertFalse(gameBoard.play(1, 2));
		assertFalse(gameBoard.play(2, 2));
		assertFalse(gameBoard.play(1, 3));
		assertTrue(gameBoard.play(3, 3));
	}

	@Test
	public void isWinnerInDownUpDiagonal() {
		assertFalse(gameBoard.play(0, 3));
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(1, 2));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(2, 1));
		assertFalse(gameBoard.play(1, 1));
		assertTrue(gameBoard.play(3, 0));
	}

	@Test
	public void notWinnerInDownUpDiagonalCenterAtTheEnd() {
		assertFalse(gameBoard.play(2, 2));
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 1));
		assertFalse(gameBoard.play(3, 3));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(2, 3));
		assertFalse(gameBoard.play(1, 2));
	}
}
