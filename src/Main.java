// NOTE: see CompileRun.txt
/**
 * This class to test Toffee Store core logic
 * 
 * @author 1.Adham Allam
 * @author 2.Abdullah Mohamed
 * @author 3.Omar Abdullah
 * @version 3.2
 * @since 5 May 2023
 */
public class Main {
    /**
     * main - This is the main method.
     * Creates a new StoreManager object and starts the store.
     * 
     * @param args an array of String arguments passed to the program
     * @throws Exception if an error occurs while starting the store
     */

     public static void main(String[] args) throws Exception{
        StoreManager store = new StoreManager();
        store.startStore();
    }
}
