import java.util.Date;

public class ExpirableShippableProduct extends Product implements Shippable{
    private double weight;
    private Date expirayDate;

    public ExpirableShippableProduct(String name, double price, int quantity, double weight, Date expirayDate) {
        super(name, price, quantity);
        this.expirayDate = expirayDate;
        this.weight = weight;
    }

    public Date getExpirayDate(){return expirayDate;}
    public void setExpirayDate(Date expirayDate){this.expirayDate = expirayDate;}

    @Override
    public double getWeight() {return weight;}

    @Override
    public String getName() {return super.getName();}

    public void setWeight(double weight){this.weight = weight;}
}
