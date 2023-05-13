/**
 * User - Represents a user account with associated information
 */
public abstract class User {
    private String name;
    private String email;
    private String phone;
    private String password;
    private boolean logIn;

    /**
     * User - Constructor for the User class
     */
    public User() {
        name = "undefined";
        email = "undefined";
        phone = "undefined";
        password = "undefined";
        logIn = false;
    }

    /**
     * User - Constructor for the User class with parameters
     * 
     * @param name     The name of the user
     * @param email    The email of the user
     * @param phone    The phone number of the user
     * @param password The password of the user
     */
    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        logIn = true;
    }

    /**
     * forgotPassword - Change the password of a user if they forgot it
     * 
     * @param newPass The new password for the user account
     */
    public void setPassword(String newPass) {
        password = newPass;
    }

    /**
     * setLogin - Set whether a user is logged in or not
     * 
     * @param val True if the user is logged in. False otherwise.
     */
    public void setLogin(boolean val) {
        logIn = val;
    }

    /**
     * getName - Get the name of a user
     * 
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * getEmail - Get the email of a user
     * 
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * getPhone - Get the phone number of a user
     * 
     * @return The phone number of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * getPassword - Get the password of a user
     * 
     * @return The password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * getLogin - Get whether a user is logged in or not
     * 
     * @return True if the user is logged in. False otherwise.
     */
    public boolean getLogin() {
        return logIn;
    }
}
