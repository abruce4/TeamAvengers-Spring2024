/**Class: Item
 * @author Team Avengers / Huyen Pham
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * This class represents an item entity within a game or inventory system. Each item object encapsulates information
 *  * such as its unique identifier (itemID), name (itemName), description, statistics (stats), and value (worth).
 */

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Item implements Serializable {
    private String itemID;
    private String itemType;
    private String itemName;
    private String itemDescription;
    private int itemValue;// Changed "cost" to "value" to represent the item's worth

    // Constructor
    public Item(String itemID, String itemType, String itemName, String itemDescription, int itemValue) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemValue = itemValue;
    }

    // Getters and Setters
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    //toString method
    @Override
    public String toString() {
        return itemName;
    }

    // Read items from file
    // Huyen Pham
    public static void readItems(String filePath, ArrayList<Item> listOfItems) {
        try {
            File myItems = new File(filePath);
            Scanner myReader = new Scanner(myItems);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] itemData = data.split("-");
                String itemID = itemData[0].trim();
                String itemType = itemData[1].trim();
                String itemName = itemData[2].trim();
                String description = itemData[3].trim();
                int value = Integer.parseInt(itemData[4].trim());

                //Lincoln Bruce
                if (itemType.equalsIgnoreCase("decoration")) {
                    Item item = new Item(itemID, itemType, itemName, description, value);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("equipable")) {
                    int addedHealth = Integer.parseInt(itemData[5].trim());
                    int addedMagic = Integer.parseInt(itemData[6].trim());
                    int addedDexterity = Integer.parseInt(itemData[7].trim());
                    int addedSpeed = Integer.parseInt(itemData[8].trim());
                    int addedDefense = Integer.parseInt(itemData[9].trim());
                    String itemUtility = itemData[10].trim();
                    Equipable item = new Equipable(itemID, itemType, itemName, description, value, addedHealth, addedMagic, addedDexterity, addedSpeed, addedDefense, itemUtility);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("consumable")) {
                    int healedHealth = Integer.parseInt(itemData[5].trim());
                    int healedMana = Integer.parseInt(itemData[6].trim());
                    Consumable item = new Consumable(itemID, itemType, itemName, description, value, healedHealth, healedMana);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("throwable")) {
                    int damageDealt = Integer.parseInt(itemData[5].trim());
                    int dexReduction = Integer.parseInt(itemData[6].trim());
                    Throwable item = new Throwable(itemID, itemType, itemName, description, value, damageDealt, dexReduction);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("Aura")) {
                    int addedMagic = Integer.parseInt(itemData[5].trim());
                    int addedDex = Integer.parseInt(itemData[6].trim());
                    int addedDefense = Integer.parseInt(itemData[7].trim());
                    Aura item = new Aura(itemID, itemType, itemName, description, value, addedMagic, addedDex, addedDefense);
                    listOfItems.add(item);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred with the items file.");
        }
    }
}

