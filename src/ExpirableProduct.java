import java.util.Date;

public class ExpirableProduct extends Product{
    private Date expirayDate;

    public ExpirableProduct(String name, double price, int quantity, Date expirayDate) {
        super(name, price, quantity);
        this.expirayDate = expirayDate;
    }

    public Date getExpirayDate(){return expirayDate;}
    public void setExpirayDate(Date expirayDate){this.expirayDate = expirayDate;}
}
