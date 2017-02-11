package es.rachelcarmena;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameBoard3Test {

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
	public void isWinnerInVertical() throws Exception {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(0, 1));
		assertFalse(gameBoard.play(1, 1));
		assertTrue(gameBoard.play(0, 2));
	}

	@Test
	public void isWinnerInHorizontal() throws Exception {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(1, 0));
		assertFalse(gameBoard.play(0, 1));
		assertTrue(gameBoard.play(2, 0));
	}

	@Test
	public void isWinnerInUpDownDiagonal() throws Exception {
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(1, 1));
		assertFalse(gameBoard.play(1, 2));
		assertTrue(gameBoard.play(2, 2));
	}

	@Test
	public void isWinnerInDownUpDiagonal() throws Exception {
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(1, 1));
		assertFalse(gameBoard.play(1, 0));
		assertTrue(gameBoard.play(2, 0));
	}

	@Test
	public void isWinnerInDownUpDiagonalCenterAtTheEnd() throws Exception {
		assertFalse(gameBoard.play(0, 2));
		assertFalse(gameBoard.play(0, 0));
		assertFalse(gameBoard.play(2, 0));
		assertFalse(gameBoard.play(1, 0));
		assertTrue(gameBoard.play(1, 1));
	}

}
