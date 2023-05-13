import java.util.Vector;
import java.util.Scanner;
import java.util.Map;

/**
 * Represents a store which has a name, a catalog, a list of admins, a list of
 * customers,
 * and a queue of orders placed by customers.
 * 
 * @author 1. Adham Allam (Admin Part and some parts from customer part)
 * @see <a href="https://www.linkedin.com/in/adham-allam/">Adham LinkedIn
 *      profile</a>
 * @author 2. Omar Abdullah (Some parts from Customer Part)
 */
public class Store {
    private String name;
    private Vector<Order> orderQueue;
    private Vector<Admin> admins;
    private Vector<Customer> customers;
    private Catalog catalog;
    Scanner scanner = new Scanner(System.in);

    /**
     * Initializes a Store object with name, catalog, admins, and customers.
     * 
     * @param name      the name of the store
     * @param catalog   the catalog of the store
     * @param admins    the list of admins of the store
     * @param customers the list of customers of the store
     */
    public void setStore(String name, Catalog catalog, Vector<Admin> admins, Vector<Customer> customers) {
        System.out.println("Welcome to " + name + " store ^_^\n");
        this.name = name;
        this.catalog = catalog;
        orderQueue = new Vector<>();
        this.admins = admins;
        this.customers = customers;
    }

    /**
     * Initializes a Store object without any parameter.
     */
    public Store() {
    }

    /**
     * Adds an admin to the list of admins of the store.
     * 
     * @param admin the admin to be added to the list
     */
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    /**
     * Adds a customer to the list of customers of the store.
     * 
     * @param customer the customer to be added to the list
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Adds an order to the queue of orders placed by customers.
     * 
     * @param order the order to be added to the queue
     */
    public void addOrder(Order order) {
        orderQueue.add(order);
    }

    /**
     * Sets the catalog of the store.
     * 
     * @param cat the catalog to be set
     */
    public void setCatalog(Catalog cat) {
        this.catalog = cat;
    }

    public void setOrders(Vector<Order> orders){
        this.orderQueue = orders;
    }
    /**
     * Returns the catalog of the store.
     * 
     * @return the catalog of the store
     */
    public Catalog getCatalog() {
        return this.catalog;
    }

    /**
     * Sets the name of the store.
     * 
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the store.
     * 
     * @return the name of the store
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the queue of orders placed by customers.
     * 
     * @return the queue of orders placed by customers
     */
    public Vector<Order> getOrders() {
        return orderQueue;
    }

    /**
     * Returns the list of customers of the store.
     * 
     * @return the list of customers of the store
     */
    public Vector<Customer> getCustomers() {
        return this.customers;
    }

    /**
     * Returns the list of admins of the store.
     * 
     * @return the list of admins of the store
     */
    public Vector<Admin> getAdmins() {
        return this.admins;
    }

    /**
     * Allows a customer to shop and add items to their cart.
     * 
     * @param curr the current customer who is shopping
     */
    public void shopping(Customer curr) {
        String cat_name = "";
        System.out.println("Choose a category: ");
        catalog.showCategories();
        System.out.println("Enter category number or zero(0) to finish shopping: ");
        int cat = scanner.nextInt();
        System.out.println();
        if (cat == 0) {
            return;
        }
        int cnt = 1;
        for (Map.Entry<String, Vector<Item>> items : catalog.getCatalog().entrySet()) {
            if (cnt == cat) {
                cat_name = items.getKey();
                catalog.showItems(items.getKey());
                break;
            }
            cnt++;
        }
        if (cnt != cat) {
            System.out.println("Invalid category number.");
            shopping(curr);
            return;
        }

        while (true) {
            System.out.println("Press 0 to Back to catalog");
            System.out.println("Press -1 to go to your cart");
            System.out.println("Press item number to add it to your cart: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (choice == 0) {
                shopping(curr);
                return;
            } else if (choice == -1) {
                curr.getCart().view();
                System.out.println("Total price = " + curr.getCart().calc() + "L.E");
                System.out.println();
                System.out.println("1. Go To Checkout $_$");
                System.out.println("2. Back To Menu");
                System.out.println("3. Remove item");
                System.out.println("Enter your choice here: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();

                if (choice == 1) {
                    if (curr.getCart().getItems().size() == 0) {
                        System.out.println("No items added to cart X_X\n");
                        shopping(curr);
                        return;
                    }
                    Checkout co = new Checkout(curr);
                    Order newOrder = co.createOrder();
                    orderQueue.add(newOrder);
                    newOrder.setOrderNum(orderQueue.size());
                    System.out.println("Your Order is " + newOrder.getStatus() + "...\n");
                    shopping(curr);
                    return;
                } else if (choice == 2) {
                    shopping(curr);
                    return;
                } else if (choice == 3) {
                    System.out.println("Enter item number: ");
                    int item_num = scanner.nextInt() - 1;
                    System.out.println();
                    if (item_num >= curr.getCart().getItems().size()) {
                        System.out.println("Invalid Item no. ;(\n");
                        shopping(curr);
                        return;
                    } else {
                        curr.getCart().getItems().remove(item_num);
                        System.out.println("Item removed successfuly :-)\n");
                    }
                    System.out.println();
                } else {
                    System.out.println("Invalid Choice ;(\n");
                    shopping(curr);
                    return;
                }
            } else {
                int size = catalog.getCatalog().get(cat_name).size();
                if (choice - 1 >= size)
                    System.out.println("Invalid Item no. ;(\nTry Again!\n");
                else {
                    Item newItem = catalog.getCatalog().get(cat_name).get(choice - 1);
                    curr.getCart().addItem(newItem);
                    System.out.println("Item added successfuly :-)\n");
                }
            }
        }
    }

    /**
     * 
     * Allows an admin user to perform various actions such as viewing the catalog,
     * viewing orders, adding a new category,
     * and adding a new item. The method prompts the admin user to enter their
     * choice and based on the choice selected,
     * performs the corresponding action.
     * 
     * @param curr An instance of the Admin class representing the current admin
     *             user.
     */
    public void startAdmin(Admin curr) {
        String cat_name = "";
        System.out.println("1. View catalog");
        System.out.println("2. View orders");
        System.out.println("3. Add new category");
        System.out.println("4. Add new item");

        System.out.println("Enter your choice here or press 0 to back: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        if (choice == 0) {
            System.out.println("Don't forget to save the changes X_X\n");
            return;
        }
        if (choice == 1) {
            System.out.println("Choose a category: ");
            catalog.showCategories();
            System.out.println("Enter category number or zero(0) for all: ");
            int cat = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            int cnt = 1;
            for (Map.Entry<String, Vector<Item>> items : catalog.getCatalog().entrySet()) {
                if (cnt == cat) {
                    cat_name = items.getKey();
                    catalog.showItems(items.getKey());
                    break;
                }
                cnt++;
            }
            if (cat == 0) {
                catalog.showCatalog();
            } else if (cnt != cat) {
                System.out.println("Invalid category number.");
                startAdmin(curr);
                return;
            }

            while (true) {
                System.out.println("1.Update item");
                System.out.println("2.Remove item");
                System.out.println("3.Remove category");
                System.out.println("Enter your choice or 0 to back: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                if (choice == 0) {
                    startAdmin(curr);
                    return;
                } else if (choice == 1) {
                    if (cat == 0) {
                        System.out.println("Enter category name: ");
                        cat_name = scanner.nextLine();
                    }
                    if (catalog.getCatalog().get(cat_name) == null) {
                        System.out.println("Invalid category name ;(\n");
                        continue;
                    } else {
                        System.out.println("Enter item number: ");
                        int item_num = scanner.nextInt() - 1;
                        scanner.nextLine();
                        System.out.println();
                        if (catalog.getCatalog().get(cat_name).size() <= item_num) {
                            System.out.println("Invalid item number ;(\n");
                            continue;
                        }
                        curr.updateItem(catalog.getCatalog().get(cat_name).get(item_num));
                        System.out.println("\nItem Updated successfuly :-)\n");
                    }
                } else if (choice == 2) {
                    if (cat == 0) {
                        System.out.println("Enter category name: ");
                        cat_name = scanner.nextLine();
                    }
                    if (catalog.getCatalog().get(cat_name) == null) {
                        System.out.println("Invalid category name ;(\n");
                        continue;
                    } else {
                        System.out.println("Enter item number: ");
                        int item_num = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (catalog.getCatalog().get(cat_name).size() <= item_num) {
                            System.out.println("Invalid item number ;(\n");
                            continue;
                        }
                        curr.removeItem((catalog.getCatalog().get(cat_name).get(item_num)), cat_name);
                        System.out.println("Item Removed successfuly :-)\n");
                    }
                } else if (choice == 3) {
                    if (cat == 0) {
                        System.out.println("Enter category name: ");
                        cat_name = scanner.nextLine();
                    }
                    if (catalog.getCatalog().get(cat_name) == null) {
                        System.out.println("Invalid category name ;(\n");
                        continue;
                    } else {
                        curr.removeCategory(cat_name);
                        System.out.println("Category removed successfuly ;]\n");
                    }
                } else {
                    System.out.println("Invalid choice ;(\n");
                }
            }

        } else if (choice == 2) {
            System.out.println("0. All");
            System.out.println("1. In Queued orders");
            System.out.println("2. Canceled orders");
            System.out.println("3. Done orders");

            System.out.println("Enter your choice here: ");
            int filter = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            String stat = "";
            boolean ok = false;
            if (filter == 0) {
                ok = true;
                curr.vieworders();
            } else if (filter == 1)
                stat = "In Queue";
            else if (filter == 2)
                stat = "Canceled";
            else if (filter == 3)
                stat = "Done";
            else {
                System.out.println("Invalid choice ;(\n");
                startAdmin(curr);
                return;
            }
            if (filter != 0){
                for (Order order : orderQueue) {
                    if (order.getStatus().equals(stat)) {
                        order.viewOrder();
                        ok = true;
                    }
                }
            }
            if (!ok) {
                System.out.println("Empty, no " + stat + " orders\n");
                startAdmin(curr);
                return;
            }
            System.out.println("Enter order number to update status or 0 to back: ");
            int order_num = scanner.nextInt() - 1;
            scanner.nextLine();
            if (order_num >= orderQueue.size() || order_num < 0) {
                System.out.println("Invalid choice ;(\n");
                startAdmin(curr);
                return;
            }
            System.out.println("Choose the new status: ");
            System.out.println("1. In Queue");
            System.out.println("2. Canceled");
            System.out.println("3. Done");
            System.out.println("Enter your choice here: ");
            int orderStat = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            if (orderStat == 1){
                orderQueue.get(order_num).updateStatus("In Queue");
            } else if (orderStat == 2){
                orderQueue.get(order_num).updateStatus("Canceled");
            } else if (orderStat == 3){
                orderQueue.get(order_num).updateStatus("Done");
            }
            else {
                System.out.println("Invalid choice ;(\n");
                startAdmin(curr);
                return;
            }
            System.out.println("Status Updated successfuly :-)\n");
            startAdmin(curr);
            return;
        } else if (choice == 3) {
            System.out.println("Enter the new category name: ");
            curr.addCategory(scanner.nextLine());
            System.out.println("Category added successfuly :-)\n");
            startAdmin(curr);
            return;
        } else if (choice == 4) {
            String item_s, item_cat;
            double item_d;
            int item_i;
            Item newItem = new Item();
            System.out.println("Enter item category: ");
            item_cat = scanner.nextLine();
            System.out.println("Enter item name: ");
            item_s = scanner.nextLine();
            newItem.setName(item_s);
            System.out.println("Enter item description: ");
            item_s = scanner.nextLine();
            newItem.setDescription(item_s);
            System.out.println("Enter item brand: ");
            item_s = scanner.nextLine();
            newItem.setBrand(item_s);
            System.out.println("Enter item price: ");
            item_d = scanner.nextDouble();
            newItem.setPrice(item_d);
            System.out.println("Enter item discount: ");
            item_d = scanner.nextDouble();
            newItem.setDiscount(item_d);
            System.out.println("Enter item amount: ");
            item_i = scanner.nextInt();
            scanner.nextLine();
            newItem.setAmount(item_i);
            curr.addItem(newItem, item_cat);
            System.out.println("Item added successfully\n");
            startAdmin(curr);
            return;
        } else {
            System.out.println("Invalid choice ;(\n");
            startAdmin(curr);
            return;
        }
    }
}
