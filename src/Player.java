import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Player
 * @author Team Avengers / Lincoln Bruce
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 * This class represents a player entity within a game. Each player object encapsulates information
 */

public class Player {

    //Player attributes
    //Lincoln Bruce
    private int health;
    private int magic;
    private int dexterity;
    private int speed;
    private int mana;
    private int defense;
    private int playerCoins;
    private Rooms currentRoom;
    private ArrayList<Item> PlayerInventory;
    private ArrayList<Spells> PlayerSpells;
    private Equipable equippedItem;
    private int hitRate;
    private int AvoidRate;


    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public Player(int health, int magic, int dexterity, int speed, int mana, int defense, Rooms currentRoom) {
        this.health = health;
        this.magic = magic;
        this.dexterity = dexterity;
        this.speed = speed;
        this.mana = mana;
        this.defense = defense;
        this.playerCoins = 0;
        this.currentRoom = currentRoom;
        this.PlayerInventory = new ArrayList<>();
        this.PlayerSpells = new ArrayList<>();
        this.equippedItem = null;
        this.hitRate = 80;
        this.AvoidRate = 4 * speed;
    }

    //Getters and Setters
    //Lincoln Bruce
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
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

    public Equipable getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Equipable equippedItem) {
        this.equippedItem = equippedItem;
    }

    public int getHitRate() {
        return hitRate;
    }

    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    public int getAvoidRate() {
        return AvoidRate;
    }

    public void setAvoidRate(int AvoidRate) {
        this.AvoidRate = AvoidRate;
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


    // Method to examine a monster
    //Ginette Wilson
    public void examine(Monster monster) {
        System.out.println("Name: " + monster.getName());
        System.out.println("Description: " + monster.getDescription());
        System.out.println("Health Points: " + monster.getHealth());
        System.out.println("Attack Damage: " + monster.getAttack());
        System.out.println("Dexterity: " + monster.getDexterity());
        System.out.println("Speed: " + monster.getSpeed());
    }

    // method to escape from the battle
    // Ginette Wilson
    public void escape(Rooms previousRoom, boolean inBattle) {
        if (inBattle) {
            System.out.println("You escaped from the battle!");
            // Set the current room to the previous room
            this.setCurrentRoom(previousRoom);
            // Set the previous room as visited since the player returns to it
            previousRoom.setHasBeenVisited(true);
        } else {
            System.out.println("You cannot escape because you are not in battle!");
        }
    }
    // Huyen Pham &  Ginette Wilson
    public void equipItem(String itemName) {
        boolean itemFound = false;
        for (Item item : PlayerInventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                itemFound = true;
                if (item instanceof Equipable) {
                    if (equippedItem != null) {
                        unequipItem(); // Unequip current item before equipping a new one
                    }
                    equippedItem = (Equipable) item;
                    applyStats(equippedItem);
                    System.out.println("Equipped: " + itemName);
                    return;
                } else {
                    System.out.println(itemName + " is not an equipable item.");
                }
            }
        }
        if (!itemFound) {
            System.out.println(itemName + " not found in inventory.");
        }
    }


    // Method to unequip the current item and revert stat changes
    // Huyen Pham
    public void unequipItem() {
        if (equippedItem != null) {
            revertStats(equippedItem);
            System.out.println("Unequipped: " + equippedItem.getItemName());
            equippedItem = null;
        } else {
            System.out.println("No item is currently equipped.");
        }
    }
    // Apply stats from an equipable item
// Huyen Pham
    private void applyStats(Equipable item) {
        // Update player stats based on the equipable item's properties
        this.health += item.getAddedHealth();
        this.magic += item.getAddedMagic(); // Assuming magic is a player stat
        this.dexterity += item.getAddedDexterity();
        this.speed += item.getAddedSpeed();
        this.defense += item.getAddedDefense();
    }
    // Revert stats from an unequipped item
//Huyen Pham
    private void revertStats(Equipable item) {
        // Revert player stats when an item is unequipped
        this.health -= item.getAddedHealth();
        this.magic -= item.getAddedMagic();
        this.dexterity -= item.getAddedDexterity();
        this.speed -= item.getAddedSpeed();
        this.defense -= item.getAddedDefense();
    }
}