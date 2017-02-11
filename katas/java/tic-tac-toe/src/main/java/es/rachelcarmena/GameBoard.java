package es.rachelcarmena;

public class GameBoard {

	private int size;
	private char[][] board;
	private char symbol;
	private final char EMPTY = '-';

	public GameBoard(int size) {
		this.size = size;
		this.symbol = 'X';
		this.board = new char[size][size];
		initializeBoard();
	}

	private void initializeBoard() {
		for (int y = 0; y < size; y++)
			for (int x = 0; x < size; x++)
				this.board[x][y] = EMPTY;
	}

	public boolean play(int coordX, int coordY) {
		if (coordX >= size || coordY >= size || board[coordX][coordY] != EMPTY)
			return false;
		board[coordX][coordY] = symbol;
		boolean winner = isWinner(coordX, coordY);
		if (!winner)
			changeSymbol();
		return winner;
	}

	private void changeSymbol() {
		symbol = (symbol == 'X') ? 'O' : 'X';
	}

	private boolean isWinner(int coordX, int coordY) {
		if (searchInVertical(coordX))
			return true;
		if (searchInHorizontal(coordY))
			return true;
		if (coordX == coordY) {
			if (searchInDownDiagonal())
				return true;
		}
		if (coordX + coordY == size - 1) {
			if (searchInUpDiagonal())
				return true;
		}
		return false;
	}

	private boolean searchInVertical(int coordX) {
		int count = 0;
		for (int y = 0; y < size; y++) {
			if (board[coordX][y] == symbol)
				count++;
		}
		return (count == size);
	}

	private boolean searchInHorizontal(int coordY) {
		int count = 0;
		for (int x = 0; x < size; x++) {
			if (board[x][coordY] == symbol)
				count++;
		}
		return (count == size);
	}

	private boolean searchInDownDiagonal() {
		int count = 0;
		for (int x = 0; x < size; x++) {
			if (board[x][x] == symbol)
				count++;
		}
		return (count == size);
	}

	private boolean searchInUpDiagonal() {
		int count = 0;
		for (int x = 0; x < size; x++) {
			if (board[x][size - x - 1] == symbol)
				count++;
		}
		return (count == size);
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
}
