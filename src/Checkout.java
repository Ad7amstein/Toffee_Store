import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Checkout class represents the process of finalizing an order by
 * specifying an address for delivery.
 */
public class Checkout {
    Scanner scanner = new Scanner(System.in);
    private Customer customer;

    /**
     * Constructs a Checkout object for the given customer.
     *
     * @param customer the customer who is checking out
     */
    public Checkout(Customer customer) {
        this.customer = customer;
    }

    /**
     * Prompts the user to specify an address for the order.
     * 
     * @return the Address object representing the chosen address
     */
    public Address specifyAddress() {
        System.out.println("Do you want to use your saved address?");
        System.out.println("1. Yes");
        System.out.println("2. No, add new address");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        if (choice == 2) {
            Map<String, String> addressMap = new HashMap<>();
            addressMap.put("Government", "");
            addressMap.put("District", "");
            addressMap.put("Street", "");
            addressMap.put("Build.no", "");
            addressMap.put("floor", "");
            addressMap.put("flat", "");
            addressMap.put("landmark", "");

            for (String key : addressMap.keySet()) {
                System.out.print("Enter " + key + ": ");
                String value = scanner.nextLine();
                addressMap.put(key, value);
            }

            Address newAddress = new Address(
                    addressMap.get("Government"),
                    addressMap.get("District"),
                    addressMap.get("Street"),
                    addressMap.get("Build.no"),
                    addressMap.get("floor"),
                    addressMap.get("flat"),
                    addressMap.get("landmark"));
            return newAddress;
        } else {
            return customer.getAddress();
        }
    }

    /**
     * Creates a new order for the customer's cart using the specified address.
     *
     * @return the Order object representing the newly created order
     */
    Order createOrder()
    {
        Order order = new Order(customer.getCart(), specifyAddress());
        return order;
    }
}
