import java.util.Scanner;
import java.util.Vector;

/**
 * The Admin class represents an administrator of a store system. It extends the
 * User class
 * and has access to add, update, and delete items from the store catalog, as
 * well as view orders.
 */
public class Admin extends User {
    Scanner scanner = new Scanner(System.in);
    private Store store;

    /**
     * Constructs an Admin object with the given name, email, phone number, and
     * password.
     * 
     * @param name     the name of the admin
     * @param email    the email address of the admin
     * @param phone    the phone number of the admin
     * @param password the password of the admin
     * @param store    the store associated with the admin
     */
    public Admin(String name, String email, String phone, String password, Store store) {
        super(name, email, phone, password);
        this.store = store;
    }

    /**
     * Adds an item to the store catalog under the given category.
     * 
     * @param item the item to be added
     * @param cat  the category to add the item under
     */
    public void addItem(Item item, String cat) {
        store.getCatalog().addItem(item, cat);
    }

    /**
     * Removes an item from the store catalog under the given category.
     * 
     * @param item the item to be removed
     * @param cat  the category to remove the item from
     */
    public void removeItem(Item item, String cat) {
        store.getCatalog().removeItem(item, cat);
    }

    /**
     * Updates the information of an item in the store catalog.
     * 
     * @param item the item to be updated
     */
    public void updateItem(Item item) {
            int choice = 0;
            String s;
            double d;
            int p;
            System.out.println("What do you want to update?\n");
            item.viewItem();
            System.out.println("Enter your choice here or zero(0) to exit: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (choice == 0) {
                scanner.close();
                return;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter the new name: ");
                    s = scanner.nextLine();
                    item.setName(s);
                    break;
                case 2:
                    System.out.println("Enter the new description: ");
                    s = scanner.nextLine();
                    item.setDescription(s);
                    break;
                case 3:
                    System.out.println("Enter the new brand: ");
                    s = scanner.nextLine();
                    item.setBrand(s);
                    break;
                case 4:
                    System.out.println("Enter the new price: ");
                    d = scanner.nextDouble();
                    item.setPrice(d);
                    break;
                case 5:
                    System.out.println("Enter the new discount: ");
                    d = scanner.nextDouble();
                    item.setPrice(d);
                    break;
                case 6:
                    System.out.println("Enter the new amount: ");
                    p = scanner.nextInt();
                    item.setPrice(p);
                    break;
                default:
                    System.out.println("Invalid Choice Try Again");
                    updateItem(item);
                    break;
            }
    }

    /**
     * Adds a new category to the store catalog.
     * 
     * @param cat the name of the new category to add
     */
    public void addCategory(String cat){
        store.getCatalog().addCategory(cat);
    }

    /**
     * Removes a category from the store catalog.
     *
     * @param cat the category to remove
     */
    public void removeCategory(String cat){
        store.getCatalog().removeCategory(cat);
    }

    /**
     * Displays all orders in the store.
     */
    public void vieworders() {
        Vector<Order> orders = store.getOrders();
        for (Order order : orders){
            order.viewOrder();
            System.out.println("___________________________________\n");
        }
    }
}