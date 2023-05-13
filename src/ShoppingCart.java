import java.util.Vector;

/**
 * ShoppingCart class - Defines a shopping cart with various properties
 */
public class ShoppingCart {
    private Vector<Item> items;

    /**
     * Constructor for ShoppingCart class
     */
    public ShoppingCart() {
        items = new Vector<Item>();
    }

    /**
     * Add an item to the shopping cart
     * 
     * @param item the item to add to the cart
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Remove an item from the shopping cart
     * 
     * @param item the item to remove from the cart
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * View the items in the shopping cart
     */
    public void view() {
        int i = 0;
        for (Item item : items) {
            i++;
            System.out.println("item " + i + ":");
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Brand: " + item.getBrand());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Discount: " + item.getDiscount());
            System.out.println("------------------------------------------");
        }
    }

    /**
     * Calculate the total cost of the items in the shopping cart
     * 
     * @return the total cost of the items in the cart
     */
    public double calc() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() - item.getPrice() * item.getDiscount();
        }
        return total;
    }
    
    /**
     * Returns the list of items in the vector.
     * 
     * @return a vector of items
     */
    public Vector<Item> getItems(){
        return items;
    }

    public void setCart(String items, Catalog cat){
        String[] values = items.split("-");
        for (int i = 0; i < values.length; i++){
            int ID = Integer.parseInt(String.valueOf(values[i]));
            this.items.add(cat.getItem(ID));
        }
    }
}
