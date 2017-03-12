package es.rachelcarmena;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    public String now() {
        final String DATE_FORMAT = "dd/MM/yyyy";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.now().format(dateTimeFormatter);
    }
}