import java.util.*;
import java.util.Vector;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * The systemManager class is responsible for managing the overall system
 * and interacting with the store, customers, and admins. It provides methods
 * for registering new customers and admins, logging in existing customers
 * and admins, and loading data from CSV files.
 * 
 * @author Adham Allam
 * @see <a href="https://www.linkedin.com/in/adham-allam/">My LinkedIn
 *      profile</a>
 */
public class systemManager {
    Scanner scanner = new Scanner(System.in);
    private Store store;

    /**
     * Sets the store instance variable to the specified Store object.
     *
     * @param store the Store object to use
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Constructor for the systemManager class.
     */
    public systemManager() {
    }

    /**
     * Registers a new customer by prompting the user for their name, email,
     * phone number, password, and address. Creates a new Customer object and
     * adds it to the store's list of customers.
     */
    public void register() {
        String name, email, phone, password;
        Map<String, String> m = new HashMap<>();
        m.put("Government", "");
        m.put("District", "");
        m.put("Street", "");
        m.put("Build.no", "");
        m.put("floor", "");
        m.put("flat", "");
        m.put("landmark", "");

        System.out.print("Enter your name: ");
        name = scanner.nextLine();

        System.out.print("Enter your email: ");
        email = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        phone = scanner.nextLine();

        password = getPassword();

        System.out.println("Enter your address: ");
        for (Map.Entry<String, String> entry : m.entrySet()) {
            System.out.print("Enter " + entry.getKey() + ": ");
            entry.setValue(scanner.nextLine());
        }

        OTPManager.Create(email);
        while (true) {
            System.out.println("OTP: ");
            if (OTPManager.otpNum == scanner.nextInt())
                break;
            System.out.println("Incorrect OTP :'(, Try again");
        }
        scanner.nextLine();
        System.out.println();
        Address newAddress = new Address(m.get("Government"), m.get("District"), m.get("Street"), m.get("Build.no"),
                m.get("floor"), m.get("flat"), m.get("landmark"));
        Customer newCustomer = new Customer(name, email, phone, password, newAddress);
        store.addCustomer(newCustomer);
        System.out.println("Registered in successfully ^_^\n");
    }

    /**
     * Registers a new admin by prompting the user for their name, email,
     * phone number, and password. Creates a new Admin object and adds it
     * to the store's list of admins.
     */
    public void registerAdmin() {
        String name, email, phone, password;

        System.out.print("Enter name: ");
        name = scanner.nextLine();

        System.out.print("Enter email: ");
        email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        phone = scanner.nextLine();

        password = getPassword();

        Admin newAdmin = new Admin(name, email, phone, password, store);
        store.addAdmin(newAdmin);
        System.out.println("Admin Added successfully ^_^\n");
    }

    /**
     * Prompts the user for their email and password and attempts to log in
     * the customer with the specified email and password. Returns the
     * Customer object if the login is successful, otherwise returns null.
     *
     * @return the logged in Customer object, or null if login fails
     */
    public Customer loginCustomer() {
        Customer current = null;
        String email;
        boolean b = false;
        while (!b) {
            System.out.print("Enter your Email: ");
            email = scanner.nextLine();

            String pass;
            System.out.println("1. Enter Password");
            System.out.println("2. Forget Password");
            System.out.println("Enter your choice: ");
            int npass = scanner.nextInt();
            scanner.nextLine();
            switch (npass) {
                case 1:
                    System.out.print("Enter your password: ");
                    pass = scanner.nextLine();

                    for (Customer i : store.getCustomers()) {
                        if (i.getEmail().equals(email)) {
                            if (i.getPassword().equals(pass)) {
                                b = true;
                                current = i;
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("We will send you an OTP in a moment...");
                    OTPManager.Create(email);
                    int otp;
                    do {
                        System.out.println("Enter OTP: ");
                        otp = scanner.nextInt();
                        scanner.nextLine();
                        if (otp != OTPManager.otpNum) {
                            System.out.println("Wrong OTP");
                        } else {
                            for (Customer i : store.getCustomers()) {
                                if (i.getEmail().equals(email)) {
                                    b = true;
                                    current = i;
                                    break;
                                }
                            }
                        }
                    } while (otp != OTPManager.otpNum);
                    break;
                default:
                    break;
            }
            if (b) {
                System.out.println("Logged in successfully :)");
                System.out.println("Hi " + current.getName() + "!\n");
            } else {
                System.out.println("Wrong Input! Try Again");
            }
        }
        return current;
    }

    /**
     * Prompts the user for their email, password, and admin credentials
     * and attempts to log in the admin with the specified email and password.
     * Returns the Admin object if the login is successful, otherwise returns
     * null.
     *
     * @return the logged in Admin object, or null if login fails
     */
    public Admin loginAdmin() {
        Admin current = null;
        String email;
        boolean b = false;
        System.out.println("Enter your credential: ");
        String cred = scanner.nextLine();

        if (!cred.equals("admin123")) {
            System.out.println("Invalid Credential!\n");
            return current;
        }
        while (!b) {
            System.out.print("Enter your Email: ");
            email = scanner.nextLine();

            String pass;

            System.out.print("Enter your password: ");
            pass = scanner.nextLine();

            for (Admin i : store.getAdmins()) {
                if (i.getEmail().equals(email)) {
                    if (i.getPassword().equals(pass)) {
                        b = true;
                        current = i;
                        break;
                    }
                }
            }
            if (b) {
                System.out.println("Logged in successfully :)\n\n");
                System.out.println("Hi " + current.getName() + "!");
            } else {
                System.out.println("Wrong Input! Try Again");
            }
        }
        return current;
    }

    /**
     * Prompts the user for a password and confirms that the user enters the
     * same password twice. Returns the password if the confirmation is
     * successful, otherwise prompts the user to try again.
     *
     * @return the confirmed password
     */
    public String getPassword() {
        String pass, password;

        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        while (true) {
            System.out.print("Confirm your password: ");
            pass = scanner.nextLine();
            if (!password.equals(pass))
                System.out.println("Password not matches, Try Again!");
            else
                break;
        }
        return pass;
    }

    /**
     * Loads customer data from a CSV file and returns a vector of Customer
     * objects.
     *
     * @return a vector of Customer objects loaded from the CSV file
     * @throws Exception if an error occurs while reading the CSV file
     */
    public Vector<Customer> loadCustomers() throws Exception {
        List<Map<String, String>> allData = new ArrayList<>();
        Vector<Customer> finalData = new Vector<>();
        CSVReader reader = new CSVReader(new FileReader("../DataBase/Customers.csv"));
        String[] header = reader.readNext();
        Map<String, String> row;
        while ((row = readNextRow(reader, header)) != null) {
            allData.add(row);
        }
        for (Map<String, String> data_row : allData) {
            String name = data_row.get("name");
            String email = data_row.get("email");
            String phone = data_row.get("phone");
            String password = data_row.get("password");
            String address = data_row.get("address");
            Address a = new Address(address);
            finalData.add(new Customer(name, email, phone, password, a));
        }

        reader.close();
        return finalData;
    }

    /**
     * Loads orders data from a CSV file and returns a vector of Order
     * objects.
     *
     * @return a vector of Order objects loaded from the CSV file
     * @throws Exception if an error occurs while reading the CSV file
     */
    public Vector<Order> loadOrders() throws Exception {
        List<Map<String, String>> allData = new ArrayList<>();
        Vector<Order> finalData = new Vector<>();
        CSVReader reader = new CSVReader(new FileReader("../DataBase/Orders.csv"));
        String[] header = reader.readNext();
        Map<String, String> row;
        while ((row = readNextRow(reader, header)) != null) {
            allData.add(row);
        }
        for (Map<String, String> data_row : allData) {
            int ID = Integer.parseInt(data_row.get("ID"));
            String date = data_row.get("date");
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            Address address = new Address(data_row.get("address"));
            String status = data_row.get("status");
            double price = Double.parseDouble(data_row.get("price"));
            ShoppingCart cart = new ShoppingCart();
            cart.setCart(data_row.get("cart"), store.getCatalog());
            finalData.add(new Order(ID, dateTime, address, cart, price, status));
        }

        reader.close();
        return finalData;
    }

    /**
     * Loads admin data from a CSV file and returns a vector of Admin objects.
     *
     * @return a vector of Admin objects loaded from the CSV file
     * @throws Exception if an error occurs while reading the CSV file
     */
    public Vector<Admin> loadAdmins() throws Exception {
        Vector<Admin> finalData = new Vector<>();
        List<Map<String, String>> allData = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("../DataBase/Admins.csv"));
        String[] header = reader.readNext();
        Map<String, String> row;
        while ((row = readNextRow(reader, header)) != null) {
            allData.add(row);
        }
        for (Map<String, String> data_row : allData) {
            String name = data_row.get("name");
            String email = data_row.get("email");
            String phone = data_row.get("phone");
            String password = data_row.get("password");
            finalData.add(new Admin(name, email, phone, password, store));
        }

        reader.close();
        return finalData;
    }

    /**
     * Loads a catalog of items from a CSV file and returns it as a Catalog object.
     *
     * @return a Catalog object representing the loaded catalog of items.
     * @throws Exception if an error occurs while reading the CSV file.
     */
    public Catalog loadCatalog() throws Exception {
        Map<String, Vector<Item>> finalData = new HashMap<>();
        List<Map<String, String>> allData = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("../DataBase/Items.csv"));
        String[] header = reader.readNext();
        Map<String, String> row;
        while ((row = readNextRow(reader, header)) != null) {
            allData.add(row);
        }
        for (Map<String, String> data_row : allData) {
            int ID = Integer.parseInt(data_row.get("ID"));
            String category = data_row.get("category");
            String name = data_row.get("name");
            String description = data_row.get("description");
            String brand = data_row.get("brand");
            double price = Double.parseDouble(data_row.get("price"));
            double discount = Double.parseDouble(data_row.get("discount"));
            int amount = Integer.parseInt(data_row.get("amount"));

            Vector<Item> items = finalData.get(category);
            if (items == null) {
                items = new Vector<>();
                finalData.put(category, items);
            }
            items.add(new Item(ID, name, description, brand, price, discount, amount));
        }

        reader.close();
        return (new Catalog(finalData));
    }

    /**
     * Saves changes made to the store's data by writing it to CSV files.
     * This method overwrites the existing CSV files.
     *
     * @throws IOException if there is an error writing to the CSV files.
     */
    public void saveChanges() throws IOException {
        String adminCsvPath = "../DataBase/Admins.csv";
        String customerCsvPath = "../DataBase/Customers.csv";
        String itemCsvPath = "../DataBase/Items.csv";
        String orderCsvPath = "../DataBase/Orders.csv";
        // Clear existing data in CSV files
        Files.write(Paths.get(adminCsvPath), "".getBytes());
        Files.write(Paths.get(customerCsvPath), "".getBytes());
        Files.write(Paths.get(itemCsvPath), "".getBytes());
        Files.write(Paths.get(orderCsvPath), "".getBytes());

        // Write Admin data to CSV file
        BufferedWriter adminWriter = new BufferedWriter(new FileWriter(adminCsvPath));
        String adminhead = String.join(",", "name", "email", "phone", "password");
        adminWriter.write(adminhead);
        adminWriter.newLine();
        for (Admin admin : store.getAdmins()) {
            String adminRow = String.join(",", admin.getName(), admin.getEmail(), admin.getPhone(),
                    admin.getPassword());
            adminWriter.write(adminRow);
            adminWriter.newLine();
        }
        adminWriter.close();

        // Write Customer data to CSV file
        BufferedWriter customerWriter = new BufferedWriter(new FileWriter(customerCsvPath));
        String customerhead = String.join(",", "name", "email", "phone", "password", "address");
        customerWriter.write(customerhead);
        customerWriter.newLine();
        for (Customer customer : store.getCustomers()) {
            String customerRow = String.join(",", customer.getName(), customer.getEmail(), customer.getPhone(),
                    customer.getPassword(), customer.getAddress().getAddress());
            customerWriter.write(customerRow);
            customerWriter.newLine();
        }
        customerWriter.close();

        // Write Item data to CSV file
        BufferedWriter itemWriter = new BufferedWriter(new FileWriter(itemCsvPath));
        String itemhead = String.join(",", "ID", "name", "description", "brand", "price", "discount", "amount",
                "category");
        itemWriter.write(itemhead);
        itemWriter.newLine();
        for (Map.Entry<String, Vector<Item>> entry : store.getCatalog().getCatalog().entrySet()) {
            String itemCat = entry.getKey();
            for (Item item : entry.getValue()) {
                String itemRow = String.join(",", String.valueOf(item.getID()), item.getName(), item.getDescription(),
                        item.getBrand(),
                        String.valueOf(item.getPrice()), String.valueOf(item.getDiscount()),
                        String.valueOf(item.getAmount()), itemCat);
                itemWriter.write(itemRow);
                itemWriter.newLine();
            }
        }
        itemWriter.close();

        // Write order data to CSV file
        BufferedWriter orderWriter = new BufferedWriter(new FileWriter(orderCsvPath));
        String orderhead = String.join(",", "ID", "date", "address", "status", "price", "cart");
        orderWriter.write(orderhead);
        orderWriter.newLine();
        for (Order order : store.getOrders()) {
            Vector<Item> OrderItems = order.getItems();

            StringBuilder sb = new StringBuilder();
            for (Item i : OrderItems) {
                sb.append(i.getID());
                sb.append("-");
            }
            sb.setLength(sb.length() - 1);
            String result = sb.toString();
            LocalDateTime dateTime = order.getDateTime();
            String pattern = "yyyy-MM-dd HH:mm:ss";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String formattedDateTime = dateTime.format(formatter);
            String orderRow = String.join(",", String.valueOf(order.getID()),
                    formattedDateTime, order.getAddress(),
                    order.getStatus(), String.valueOf(order.getPrice()), result);
            orderWriter.write(orderRow);
            orderWriter.newLine();
        }
        orderWriter.close();
    }

    /**
     * Reads the next row of data from the CSV file and returns a Map object
     * containing the values of the row with the corresponding header keys.
     *
     * @param reader the CSVReader object used to read the data from the file
     * @param header an array of Strings containing the header values of the CSV
     *               file
     * @return a Map object containing the values of the row with the corresponding
     *         header keys
     * @throws IOException if an I/O error occurs while reading the file
     * @throws Exception   if an error occurs while parsing the row data
     */
    private static Map<String, String> readNextRow(CSVReader reader, String[] header) throws IOException, Exception {
        String[] values = reader.readNext();
        if (values == null) {
            return null;
        }
        Map<String, String> row = new HashMap<>();
        for (int i = 0; i < header.length; i++) {
            row.put(header[i], values[i]);
        }
        return row;
    }
}