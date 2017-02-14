package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;

public class Diamond {

	private static final char INITIAL_LETTER = 'A';

	private char diamondLetter;
	private int diamondLetterIndex;

	Diamond(char letter) {
		diamondLetter = letter;
		diamondLetterIndex = diamondLetter - INITIAL_LETTER;
	}

	private String getFirstLine() {
		String edgeSpaces = getWhitespacesString(diamondLetterIndex);

		StringBuilder line = new StringBuilder();
		line.append(edgeSpaces);
		line.append(INITIAL_LETTER);
		line.append(edgeSpaces);
		return line.toString();
	}

	private String getIntermediateLine(int row) {
		String edgeSpaces = getWhitespacesString(diamondLetterIndex - row);
		char letter = (char) (INITIAL_LETTER + row);

		StringBuilder line = new StringBuilder();
		line.append(edgeSpaces);
		line.append(letter);
		line.append(getWhitespacesString(2 * row - 1));
		line.append(letter);
		line.append(edgeSpaces);
		return line.toString();
	}

	private String getMiddleLine() {
		StringBuilder line = new StringBuilder();
		line.append(diamondLetter);
		line.append(getWhitespacesString(2 * diamondLetterIndex - 1));
		line.append(diamondLetter);
		return line.toString();
	}

	private List<String> addReverseMinusMiddle(List<String> initialList) {
		List<String> newList = new ArrayList<>(initialList);
		for (int i = initialList.size() - 2; i >= 0; i--) {
			newList.add(initialList.get(i));
		}
		return newList;
	}

	public static String getWhitespacesString(int numberOfSpaces) {
		StringBuilder format = new StringBuilder("%");
		format.append(numberOfSpaces);
		format.append("s");
		return String.format(format.toString(), "");
	}

	public String[] getRepresentation() {
		if (diamondLetter == INITIAL_LETTER) {
			return new String[] { String.valueOf(INITIAL_LETTER) };
		}

		List<String> diamond = new ArrayList<>();
		diamond.add(getFirstLine());
		for (int row = 1; row < diamondLetterIndex; row++) {
			diamond.add(getIntermediateLine(row));
		}
		diamond.add(getMiddleLine());
		return addReverseMinusMiddle(diamond).toArray(new String[0]);
	}
}
