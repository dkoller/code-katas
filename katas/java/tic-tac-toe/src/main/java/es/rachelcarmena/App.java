package es.rachelcarmena;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		boolean winner = false;
		int x = 0;
		int y = 0;
		int size = 0;
		GameBoard board;

		System.out.print("Give me the board size: ");
		Scanner scanner = new Scanner(System.in);
		if (scanner.hasNextInt()) {
			size = scanner.nextInt();
		}
		board = new GameBoard(size);
		System.out.println(board.toString());
		while (!winner && board.isNotFull()) {
			System.out.print("Play (x): ");
			if (scanner.hasNextInt()) {
				x = scanner.nextInt();
			}
			System.out.print("Play (y): ");
			if (scanner.hasNextInt()) {
				y = scanner.nextInt();
			}
			winner = board.play(x, y);
			System.out.println(board.toString());
		}
		System.out.println(winner ? "Win!!" : "Game over!!");
		scanner.close();
	}
}
