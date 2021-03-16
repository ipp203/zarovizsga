package hu.nive.ujratervezes.zarovizsga.workhours;

import java.time.LocalDate;

public class WorkHour {
    private final String name;
    private final int hour;
    private final LocalDate date;

    public WorkHour(String name, int hour, LocalDate date) {
        this.name = name;
        this.hour = hour;
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public String toString() {
        return name + ": " + date;
    }
}
