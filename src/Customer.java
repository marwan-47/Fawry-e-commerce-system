import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private String name;
    private double balance = 0.0;
    private ArrayList<CartItem> cart;

    public Customer(String name,double balance){
        this.name = name;
        this.balance = balance;
        this.cart = new ArrayList<>();
    }

    public String getName() {return name;}
    public double getBalance() {return balance;}

    public void setName(String name){this.name = name;}
    public void addToBalance(double amount){this.balance += amount;}


    public void addToCart(Product product,int quantity){
        if(quantity == 0){
            System.out.println("Error: Quantity can't be 0");
            return;
        }

        if(product.getQuantity()<quantity){
            if(product.getQuantity() == 0){
                System.out.println("Out of stock");
                return;
            }
            System.out.println("Insufficient quantity: Only " + product.getQuantity() + " in stock");
            return;
        }
        
        for(CartItem item : cart){
            if(item.getProduct().getName().equals(product.getName())){
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Product already in cart: Updated quantity successfully");
                return;
            }
        }
        cart.add(new CartItem(product, quantity));
        System.out.println("Product added successfully");

    }

    public void Checkout(){
        if (cart.isEmpty()){
            System.out.println("Your cart is empty");
            return;
        }
            
        Date today = new Date();
        for(CartItem item : cart){
            Product product = item.getProduct();

            if(product.getQuantity()<item.getQuantity()){
                if(product.getQuantity() == 0){
                    System.out.println("The following product is out of stock: " + product.getName());
                    return;
                }
                System.out.println("Insufficient quantity for "+ product.getName() + ": Only " + product.getQuantity() + " in stock");
                return;
            }

            if(product instanceof ExpirableProduct){
                ExpirableProduct EXproduct = (ExpirableProduct) product;
                if(today.after(EXproduct.getExpirayDate())){
                    System.out.println("The following product is Expired: " + EXproduct.getName());
                    return;
                }
            }
            if(product instanceof ExpirableShippableProduct){
                ExpirableShippableProduct EXSHproduct = (ExpirableShippableProduct) product;
                if(today.after(EXSHproduct.getExpirayDate())){
                    System.out.println("The following product is Expired: " + EXSHproduct.getName());
                    return;
                }
            }
        }

        double subTotal = 0.0;
        boolean shipmentRequirment = false;
        double shippingFees = 0.0;
        ArrayList<CartItem> shippedItems = new ArrayList<>();

        for(CartItem item : cart){
            subTotal += item.getProduct().getPrice() * item.getQuantity() ;
        }
        
        for(CartItem item : cart){
            Product product = item.getProduct();
            if(product instanceof Shippable){    //if at least on item is shippable then we need to make a shippment and apply a shipment fees
                shipmentRequirment = true;
                shippedItems.add(item);
            }
        }

        if(shipmentRequirment){
            System.out.println();
            double totalWeight = ShippingService.shipItems(shippedItems);
            System.out.println();
            shippingFees = 0.05 * totalWeight * 1000;  // Assumption: Shipping fee is 5% of the total weight of the shippment
        }

        if((subTotal+ shippingFees) > balance ){
            System.out.println("Insufficient balance");
            return;
        }
        else
            balance-=(subTotal + shippingFees); 

        System.out.println("** Checkout receipt **");
        for(CartItem item : cart){
            System.out.printf("%-15s %5.2f%n",item.getQuantity() + "x " + item.getProduct().getName(),item.getProduct().getPrice());
        }
        System.out.println("----------------------");
        System.out.printf("%-15s %5.2f%n","Subtotal" , subTotal);
        System.out.printf("%-15s %5.2f%n","Shipping" , shippingFees);
        System.out.printf("%-15s %5.2f%n","Amount" , subTotal + shippingFees);
        System.out.println("----------------------");
        System.out.printf("%-15s %5.2f%n","Your new Balance" , balance);
    }

}


