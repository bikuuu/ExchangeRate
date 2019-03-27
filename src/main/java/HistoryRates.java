import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.apache.commons.lang3.StringUtils.substringAfter;


public class HistoryRates {

    /**
     * TODO: Obsługa wprowadzenia błędnych danych przez użytkownika
     */


    public static String dataCorrectness(String dateStart, String dateEnd, String chooseCurrency) {
        LocalDate startDate = LocalDate.parse(dateStart);
        LocalDate endDate = LocalDate.parse(dateEnd);
        List<String> currencyList = new ArrayList<>();

        if (startDate.equals(endDate)) {
            if (!isWeekend(startDate)) {
                return RateCreator.writteDate(dateStart).toString();
            }

        } else if (startDate.isAfter(endDate) || endDate.isBefore(endDate) || startDate == null || endDate == null || chooseCurrency == null) {
            //TODO: Dodać zdarzenie
        } else {

            return Rate(dateStart, dateEnd, chooseCurrency);
        }
        return currencyList.toString();
    }

    public static String Rate(String dateStart, String dateEnd, String chooseCurrency) {
        LocalDate startDate = LocalDate.parse(dateStart);
        LocalDate endDate = LocalDate.parse(dateEnd);
        List<LocalDate> allDate = dateBetween(startDate, endDate);
        List<Double> rateDouble = new ArrayList<>();
        List<String> selectedCurrency = new ArrayList<>();

        for (LocalDate date : allDate)
            if (!isWeekend(date)) {
                RateCreator.writteDate(date.toString());

//  Iterators method to separate selected currency from "currencyList"
                Iterator<String> iterator = RateCreator.writteDate(date.toString()).iterator();
                while (iterator.hasNext()) {
                    String myiterator = iterator.next();
                    if (myiterator.contains(chooseCurrency)) {
                        selectedCurrency.add(myiterator);
                    }
                }
            }

        for (String rate : selectedCurrency) {
            String rateNo = substringAfter(rate, ":");
            rateDouble.add(Double.parseDouble(rateNo));

        }

        return RateOperation.averageRate(rateDouble, rateDouble.size()).toString();
    }

    public static List<LocalDate> dateBetween(LocalDate startDate, LocalDate endDate) {
        long numberOfBetween = DAYS.between(startDate, endDate);
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
