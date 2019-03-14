public class WritteActualyCourse {
    public static void main(String[] args) {
        Table table = NBPExchange.downloadActualyCoures();
        System.out.println(table);
        for(Currency currency : table.getAllCurrency()){
            System.out.println(currency);
        }
    }

}
