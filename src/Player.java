import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Player
{
    private Rooms currentRoom;
    private Map map;
    private ArrayList<Item> inventory;

    //Thuy Vy Pham
    public Player() throws FileNotFoundException
    {
        map = new Map();
        currentRoom = map.getRoom(1);
        inventory = new ArrayList<>();
    }
    public Rooms getCurrentRoom()
    {
        return currentRoom;
    }

    public void inventory()
    {
        if (inventory.isEmpty())
        {
            System.out.println("You didn't pick up any items yet.");
        } else
        {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getItemName());
            }
        }
    }


    //Method to pick up an item
    public void pickup(String itemName)
    {
        for (Item item : currentRoom.getItems())
        {
            if (item.getItemName().equalsIgnoreCase(itemName))
            {
                inventory.add(item);
                currentRoom.removeItem(item);
                System.out.println(itemName + " has been picked up from the room and successfully added to the player inventory.");
                return;
            }
        }
        System.out.println("There is no " + itemName + " in this room.");
    }

    //Method to drop an item
    public void drop(String itemName)
    {
        for (Item item : inventory)
        {
            if (item.getItemName().equalsIgnoreCase(itemName))
            {
                inventory.remove(item);
                currentRoom.addItem(item);
                System.out.println(itemName + " has been dropped successfully from the player inventory and placed in " + currentRoom.getDescription());
                return;
            }
        }
        System.out.println("You don't have " + itemName + " in your inventory.");
    }

    // Method to inspect an item in the current room
    public void inspectItem(String itemName) {
        ArrayList<Item> itemsInRoom = currentRoom.getItems();
        for (Item item : itemsInRoom)
        {
            if (item.getItemName().equalsIgnoreCase(itemName))
            {
                System.out.println("Inspecting: " + itemName);
                System.out.println("Description: " + item.getItemDescription());
                System.out.println("Type: " + item.getItemType());
                System.out.println("Value: " + item.getItemValue());
                return;
            }
        }
        System.out.println("Item '" + itemName + "' not found in this room.");
    }
}
