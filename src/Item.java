/**Class: Item
 * @author Team Avengers / Huyen Pham
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * This class represents an item entity within a game or inventory system. Each item object encapsulates information
 *  * such as its unique identifier (itemID), name (itemName), description, statistics (stats), and value (worth).
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Item {
    private String itemID;
    private String itemName;
    private String description;
    private String stats;
    private int value; // Changed "cost" to "value" to represent the item's worth

    // Constructor
    public Item(String itemID, String itemName, String description, String stats, int value) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = description;
        this.stats = stats;
        this.value = value;
    }

    // Getters
    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getStats() {
        return stats;
    }

    public int getValue() {
        return value;
    }

    // Method to read items from a file and populate the list
    public static void readItems(String filePath, ArrayList<Item> listOfItems) {
        try {
            File myItems = new File(filePath);
            Scanner myReader = new Scanner(myItems);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] itemData = data.split(",");
                String itemID = itemData[0];
                String itemName = itemData[1];
                String description = itemData[2];
                String stats = itemData[3];
                int value = Integer.parseInt(itemData[4]);
                Item item = new Item(itemID, itemName, description, stats, value);
                listOfItems.add(item);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred with the items file.");
            e.printStackTrace(); // Print the stack trace of the exception for debugging purposes
        }
    }
}

