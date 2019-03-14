import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Table {

    private final String nameOfTable;
    private final String numberOfTable;
    private final LocalDate date;
    private final Map<String, Currency> currencies = new TreeMap<>();

    public Table(){
        nameOfTable = null;
        numberOfTable = null;
        date = null;
    }

    public Table(String nameOfTable, String numberOfTable, LocalDate date) {
        this.nameOfTable = nameOfTable;
        this.numberOfTable = numberOfTable;
        this.date = date;
    }

    public String getNameOfTable() {
        return nameOfTable;
    }

    public String getNumberOfTable() {
        return numberOfTable;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    @Override
    public String toString() {
        return "Table: " + nameOfTable + ", Number of Table:" + numberOfTable + ", Date: " + date
                + " (" + currencies.size() + " currencies";
    }

    public void add(Currency currency) {
        currencies.put(currency.getCode(), currency);
    }

    public Currency search(String code) {
        return currencies.get(code);
    }

    public Collection<Currency> getAllCurrency(){
        return currencies.values();
    }

    private static final String[] EMPTY_TABLE = new String[0];

    public String[] getCurrencyCode() {
        return currencies.keySet().toArray(EMPTY_TABLE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((nameOfTable == null) ? 0 : nameOfTable.hashCode());
        result = prime * result + ((numberOfTable == null) ? 0 : numberOfTable.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((currencies == null) ? 0 : currencies.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Table other = (Table) obj;

        if(nameOfTable == null) {
            if(other.nameOfTable != null) {
                return false;
            }else if (!nameOfTable.equals(other.nameOfTable)) {
                return false;
            }
        }
        if(numberOfTable == null) {
            if(other.numberOfTable != null) {
                return false;
            }else if (!numberOfTable.equals(other.numberOfTable)) {
                return false;
            }
        }
        if(date == null) {
            if(other.date != null) {
                return false;
            }else if (!date.equals(other.date)) {
                return false;
            }
            if(currencies == null) {
                if(other.currencies != null) {
                    return false;
                }else if (!currencies.equals(other.currencies)) {
                    return false;
                }
            }
        }

        return true;
    }
}
