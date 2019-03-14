public class WritteActualyRate {
    public static void main(String[] args) {
        Table table = NBPExchange.downloadActualyRate();
        System.out.println(table);
        for(Currency currency : table.getAllCurrency()){
            System.out.println(currency);
        }
    }

}

