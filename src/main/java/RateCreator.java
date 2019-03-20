import java.util.ArrayList;
import java.util.List;

public class RateCreator {


    public static ArrayList<String> writteDate(String date){
        List<String> currencyList = new ArrayList<>();
        Table table = NBPExchange.historyRate(date.toString());
        for (Currency currency : table.getAllCurrency()) {
            currencyList.add(currency.toString());
        }
        return (ArrayList<String>) currencyList;
    }

    public static void writteActualyRate(){
        List<Currency> acutalyRate = new ArrayList<>();
        Table table = NBPExchange.downloadActualyRate();
        for(Currency currency : table.getAllCurrency()){
            acutalyRate.add(currency);
        }
    }

}

