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
    //Lincoln Bruce
    public Player(int health, int attack, int dexterity, int speed, int mana, int defense,Rooms currentRoom) {
        this.health = health;
        this.attack = attack;
        this.dexterity = dexterity;
        this.speed = speed;
        this.mana = mana;
        this.defense = defense;
        this.playerCoins = 0;
        this.currentRoom = currentRoom;
        this.PlayerInventory = new ArrayList<>();
        this.PlayerSpells = new ArrayList<>();
    }

    //Getters and Setters
    //Lincoln Bruce
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



}