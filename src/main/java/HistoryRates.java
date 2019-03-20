import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;


public class HistoryRates {

 /**
 * TODO: Obsługi wprowadzenia null przez użytkownika
  * TODO: Obsługa wprowadzenia błędnych danych przez użytkownika
 */


    public static List<String> dataCorrectness(String dateStart, String dateEnd, String chooseCurrency) {
        LocalDate startDate = LocalDate.parse(dateStart);
        LocalDate endDate = LocalDate.parse(dateEnd);
        List<String> currencyList = new ArrayList<>();

        if (startDate.equals(endDate)) {
            if (!isWeekend(startDate)) {
                return RateCreator.writteDate(dateStart);
            }

        } else if (startDate.isAfter(endDate) || endDate.isBefore(endDate)) {
            //TODO: Dodać zdarzenie
        } else {
            return betweenRate(dateStart, dateEnd, chooseCurrency);
        }
        return currencyList;
    }


    public static List<String> betweenRate(String dateStart, String dateEnd, String chooseCurrency) {
        LocalDate startDate = LocalDate.parse(dateStart);
        LocalDate endDate = LocalDate.parse(dateEnd);


        List<LocalDate> allDate = dateBetween(startDate, endDate);
        List<String> currencyList = new ArrayList<>();
        List<String> selectedCurrency = new ArrayList<>();


        for (LocalDate date : allDate)
            if (!isWeekend(date)) {
                Table table = NBPExchange.historyRate(date.toString());
                for (Currency currency : table.getAllCurrency()) {
                    currencyList.add(currency.toString());
                }


                /**
                 * TODO: Wczytuje tylko ostatnią datę (tak przekazane z "RateCreator"
                 * TODO: potrzebna jakaś metoda zbierająca dane
                 *
                 * RateCreator.writteDate(date.toString());
                 *
                 */
                /**
                 * TODO: Poprawić iteracje, wskazuje na następny element po elemencie "contains(chooseCurrency)"
                 */
                Iterator<String> iterator = currencyList.iterator();

                while (iterator.hasNext()) {
                    if (iterator.next().contains(chooseCurrency) && iterator.hasNext()) {
                        selectedCurrency.add(iterator.next());
                    }
                }
            }


        return currencyList;
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
