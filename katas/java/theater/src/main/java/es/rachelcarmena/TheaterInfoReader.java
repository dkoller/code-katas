package es.rachelcarmena;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import es.rachelcarmena.model.Seat;

public class TheaterInfoReader {

	public Seat[][] createTheaterStructure(String fileName) {
		JsonReader fileReader = buildJsonReader(fileName);
		JsonObject theaterInfo = fileReader.readObject();
		JsonArray rows = theaterInfo.getJsonArray("rows");
		JsonObject seats = theaterInfo.getJsonObject("seats");
		int rowsNumber = rows.size();

		Seat[][] theater = new Seat[rowsNumber][];
		int rowIndex = 0;
		for (JsonValue row : rows) {
			fillTheaterRow(theater, seats, rowIndex, row);
			rowIndex++;
		}
		fileReader.close();

		return theater;
	}

	private void fillTheaterRow(Seat[][] theater, JsonObject seats, int rowIndex, JsonValue row) {
		String rowName = ((JsonString) row).getString();
		JsonArray seatsByRow = seats.getJsonArray(rowName);
		int seatsByRowNumber = seatsByRow.size();

		theater[rowIndex] = new Seat[seatsByRowNumber];
		int seatIndex = 0;
		for (JsonValue seatNumber : seatsByRow) {
			theater[rowIndex][seatIndex] = new Seat(rowName, seatNumber.toString());
			seatIndex++;
		}
	}

	private JsonReader buildJsonReader(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		return Json.createReader(inputStream);
	}

}
