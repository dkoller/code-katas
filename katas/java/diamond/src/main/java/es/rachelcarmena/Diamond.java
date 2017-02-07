package es.rachelcarmena;

import java.util.ArrayList;
import java.util.List;

public class Diamond {

	private final char INITIAL_LETTER = 'A';
	private char letter;
	private int letterIndex;

	Diamond(char diamondLetter) {
		letter = diamondLetter;
		letterIndex = letter - INITIAL_LETTER;
	}

	private String getFirstString() {
		String edgeSpaces = StringUtils.getWhitespacesString(letterIndex);

		StringBuilder sb = new StringBuilder();
		sb.append(edgeSpaces);
		sb.append(INITIAL_LETTER);
		sb.append(edgeSpaces);
		return sb.toString();
	}

	private String getMiddleString() {
		StringBuilder sb = new StringBuilder();
		sb.append(letter);
		sb.append(StringUtils.getWhitespacesString(2 * letterIndex - 1));
		sb.append(letter);
		return sb.toString();
	}

	private String getIntermediateString(int row) {
		String edgeSpaces = StringUtils.getWhitespacesString(letterIndex - row);
		char letter = (char) (INITIAL_LETTER + row);

		StringBuilder sb = new StringBuilder();
		sb.append(edgeSpaces);
		sb.append(letter);
		sb.append(StringUtils.getWhitespacesString((2 * row - 1)));
		sb.append(letter);
		sb.append(edgeSpaces);
		return sb.toString();
	}

	public String[] getRepresentation() {
		if (letter == INITIAL_LETTER) {
			return new String[] { String.valueOf(INITIAL_LETTER) };
		}

		List<String> diamond = new ArrayList<>();
		diamond.add(getFirstString());
		for (int row = 1; row < letterIndex; row++) {
			diamond.add(getIntermediateString(row));
		}
		diamond.add(getMiddleString());
		return ListUtils.addReverse(diamond).toArray(new String[0]);
	}
}
