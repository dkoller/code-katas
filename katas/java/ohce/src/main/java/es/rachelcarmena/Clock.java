package es.rachelcarmena;

import java.time.LocalTime;

public class Clock {
    public int hour() {
        return LocalTime.now().getHour();
    }
}
