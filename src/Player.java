import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    //Player attributes
    private String name;
    private String description;
    private int health;
    private int attack;
    private int dexterity;
    private int speed;
    private int mana;
    private int defense;
    private int playerCoins;
    private Rooms currentRoom;
    private ArrayList<Item> PlayerInventory;
    private ArrayList<Spells> PlayerSpells;

    //Constructor and Initialization of attributes
    public Player(String name, String description, int health, int attack, int dexterity, int speed, int mana, int defense) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.dexterity = dexterity;
        this.speed = speed;
        this.mana = mana;
        this.defense = defense;
        this.playerCoins = 0;
        this.PlayerInventory = new ArrayList<>();
        this.PlayerSpells = new ArrayList<>();
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    public Rooms getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Rooms currentRoom) {
        this.currentRoom = currentRoom;
    }

    public ArrayList<Item> getPlayerInventory() {
        return PlayerInventory;
    }

    public void setPlayerInventory(ArrayList<Item> playerInventory) {
        PlayerInventory = playerInventory;
    }

    public ArrayList<Spells> getPlayerSpells() {
        return PlayerSpells;
    }

    public void setPlayerSpells(ArrayList<Spells> playerSpells) {
        PlayerSpells = playerSpells;
    }

    //ToString method
    @Override
    public String toString() {
        return name;
    }

    //Method to read the player file
    public static void readPlayers(String filePath, ArrayList<Player> listOfPlayers) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] playerData = data.split("-");
                String name = playerData[0];
                String description = playerData[1];
                int health = Integer.parseInt(playerData[2]);
                int attack = Integer.parseInt(playerData[3]);
                int dexterity = Integer.parseInt(playerData[4]);
                int speed = Integer.parseInt(playerData[5]);
                int mana = Integer.parseInt(playerData[6]);
                int defense = Integer.parseInt(playerData[7]);
                listOfPlayers.add(new Player(name, description, health, attack, dexterity, speed, mana, defense));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the player file.");
        }
    }

    //Method to display the player inventory
    //Thuy Vy
    public void inventory() {
        if (getPlayerInventory().isEmpty()) {
            System.out.println("You didn't pick up any items yet.");
        } else {
            System.out.println("Inventory:");
            for (Item item : getPlayerInventory()) {
                System.out.println("- " + item.getItemName());
            }
        }
    }


    //Method to pick up an item
    //Thuy Vy
    public void pickup(String itemName) {
        for (Item item : currentRoom.getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                getPlayerInventory().add(item);
                currentRoom.removeItem(item);
                System.out.println(itemName + " has been picked up from the room and successfully added to the player inventory.");
                return;
            }
        }
        System.out.println("There is no " + itemName + " in this room.");
    }

    //Method to drop an item
    //Thuy Vy
    public void drop(String itemName) {
        for (Item item : getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                getPlayerInventory().remove(item);
                currentRoom.addItem(item);
                System.out.println(itemName + " has been dropped successfully from the player inventory and placed in " + currentRoom.getDescription());
                return;
            }
        }
        System.out.println("You don't have " + itemName + " in your inventory.");
    }

    // Method to inspect an item in the current room
    //Huyen Pham
    public void inspectItem(String itemName) {
        for (Item item : getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println("Inspecting: " + itemName);
                System.out.println("Description: " + item.getItemDescription());
                System.out.println("Type: " + item.getItemType());
                System.out.println("Value: " + item.getItemValue());
                return;
            }
        }
        System.out.println(itemName + " not found in your inventory.");
    }
}