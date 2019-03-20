import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        String dateStart = JOptionPane.showInputDialog("Podaj datę: ", "2005-01-03");
        String dateEnd = JOptionPane.showInputDialog("Podaj datę: ", "2005-01-03");
        String chooseCurrency = JOptionPane.showInputDialog("Podaj kod waluty: ", "EUR");


        HistoryRates.betweenRate(dateStart, dateEnd, chooseCurrency);
        HistoryRates.dataCorrectness(dateStart, dateEnd, chooseCurrency);

    }
}
