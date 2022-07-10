package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TradeUtil {

    public List<Trade> availableTrades() {
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade(
                "T1", 1, "CP-1",
                "B1", "20-05-2020", getCurrentDate(),"N"));
        trades.add(new Trade(
                "T2", 2, "CP-2",
                "B1", "20-05-2021", getCurrentDate(), "N"));
        trades.add(new Trade(
                "T3", 3, "CP-3",
                "B2", "20-05-2099", getCurrentDate(), "Y"));
        return trades;
    }

    public String getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = formatter.format(localDateTime);
            return formattedDate;
        } catch (Exception e) {
            throw e;
        }
    }

    public static LocalDate convertStringToDate(String input) {
        if (input == null || input.length() == 0) {
            return LocalDate.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(input, formatter);
        return localDate;
    }
}
