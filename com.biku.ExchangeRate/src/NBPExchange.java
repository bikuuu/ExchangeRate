import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

public class NBPExchange {
    private static final String ADRES = "http://api.nbp.pl/api/exchangerates/tables/";

    /** Pobiera tabelę z bieżącymi kursami walut.
     * Zwraca null w przypadku błędów.
     */
    public static Table downloadActualyCoures() {
        Document doc = loadXmlFromAdress(ADRES + "A?format=xml");
        return xmlTable(doc);
    }

    /** Pobiera tabelę z archiwalnymi kursami walut z określonej daty.
     * Zwraca null w przypadku błędów, np. wtedy, gdy dla danej daty nie istnieje tabela.
     */
    public static Table historyCourses(String data) {
        Document doc = loadXmlFromAdress(ADRES + "A/" + data + "?format=xml");
        return xmlTable(doc);
    }

    private static Document loadXmlFromAdress(String adres) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            URL url = new URL(adres);
            try (InputStream in = url.openStream()) {
                return db.parse(in);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Table xmlTable(Document doc) {
        if (doc == null)
            return null;
        try {
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();

            String nameOfTable = xpath.evaluate("//Table", doc);
            String numberOfTable = xpath.evaluate("//No", doc);
            LocalDate date = LocalDate.parse(xpath.evaluate("//EffectiveDate", doc));
            Table table = new Table(nameOfTable, numberOfTable, date);

            NodeList rates = (NodeList) xpath.evaluate("//Rate", doc, XPathConstants.NODESET);
            final int n = rates.getLength();
            for (int i = 0; i < n; i++) {
                Node rate = rates.item(i);
                String code = xpath.evaluate("Code", rate);
                String name = xpath.evaluate("Currency", rate);
                String cours = xpath.evaluate("Mid", rate);
                BigDecimal coursBD = new BigDecimal(cours);
                Currency currency = new Currency(code, name, coursBD);
                table.add(currency);
            }

            return table;
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }
}