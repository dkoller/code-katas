package es.rachelcarmena;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Kata {

	public static String createPhoneNumber(int[] numbers) {
		return String.format("(%d%d%d) %d%d%d-%d%d%d%d", Arrays.stream(numbers).boxed().toArray());
	}

	public static String order(String string) {
		List<String> words = Arrays.asList(string.split("\\s"));
		return words.stream().sorted(Comparator.comparing(e -> getNumberFromWord(e))).collect(Collectors.joining(" "));
	}

	public static int getNumberFromWord(String word) {
		if (!Pattern.matches("\\D*\\d\\D*", word))
			return -1;
		return Integer.valueOf(word.replaceAll("\\D", ""));
	}

	public static int findEvenIndex(int[] integers) {
		int leftSum = 0;
		int rightSum = Arrays.stream(integers).sum() - integers[0];
		for (int i = 1; i < integers.length - 1; i++) {
			leftSum += integers[i - 1];
			rightSum -= integers[i];
			if (leftSum == rightSum)
				return i;
		}
		return -1;
	}

	public static String seriesSum(int n) {
		float result = 0;
		for (int i = 0; i < n; i++) {
			result += 1.0 / (3 * i + 1);
		}
		return String.format("%.2f", result);
	}
}
