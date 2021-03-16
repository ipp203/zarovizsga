package hu.nive.ujratervezes.zarovizsga.workhours;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class WorkHours {
    private List<WorkHour> workHourList;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String minWork(String file) {

        workHourList = new ArrayList<>();
        Path path = Path.of(file);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file " + file, ioe);
        }

        return getNameOfMinWorkHour();
    }

    private String getNameOfMinWorkHour() {
        int minHour = Integer.MAX_VALUE;
        String result = "";
        for (WorkHour wh : workHourList) {
            if (wh.getHour() < minHour) {
                minHour = wh.getHour();
                result = wh.toString();
            }
        }
        return result;
    }

    private void processLine(String line) {
        String[] data = line.split(",");
        if (data.length == 3) {
            String name = data[0];
            int hours;
            LocalDate date;
            try {
                hours = Integer.parseInt(data[1]);
                date = LocalDate.parse(data[2], dateTimeFormatter);
            } catch (DateTimeParseException | NumberFormatException e) {
                return;
            }
            workHourList.add(new WorkHour(name, hours, date));
        }
    }


}
