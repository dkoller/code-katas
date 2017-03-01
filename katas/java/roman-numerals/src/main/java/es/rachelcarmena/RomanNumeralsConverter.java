package es.rachelcarmena;

public class RomanNumeralsConverter {

	public static String romanFor(int decimal) {
		StringBuilder roman = new StringBuilder();
		int decimalNumber = decimal;

		for (DecimalToRoman decimalToRoman : DecimalToRoman.values()) {
			while (decimalNumber >= decimalToRoman.decimal) {
				roman.append(decimalToRoman.roman);
				decimalNumber -= decimalToRoman.decimal;
			}
		}
		return roman.toString();
	}

	private enum DecimalToRoman {
		THOUSAND(1000, "M"), 
		NINE_HUNDRED(900, "CM"), 
		FIVE_HUNDRED(500, "D"), 
		FOUR_HUNDRED(400, "CD"), 
		HUNDRED(100, "C"), 
		NINETY(90, "XC"), 
		FIFTY(50, "L"), 
		TEN(10, "X"), 
		NINE(9, "IX"), 
		FIVE(5, "V"), 
		FOUR(4, "IV"), 
		ONE(1, "I"),;

		private final int decimal;
		private final String roman;

		private DecimalToRoman(int decimal, String roman) {
			this.decimal = decimal;
			this.roman = roman;
		}
	}
}
