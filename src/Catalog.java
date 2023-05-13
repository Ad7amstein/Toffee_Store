import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * The Catalog class represents a catalog of items, organized into categories.
 */
public class Catalog {
    /**
     * The map of categories to lists of items.
     */
    private Map<String, Vector<Item>> categories;

    /**
     * Creates a new empty catalog.
     */
    public Catalog() {
        categories = new HashMap<>();
    }
    
    /**
     * Creates a new catalog with the specified map of categories to lists of items.
     *
     * @param cat the map of categories to lists of items
     */
    public Catalog(Map<String, Vector<Item>> cat) {
        categories = cat;
    }

    /**
     * Displays all the items in the given category.
     *
     * @param category the name of the category
     */
    public void showItems(String category) {
        Vector<Item> items = categories.get(category);
        if (items == null) {
            System.out.println("Category not found.");
            return;
        }

        int i = 1;
        for (Item item : items) {
            System.out.println("item " + i + ":");
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Brand: " + item.getBrand());
            System.out.println("Price: " + item.getPrice());
            System.out.println("Discount: " + item.getDiscount());
            System.out.println("------------------------------------------");
            i++;
        }
    }

    /**
     * Displays all the categories in the catalog.
     */
    public void showCategories() {
        int cnt = 1;
        for (String category : categories.keySet()) {
            System.out.println(cnt + ". " + category);
            cnt++;
        }
    }

    /**
     * Returns a reference to the map of categories to lists of items.
     *
     * @return the catalog's map of categories to items
     */
    public Map<String, Vector<Item>> getCatalog() {
        return categories;
    }

    /**
     * Adds an item to the given category.
     *
     * @param item     the item to add
     * @param category the name of the category
     */
    public void addItem(Item item, String category) {
        Vector<Item> items = categories.get(category);
        if (items == null) {
            items = new Vector<>();
            categories.put(category, items);
        }
        items.add(item);
    }

    /**
     * Removes an item from the given category.
     *
     * @param item     the item to remove
     * @param category the name of the category
     */
    public void removeItem(Item item, String category) {
        Vector<Item> items = categories.get(category);
        if (items == null) {
            return;
        }
        items.remove(item);
    }

    /**
     * Updates an item in the given category.
     *
     * @param item     the updated item
     * @param category the name of the category
     */
    public void updateItem(Item item, String category) {
        Vector<Item> items = categories.get(category);
        if (items == null) {
            return;
        }
        for (Item oldItem : items) {
            if (oldItem.getName().equals(item.getName())) {
                oldItem.setDescription(item.getDescription());
                oldItem.setBrand(item.getBrand());
                oldItem.setPrice(item.getPrice());
                oldItem.setDiscount(item.getDiscount());
                return;
            }
        }
    }

    /**
     * Adds a new category to the catalog.
     *
     * @param category the name of the category to add
     */
    public void addCategory(String category) {
        categories.put(category, new Vector<>());
    }

    /**
     * Removes a category from the catalog.
     *
     * @param cat the name of the category to remove
     */
    public void removeCategory(String cat) {
        categories.remove(cat);
    }

    /**
     * Displays all the categories and their items in the catalog.
     */
    public void showCatalog() {
        for (Map.Entry<String, Vector<Item>> items : categories.entrySet()) {
            int i = 1;
            System.out.println(items.getKey() + ":");
            System.out.println("______________");
            for (Item item : items.getValue()) {
                System.out.println("item # " + i + ":");
                System.out.println("Name: " + item.getName());
                System.out.println("Description: " + item.getDescription());
                System.out.println("Brand: " + item.getBrand());
                System.out.println("Price: " + item.getPrice());
                System.out.println("Discount: " + item.getDiscount());
                System.out.println("-------------------------------------");
                i++;
            }
            System.out.println();
        }
    }
    
    /**
     * Returns the Item with the given ID, if it exists in the categories Map.
     * 
     * @param ID the ID of the Item to search for
     * @return the Item with the given ID, or null if it is not found
     */
    public Item getItem(int ID){
        for (Map.Entry<String, Vector<Item>> entry : categories.entrySet()) {
            Vector<Item> items = entry.getValue();
            for (Item i : items){
                if (i.getID() == ID){
                    return i;
                }
            }
        }
        return null;
    }
}
