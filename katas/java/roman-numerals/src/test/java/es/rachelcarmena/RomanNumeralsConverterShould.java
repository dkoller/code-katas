package es.rachelcarmena;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RomanNumeralsConverterShould {

	@Test
	@Parameters({ "1, I", "2, II", "3, III", "4, IV", "5, V", "7, VII", "9, IX", "10, X", "18, XVIII", "30, XXX",
			"38, XXXVIII", "50, L", "66, LXVI", "2498, MMCDXCVIII", "3999, MMMCMXCIX" })
	public void convert_normal_arabicNumber_in_romanNumeral(int decimal, String roman) {
		assertThat(RomanNumeralsConverter.romanFor(decimal), is(roman));
	}
}
