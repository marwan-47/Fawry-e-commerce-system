import java.util.ArrayList;

public class ShippingService {
    public static double shipItems(ArrayList<CartItem> items) {
        System.out.println("** Shipment notice **");

        double totalWeight = 0;
        for (CartItem item : items) {
            Shippable product = (Shippable) item.getProduct();
            System.out.printf("%-15s %5.2fg%n",  item.getQuantity() + "x "+ product.getName(), (product.getWeight() * 1000));
            totalWeight += product.getWeight() * item.getQuantity();
        }
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
        return totalWeight;
    }
}
