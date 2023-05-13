import java.util.Scanner;
import java.util.Vector;

/**
 * The StoreManager class handles the overall flow of the store system,
 * including registering and logging in users,
 * displaying the catalog of products, and managing the store (for admins).
 * 
 * @author Adham Allam
 * @see <a href="https://www.linkedin.com/in/adham-allam/">My LinkedIn
 *      profile</a>
 */
public class StoreManager {
    Scanner scanner = new Scanner(System.in);
    private Store store;
    private systemManager sys;

    /**
     * Creates a new instance of StoreManager and initializes the store with data
     * from the systemManager.
     * 
     * @throws Exception if there is an error loading the customers, admins, or
     *                   catalog data
     */
    public StoreManager() throws Exception {
        this.store = new Store();
        sys = new systemManager();
        sys.setStore(this.store);
        Vector<Customer> customers = sys.loadCustomers();
        Vector<Admin> admins = sys.loadAdmins();
        Catalog cat = sys.loadCatalog();
        this.store.setStore("Toffee", cat, admins, customers);
        Vector<Order> orders = sys.loadOrders();
        this.store.setOrders(orders);
    }

    /**
     * Displays the main menu of the store system and handles user input to perform
     * the selected action.
     * 
     * @throws Exception if there is an error registering, logging in, or saving
     *                   changes to the system
     */
    public void startStore() throws Exception {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Catalog");
        System.out.println("4. Admin");
        System.out.print("Enter your choice here: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                sys.register();
                sys.saveChanges();
                System.out.println("Please Login to continue");
                Customer currCustomer = sys.loginCustomer();
                store.shopping(currCustomer);
                break;
            case 2:
                currCustomer = sys.loginCustomer();
                store.shopping(currCustomer);
                break;
            case 3:
                store.getCatalog().showCatalog();
                startStore();
                return;
            case 4:
                Admin currAdmin = sys.loginAdmin();
                if (currAdmin == null) {
                    startStore();
                    return;
                }
                while (true) {
                    System.out.println("1. Manage store");
                    System.out.println("2. add new admin");
                    System.out.println("3. Save changes and exit");
                    System.out.println("Enter you choice here: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();
                    if (choice == 1)
                        store.startAdmin(currAdmin);
                    else if (choice == 2)
                        sys.registerAdmin();
                    else if (choice == 3) {
                        sys.saveChanges();
                        System.out.println("Changes saved successfuly ^.^");
                        System.out.println("Thanks for you time!");
                        System.out.println("See you ^_-");
                        return;
                    } else {
                        System.out.println("Invalid Choice!, Try Again\n");
                        startStore();
                        return;
                    }
                }
            default:
                System.out.println("Invalid Choice!, Try Again\n");
                startStore();
                return;
        }
        sys.saveChanges();
        System.out.println("Thanks for shopping today!");
        System.out.println("See you ^_+");
    }
}