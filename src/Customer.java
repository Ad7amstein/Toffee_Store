/**
 * Customer class - Defines a customer with various properties
 */
public class Customer extends User {
    private Address address;
    private ShoppingCart cart;

    /**
     * Constructor for the Customer class
     *
     * @param name     The name of the customer
     * @param email    The email of the customer
     * @param phone    The phone number of the customer
     * @param password The password of the customer
     * @param address  The address of the customer
     */
    public Customer(String name, String email, String phone, String password, Address address) {
        super(name, email, phone, password);
        this.address = address;
        cart = new ShoppingCart();
    }

    /**
     * Constructs a new instance of the Customer class.
     * This constructor initializes the address variable to null and creates a new
     * ShoppingCart object for the customer.
     * It also calls the constructor of the superclass (Object class) to initialize
     * any inherited variables.
     */
    public Customer(){
        super();
        this.address = null;
        cart = new ShoppingCart();
    }

    /**
     * Get the shopping cart of the customer
     *
     * @return The shopping cart of the customer
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Get the address of the customer
     *
     * @return The address of the customer
     */
    public Address getAddress() {
        return address;
    }
}
