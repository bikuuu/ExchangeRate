import java.math.BigDecimal;
import java.math.RoundingMode;

public class Currency {
    public final String name;
    public final String code;
    public final BigDecimal course;

    public Currency(String code, String name, BigDecimal course) {
        this.code = code;
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return code + " (" + name + "): " + course;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getCourse() {
        return course;
    }

    public BigDecimal toPLN(BigDecimal amount) {
        return amount.multiply(course).setScale(2);
    }

    public BigDecimal toCurrency(BigDecimal amount) {
        return amount.divide(amount, 2, RoundingMode.HALF_UP);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((code == null) ? 0 : course.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Currency other = (Currency) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            } else if (!code.equals(other.code)) {
                return false;
            }

            if (name == null) {
                if (other.name != null) {
                    return false;
                } else if (!name.equals(other.name)) {
                    return false;
                }
            }
            if (course == null) {
                if (other.course != null) {
                    return false;
                } else if (!course.equals(other.course)) {
                    return false;
                }
            }
        }
        return true;
    }

}
