/**
 * Defines an item with various properties
 */
public class Item {
    private int ID;
    private String name;
    private String description;
    private String brand;
    private double price;
    private double discount = 0;
    private int amount;

    /**
     * Constructs an item with the given properties
     * 
     * @param ID          The id of the item
     * @param name        The name of the item
     * @param description A description of the item
     * @param brand       The brand of the item
     * @param price       The price of the item
     * @param discount    The discount on the item (if any)
     * @param amount      The amount of items available
     */
    public Item(int ID, String name, String description, String brand, double price, double discount, int amount) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.discount = discount;
        this.amount = amount;
    }

    /**
     * Sets the ID of the item.
     * 
     * @param ID an integer value representing the ID of the item
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Returns the ID of the item.
     * 
     * @return an integer value representing the ID of the item
     */
    public int getID() {
        return ID;
    }

    /**
     * Constructs an item with the default values (undefined)
     */
    public Item() {
        this.name = "undefined";
        this.description = "undefined";
        this.brand = "undefined";
        this.price = 0.0;
        this.discount = 0.0;
        this.amount = 0;
    }

    /**
     * Sets the name of the item
     * 
     * @param name The name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the brand of the item
     * 
     * @param brand The brand of the item
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the description of the item
     * 
     * @param description The description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the price of the item
     * 
     * @param price The price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the discount on the item
     * 
     * @param discount The discount on the item
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Sets the amount of items available
     * 
     * @param amount The amount of items available
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the name of the item
     * 
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the item
     * 
     * @return The description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the brand of the item
     * 
     * @return The brand of the item
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the discount on the item
     * 
     * @return The discount on the item
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Gets the price of the item
     * 
     * @return The price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the amount of the item.
     * 
     * @return the amount of the item.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Prints a formatted view of the item to the console
     */
    public void viewItem() {
        System.out.println("1. Name: " + name);
        System.out.println("2. Description: " + description);
        System.out.println("3. Brand: " + brand);
        System.out.println("4. Price: " + price);
        System.out.println("5. Discount: " + discount);
        System.out.println("6. Amount: " + amount);
        System.out.println("------------------------------------------");
    }

}