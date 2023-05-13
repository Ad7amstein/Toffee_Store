import java.time.LocalDateTime;
import java.util.Vector;
/**
 * Defines an order with various properties
 */
public class Order {
    private LocalDateTime date;
    private int ID;
    private Address address;
    private ShoppingCart cart;
    private double totalPrice;
    private String orderStatus;

    /**
     * Constructs an Order object with the specified shopping cart and delivery
     * address.
     * 
     * @param cart    the shopping cart associated with this order.
     * @param address the delivery address for this order.
     */
    public Order(ShoppingCart cart,Address address) {
        this.date = LocalDateTime.now();
        this.cart = cart;
        this.address = address;
        setTotalPrice();
        updateStatus("In Queue");
    }

    /**
     * Constructor for the Order class.
     * 
     * @param ID          the ID of the order
     * @param d           the date and time the order was made
     * @param address     the delivery address for the order
     * @param cart        the shopping cart containing the items to be ordered
     * @param totalPrice  the total price of the order
     * @param orderStatus the status of the order (e.g. In Queue, In Progress,
     *                    Shipped, Delivered)
     */
    public Order(int ID, LocalDateTime d, Address address, ShoppingCart cart, double totalPrice, String orderStatus){
        this.ID = ID;
        this.date = d;
        this.address = address;
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    /**
     * Updates the status of the order
     * 
     * @param status The new status of the order
     */
    public void updateStatus(String status) {
        this.orderStatus = status;
    }

    /**
     * Cancels the order
     */
    public void cancelOrder() {
        orderStatus = "Canceled";
    }

    /**
     * Sets the address of the order
     * 
     * @param address The new address of the order
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Sets the total price of the order
     */
    public void setTotalPrice() {
        totalPrice = cart.calc();
    }

    /**
     * Gets the order number
     * 
     * @return The order number
     */
    public int getID() {
        return ID;
    }

    /**
     * Views the items in the shopping cart
     */
    public void viewOrder() {
        System.out.println("Order # " + ID);
        System.out.println("Date & Time: " + date);
        System.out.println("Delivery Address: " + address.getAddress());
        System.out.println("Order Status: " + orderStatus);
        System.out.println("Ordered Items: ");
        cart.view();
        System.out.println("Total Price: " + totalPrice + "L.E");
    }

    /**
     * Returns the current status of the order.
     * 
     * @return a string representing the current status of the order.
     */
    public String getStatus(){
        return orderStatus;
    }

    /**
     * Returns the date and time of the order.
     * 
     * @return the date and time of the order.
     */
    public LocalDateTime getDateTime(){
        return date;
    }

    /**
     * Returns the address of the order.
     * 
     * @return the address of the order.
     */
    public String getAddress(){
        return address.getAddress();
    }
    
    /**
     * Returns the items of the order.
     * 
     * @return the items of the order.
     */
    public Vector<Item> getItems(){
        return cart.getItems();
    }
    
    /**
     * Returns the total price of the order.
     * 
     * @return the total price of the order.
     */
    public double getPrice(){
        return totalPrice;
    }
    /**
     * Sets the order number for the order.
     * 
     * @param ID an integer representing the order number to be set for the
     *                    order.
     */
    public void setOrderNum(int ID){
        this.ID = ID;
    }
}
