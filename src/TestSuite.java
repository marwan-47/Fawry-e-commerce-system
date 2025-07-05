import java.util.*;

public class TestSuite {
    private static Date daysFromToday(int offset) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, offset);
        return cal.getTime();
    }

    public static void test1_validCheckout() {
        System.out.println("== Test 1: Valid Checkout ==");
        Customer customer = new Customer("Marwan", 500);
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, 0.4, daysFromToday(5));
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, 0.7, daysFromToday(3));
        customer.addToCart(cheese, 2);
        customer.addToCart(biscuits, 1);
        customer.Checkout();
    }

    public static void test2_expiredProduct() {
        System.out.println("== Test 2: Expired Product ==");
        Customer customer = new Customer("Marwan", 500);
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, 0.4, daysFromToday(-1));
        customer.addToCart(cheese, 1);
        customer.Checkout();
    }

    public static void test3_outOfStock() {
        System.out.println("== Test 3: Out of Stock ==");
        Customer customer = new Customer("Marwan", 500);
        Product tv = new ShippableProduct("TV", 200, 1, 2.0);
        customer.addToCart(tv, 2); 
        customer.Checkout();
    }

    public static void test4_emptyCart() {
        System.out.println("== Test 4: Empty Cart ==");
        Customer customer = new Customer("Marwan", 500);
        customer.Checkout();
    }

    public static void test5_insufficientBalance() {
        System.out.println("== Test 5: Insufficient Balance ==");
        Customer customer = new Customer("Ali", 100);
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, 0.5, daysFromToday(5));
        customer.addToCart(cheese, 3); 
        customer.Checkout();
    }

    public static void test6_nonShippableProduct() {
        System.out.println("== Test 6: Non-Shippable Product ==");
        Customer customer = new Customer("Marwan", 500);
        Product scratchCard = new Product("Scratch Card", 50, 5);
        customer.addToCart(scratchCard, 1);
        customer.Checkout();
    }

    public static void test7_mixShippableAndNonShippable() {
        System.out.println("== Test 7: Mixed Shippable + Non-Shippable ==");
        Customer customer = new Customer("Marwan", 500);
        Product tv = new ShippableProduct("TV", 200, 2, 2.0);
        Product scratchCard = new Product("Scratch Card", 50, 10);
        customer.addToCart(tv, 1);
        customer.addToCart(scratchCard, 2);
        customer.Checkout();
    }

    public static void test8_addSameProductTwice() {
        System.out.println("== Test 8: Add Same Product Twice ==");
        Customer customer = new Customer("Marwan", 500);
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 10, 0.4, daysFromToday(5));
        customer.addToCart(cheese, 1);
        customer.addToCart(cheese, 2); 
        customer.Checkout();
    }

    public static void test9_zeroQuantity() {
        System.out.println("== Test 9: Add Zero Quantity ==");
        Customer customer = new Customer("Marwan", 500);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 5, 0.7, daysFromToday(3));
        customer.addToCart(biscuits, 0);
        customer.Checkout();
    }

    public static void main(String[] args) {
        List<Runnable> tests = Arrays.asList(
            TestSuite::test1_validCheckout,
            TestSuite::test2_expiredProduct,
            TestSuite::test3_outOfStock,
            TestSuite::test4_emptyCart,
            TestSuite::test5_insufficientBalance,
            TestSuite::test6_nonShippableProduct,
            TestSuite::test7_mixShippableAndNonShippable,
            TestSuite::test8_addSameProductTwice,
            TestSuite::test9_zeroQuantity
        );

        for (Runnable test : tests) {
            try {
                test.run();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("\n----------------------------\n");
        }
    }
}
