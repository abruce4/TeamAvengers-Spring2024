package Characters;

import Items.Item;
import Items.Spells;

import java.util.ArrayList;

public class MainCharacter extends Character {

    private int mana;
    private int defense;
    private ArrayList<Item> PlayerInventory;
    private ArrayList<Spells> PlayerSpells;
    private int playerCoins;

    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public MainCharacter(String characterType, String name, String description, int health, int attack, int dexterity, int speed, int mana, int defense) {
        super(characterType, name, description, health, attack, dexterity, speed);
        this.mana = mana;
        this.defense = defense;
        this.PlayerInventory = new ArrayList<>();
        this.PlayerSpells = new ArrayList<>();
        this.playerCoins = 0;
    }

    //Getters and Setters
    //Lincoln Bruce
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

    public int getPlayerCoins() {
        return playerCoins;
    }
    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }
}
