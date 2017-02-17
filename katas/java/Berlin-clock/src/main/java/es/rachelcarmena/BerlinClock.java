package es.rachelcarmena;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BerlinClock {

	private static final String LAMP_OFF = "[O]";

	private int hour;
	private int minute;

	public BerlinClock(String time) {
		LocalTime localTime = LocalTime.parse(time);
		hour = localTime.getHour();
		minute = localTime.getMinute();
	}

	@Override
	public String toString() {
		final String HOUR_LAMP_ON = "[R]";
		final String MINUTE_LAMP_ON = "[Y]";

		int fiveHourLamps = hour / 5;
		int oneHourLamps = hour % 5;
		int fiveMinuteLamps = minute / 5;
		int oneMinuteLamps = minute % 5;

		String[] representationElements = new String[4];
		representationElements[0] = createFourElementRow(fiveHourLamps, HOUR_LAMP_ON);
		representationElements[1] = createFourElementRow(oneHourLamps, HOUR_LAMP_ON);
		representationElements[2] = createFiveMinuteRow(fiveMinuteLamps, MINUTE_LAMP_ON);
		representationElements[3] = createFourElementRow(oneMinuteLamps, MINUTE_LAMP_ON);
		return String.join("%n", representationElements);
	}

	private String createFourElementRow(int lampsOn, String lampOnSymbol) {
		final int FOUR_ELEMENT_LAMPS = 4;
		final String FOUR_ELEMENT_SEPARATOR = "      ";

		boolean hasLampsOn = lampsOn > 0;
		if (!hasLampsOn)
			return nCopies(FOUR_ELEMENT_LAMPS, LAMP_OFF, FOUR_ELEMENT_SEPARATOR);

		int lampsOff = FOUR_ELEMENT_LAMPS - lampsOn;
		List<String> rowElements = new ArrayList<>();
		rowElements.addAll(Collections.nCopies(lampsOn, lampOnSymbol));
		rowElements.addAll(Collections.nCopies(lampsOff, LAMP_OFF));
		return rowElements.stream().collect(Collectors.joining(FOUR_ELEMENT_SEPARATOR));
	}

	private String createFiveMinuteRow(int fiveMinuteLamps, String lampOnSymbol) {
		final String QUARTER_STRING = "[Y][Y][R]";
		final int LAMPS_OUT_OF_QUARTER = 2;
		final int LAMPS_PER_QUARTER = 3;
		final int QUARTERS = 3;
		final int FIVE_MINUTE_LAMPS = LAMPS_OUT_OF_QUARTER + QUARTERS * LAMPS_PER_QUARTER;

		boolean hasFiveMinuteLampsOn = fiveMinuteLamps > 0;
		if (!hasFiveMinuteLampsOn)
			return nCopies(FIVE_MINUTE_LAMPS, LAMP_OFF, "");

		int quarters = fiveMinuteLamps / LAMPS_PER_QUARTER;
		int lampsOn = fiveMinuteLamps % LAMPS_PER_QUARTER;
		int lampsOff = FIVE_MINUTE_LAMPS - quarters * LAMPS_PER_QUARTER - lampsOn;

		List<String> fiveMinuteRowElements = new ArrayList<>();
		fiveMinuteRowElements.addAll(Collections.nCopies(quarters, QUARTER_STRING));
		fiveMinuteRowElements.addAll(Collections.nCopies(lampsOn, lampOnSymbol));
		fiveMinuteRowElements.addAll(Collections.nCopies(lampsOff, LAMP_OFF));
		return fiveMinuteRowElements.stream().collect(Collectors.joining());
	}

	private String nCopies(int n, String o, String separator) {
		return Collections.nCopies(n, o).stream().collect(Collectors.joining(separator));
	}
}