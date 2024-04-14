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
    private int itemID;
    private String itemType;
    private String itemName;
    private String itemDescription;
    private int itemValue;// Changed "cost" to "value" to represent the item's worth
    private int itemRoomID;

    // Constructor
    public Item(int itemID, String itemType, String itemName, String itemDescription, int itemValue, int itemRoomID) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemValue = itemValue;
        this.itemRoomID = itemRoomID;
    }

    // Getters and Setters
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
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

    public int getItemRoomID() {
        return itemRoomID;
    }

    public void setItemRoomID(int itemRoomID) {
        this.itemRoomID = itemRoomID;
    }
}

