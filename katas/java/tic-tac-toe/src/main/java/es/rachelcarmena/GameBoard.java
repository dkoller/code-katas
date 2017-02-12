package es.rachelcarmena;

import java.util.Arrays;

public class GameBoard {

	private final char EMPTY = '-';
	private final char DAVID = 'X';
	private final char GOLIATH = 'O';

	private int size;
	private char playerSymbol;
	private int busyPositions;
	private char[][] board;

	public GameBoard(int size) {
		this.size = size;
		this.playerSymbol = DAVID;
		this.busyPositions = 0;
		this.board = new char[size][size];
		initializeBoard();
	}

	private void initializeBoard() {
		for (int x = 0; x < size; x++)
			Arrays.fill(board[x], EMPTY);
	}

	public char getPlayer() {
		return playerSymbol;
	}

	public int getSize() {
		return size;
	}

	public boolean play(int coordX, int coordY) {
		if (coordX >= size || coordY >= size || board[coordX][coordY] != EMPTY)
			return false;

		board[coordX][coordY] = playerSymbol;
		busyPositions++;
		boolean winner = isWinner(coordX, coordY);
		if (!winner)
			changeSymbolToTheFollowingPlayer();
		return winner;
	}

	private void changeSymbolToTheFollowingPlayer() {
		playerSymbol = (playerSymbol == DAVID) ? GOLIATH : DAVID;
	}

	private boolean isWinner(int coordX, int coordY) {
		if (checkVertical(coordX))
			return true;

		if (checkHorizontal(coordY))
			return true;

		if (coordinatesInDownDiagonal(coordX, coordY) && checkDownDiagonal())
			return true;

		if (coordinatesInUpDiagonal(coordX, coordY) && checkUpDiagonal())
			return true;

		return false;
	}

	private boolean coordinatesInUpDiagonal(int coordX, int coordY) {
		return (coordX + coordY == size - 1);
	}

	private boolean coordinatesInDownDiagonal(int coordX, int coordY) {
		return (coordX == coordY);
	}

	private boolean checkVertical(int coordX) {
		for (int y = 0; y < size; y++) {
			if (board[coordX][y] != playerSymbol)
				return false;
		}
		return true;
	}

	private boolean checkHorizontal(int coordY) {
		for (int x = 0; x < size; x++) {
			if (board[x][coordY] != playerSymbol)
				return false;
		}
		return true;
	}

	private boolean checkDownDiagonal() {
		for (int x = 0; x < size; x++) {
			if (board[x][x] != playerSymbol)
				return false;
		}
		return true;
	}

	private boolean checkUpDiagonal() {
		for (int x = 0; x < size; x++) {
			if (board[x][size - x - 1] != playerSymbol)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				result.append(String.format(" %s ", board[x][y]));
			}
			result.append('\n');
		}
		return result.toString();
	}

	public boolean isNotFull() {
		return busyPositions != size * size;
	}
}
