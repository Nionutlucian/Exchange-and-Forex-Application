package hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Currencypairs implements Serializable{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;
    private String pair;
    private Double values_pairs;

    public double getValues_pairs() {
        return values_pairs;
    }
    public void setValues_pairs(double values_pairs) {
        this.values_pairs = values_pairs;
    }
    @Column
    private Date createDateTime;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPair() {
        return pair;
    }
    public void setPair(String pair) {
        this.pair = pair;
    }
    public Date getDateTime() {
        return createDateTime;
    }
    public void setDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currencypairs that = (Currencypairs) o;

        if (id != that.id) return false;
        if (pair != null ? !pair.equals(that.pair) : that.pair != null) return false;
        if (values_pairs != null ? !values_pairs.equals(that.values_pairs) : that.values_pairs != null) return false;
        if (createDateTime != null ? !createDateTime.equals(that.createDateTime) : that.createDateTime != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pair != null ? pair.hashCode() : 0);
        result = 31 * result + (values_pairs != null ? values_pairs.hashCode() : 0);
        result = 31 * result + (createDateTime != null ? createDateTime.hashCode() : 0);
        return result;
    }
}
