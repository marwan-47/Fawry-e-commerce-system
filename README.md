# Fawry E-Commerce System (Java)

This is a simple Java-based e-commerce system for the Fawry Rise Journey Internship Program.

##  Features
- Define products with name, price, quantity
- Supports expirable and shippable products
- Validates stock, expiry date, and customer balance
- Handles shipping with weight calculation
- Checkout with itemized receipt and shipment notice
- Covers all edge cases with test scenarios

##  Run the Tests
```bash
javac -cp src TestSuite.java
java -cp .;src TestSuite    # On Windows
# or use ':' instead of ';' on Linux/macOS:
# java -cp .:src TestSuite
```

##  Structure
- `Product.java` – base class for all products
- `ShippableProduct.java` / `ExpirableProduct.java` – specialized product types
- `Customer.java` – manages cart and checkout
- `ShippingService.java` – prints shipment info
- `TestSuite.java` – includes 9 automated test cases

##  Author
Marwan Ahmed Hassen
