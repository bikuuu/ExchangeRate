import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;


public class WritteHistoryCourses {
    public static void main(String[] args) {


        String dateStart = JOptionPane.showInputDialog("Podaj datę: ", "2005-01-03");
        String dateEnd = JOptionPane.showInputDialog("Podaj datę: ", "2005-01-03");

        LocalDate startDate = LocalDate.parse(dateStart);
        LocalDate endDate = LocalDate.parse(dateEnd);

        List<LocalDate> allDate = dateBetween(startDate,endDate);

        for (LocalDate date: allDate
        ) {


            Table table = NBPExchange.historyCourses(date.toString());
            if (table == null){
                System.out.println("Course fot this date " + date + " not exist");
            }else{
                for (Currency currency : table.getAllCurrency()){
                    System.out.println(currency);
                }
            }

        }

//        for (Currency currency : table.getAllCurrency()){
//            if(table == null){
//
//            }else
//                System.out.println(currency);
//        }







    }

    public static List<LocalDate> dateBetween(LocalDate startDate, LocalDate endDate){
        long numberOfBetween = (long) DAYS.between(startDate,endDate);
        return IntStream.iterate(0,i->i+1)
                .limit(numberOfBetween)
                .mapToObj(i->startDate.plusDays(i))
                .collect(Collectors.toList());

    }


}
