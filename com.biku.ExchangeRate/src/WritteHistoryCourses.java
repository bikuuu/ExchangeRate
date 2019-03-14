import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;


public class WritteHistoryCourses {
    public static void main(String[] args) {


        String dateStart = JOptionPane.showInputDialog("Podaj datę: ", "2005-01-03");
        String dateEnd = JOptionPane.showInputDialog("Podaj datę: ", "2005-01-03");

        betweenCourses(dateStart, dateEnd);

    }


    public static Currency betweenCourses(String dateStart, String dateEnd) {
        LocalDate startDate = LocalDate.parse(dateStart);
        LocalDate endDate = LocalDate.parse(dateEnd);

        List<LocalDate> allDate = dateBetween(startDate, endDate);

        List <Currency> currency1 = null;
        for (LocalDate date : allDate) {
            if (!isWeekend(date)) {
                Table table = NBPExchange.historyCourses(date.toString());
                for (Currency currency : table.getAllCurrency()) {

                    System.out.println(currency);

                }

            }

        }
        return null;
    }

    public static List<LocalDate> dateBetween(LocalDate startDate, LocalDate endDate) {
        long numberOfBetween = (long) DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numberOfBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());

    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        switch (dayOfWeek) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }


}
