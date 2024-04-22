import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Player
 * @author Team Avengers / Lincoln Bruce
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 * This class represents a player entity within a game. Each player object encapsulates information
 */

public class Player implements Serializable {

    //Player attributes
    private int health;
    private int maxHealth;
    private int magic;
    private int dexterity;
    private int speed;
    private int maxMana;
    private int mana;
    private int defense;
    private int playerCoins;
    private Rooms currentRoom;
    private ArrayList<Item> playerInventory;
    private ArrayList<Spells> playerSpells;
    private Equipable equippedItem;
    private int baseHitRate;
    private int hitRate;
    private int AvoidRate;
    private boolean inBattle;
    private int playerLevel;
    private int playerExp;
    private int playerMaxExp;
    private String name;
    private String description;
    private int effects;
    private int manaCost;

    public Player(int health, int magic, int dexterity, int speed, int mana, int defense,Rooms currentRoom) {
        this.health = health;
        this.maxHealth = health;
        this.magic = magic;
        this.dexterity = dexterity;
        this.speed = speed;
        this.mana = mana;
        this.maxMana = mana;
        this.defense = defense;
        this.playerCoins = 0;
        this.currentRoom = currentRoom;
        this.playerInventory = new ArrayList<>();
        this.playerSpells = new ArrayList<>();
        this.equippedItem = null;
        this.baseHitRate = 80;
        this.hitRate = 0;
        this.AvoidRate = 4 * speed;
        this.inBattle = false;
        this.playerLevel = 1;
        this.playerExp = 0;
        this.playerMaxExp = 100;
    }

    //Getters and Setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
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
        return playerInventory;
    }

    public void setPlayerInventory(ArrayList<Item> playerInventory) {

        this.playerInventory = playerInventory;
    }

    public ArrayList<Spells> getPlayerSpells() {
        return playerSpells;
    }

    public void setPlayerSpells(ArrayList<Spells> playerSpells) {
        this.playerSpells = playerSpells;
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

    public boolean getInBattle() {
        return inBattle;
    }

    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    public int getBaseHitRate() {
        return baseHitRate;
    }

    public void setBaseHitRate(int baseHitRate) {
        this.baseHitRate = baseHitRate;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getPlayerExp() {
        return playerExp;
    }

    public void setPlayerExp(int playerExp) {
        this.playerExp = playerExp;
    }

    public int getPlayerMaxExp() {
        return playerMaxExp;
    }

    public void setPlayerMaxExp(int playerMaxExp) {
        this.playerMaxExp = playerMaxExp;
    }
    public String getName() {
        return name;
    }
    public int getEffects(){return effects;}
    public int getManaCost(){return manaCost;}

    public void setManaCost(int manaCost){this.manaCost = manaCost;}
    public void setEffects(int effects){this.effects = effects;}
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Method to display the player inventory
    // Thuy Vy
    public void inventory() {
        if (getPlayerInventory().isEmpty()) {
            System.out.println("You didn't pick up any items yet.");
        } else {
            System.out.println("Inventory:");
            for (Item item : getPlayerInventory()) {
                System.out.println("- " + item.getItemName());
            }
        }
    }//end inventory

    // Method to escape from the battle
    // Ginette Wilson
    public void escape(Rooms previousRoom, boolean inBattle) {
        if (inBattle) {
            System.out.println("You escaped from the battle!");
            setInBattle(false);
            // Set the current room to the previous room
            this.setCurrentRoom(previousRoom);
            // Set the previous room as visited since the player returns to it
            previousRoom.setHasBeenVisited(true);
        } else {
            System.out.println("You cannot escape because you are not in battle!");
        }
    }

    // Method to equip an item
    // Huyen Pham - Ginette Wilson
    public void equipItem(String itemName) {
        boolean itemFound = false;
        for (Item item : playerInventory) {
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
        this.maxHealth += item.getAddedHealth();
        this.magic += item.getAddedMagic(); // Assuming magic is a player stat
        this.dexterity += item.getAddedDexterity();
        this.speed += item.getAddedSpeed();
        this.defense += item.getAddedDefense();
    }

    // Revert stats from an unequipped item
    // Huyen Pham
    private void revertStats(Equipable item) {
        // Revert player stats when an item is unequipped
        this.maxHealth -= item.getAddedHealth();
        this.magic -= item.getAddedMagic();
        this.dexterity -= item.getAddedDexterity();
        this.speed -= item.getAddedSpeed();
        this.defense -= item.getAddedDefense();
    }

    // Method to level up the player
    // Lincoln Bruce
    public void levelUp(ArrayList<Spells> spells) {
        if (playerExp >= playerMaxExp && playerLevel < 5) {
            if (playerLevel == 1 && playerExp >= 100) {
                playerLevel = 2;
                playerExp = playerExp - playerMaxExp;
                playerMaxExp = 300;
                setMaxHealth(getMaxHealth() + 15);
                setHealth(getMaxHealth());
                setMaxMana(getMaxMana() + 8);
                setMana(getMaxMana());
                setMagic(getMagic() + 5);
                setSpeed(getSpeed() + 3);
                setDexterity(getDexterity() + 8);
                setDefense(getDefense() + 3);
                playerSpells.add(spells.get(1));
                System.out.println("You leveled up to level 2!");
            } else if (playerLevel == 2 && playerExp >= 300) {
                playerLevel = 3;
                playerExp = playerExp - playerMaxExp;
                playerMaxExp = 600;
                setMaxHealth(getMaxHealth() + 25);
                setHealth(getMaxHealth());
                setMaxMana(getMaxMana() + 8);
                setMana(getMaxMana());
                setMagic(getMagic() + 5);
                setSpeed(getSpeed() + 3);
                setDexterity(getDexterity() + 5);
                setDefense(getDefense() + 3);
                playerSpells.add(spells.get(2));
                System.out.println("You leveled up to level 3!");
            } else if (playerLevel == 3 && playerExp >= 600) {
                playerLevel = 4;
                playerExp = playerExp - playerMaxExp;
                playerMaxExp = 1000;
                setMaxHealth(getMaxHealth() + 15);
                setHealth(getMaxHealth());
                setMaxMana(getMaxMana() + 8);
                setMana(getMaxMana());
                setMagic(getMagic() + 5);
                setSpeed(getSpeed() + 3);
                setDexterity(getDexterity() + 5);
                setDefense(getDefense() + 3);
                playerSpells.add(spells.get(3));
                System.out.println("You leveled up to level 4!");
            } else if (playerLevel == 4 && playerExp >= 1000) {
                playerLevel = 5;
                setMaxHealth(getMaxHealth() + 30);
                setHealth(getMaxHealth());
                setMaxMana(getMaxMana() + 16);
                setMana(getMaxMana());
                setMagic(getMagic() + 5);
                setSpeed(getSpeed() + 4);
                setDexterity(getDexterity() + 5);
                setDefense(getDefense() + 3);
                playerSpells.add(spells.get(4));
                System.out.println("You leveled up to level 5!");
                System.out.println("You have reached the maximum level!");
            }
        }
    }

    //Method to solve the puzzle
    // Thuy Vy Pham - Lincoln Bruce
    public void solvePuzzle(String solution, ArrayList<Item> playerInventory, ArrayList<Item> listOfItems, Rooms currentRoom, ArrayList<Puzzle> roomPuzzle) {
        if (currentRoom.getRoomPuzzle() != null) {
            String itemToUse = "";
            for (Item item : playerInventory) {
                if (item.getItemName().equalsIgnoreCase(currentRoom.getRoomPuzzle().get(0).getItemSolution())) {
                    itemToUse = item.getItemName();
                }
            }
            while (!currentRoom.getRoomPuzzle().get(0).isSolved()) {
                Scanner scanner = new Scanner(System.in);
                solution = scanner.nextLine();
                if (solution.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting puzzle... You can return to solve it later.");
                    break;
                }
                else if (solution.equalsIgnoreCase(currentRoom.getRoomPuzzle().get(0).getSolution())) {
                    System.out.println(currentRoom.getRoomPuzzle().get(0).getSolvedMessage());
                    rewardPlayer(currentRoom, listOfItems, roomPuzzle);
                    currentRoom.getRoomPuzzle().get(0).setSolved(true);
                    currentRoom.setRoomPuzzle(null);
                    break;
                } else if (solution.equalsIgnoreCase(itemToUse)) {
                    System.out.println(currentRoom.getRoomPuzzle().get(0).getSolvedMessage());
                    rewardPlayer(currentRoom, listOfItems, roomPuzzle);
                    currentRoom.getRoomPuzzle().get(0).setSolved(true);
                    currentRoom.setRoomPuzzle(null);
                    break;
                } else if (solution.equalsIgnoreCase("eot") || solution.equalsIgnoreCase("Eye of Truth")) {
                    System.out.println(currentRoom.getRoomPuzzle().get(0).getHint());
                } else if (currentRoom.getRoomPuzzle().get(0).getAttemptsLeft() == 1) {
                    System.out.println(currentRoom.getRoomPuzzle().get(0).getFailMessage());
                    malusPlayer(currentRoom, roomPuzzle);
                    currentRoom.getRoomPuzzle().get(0).setSolved(true);
                    break;
                } else {
                    currentRoom.getRoomPuzzle().get(0).setAttemptsLeft(currentRoom.getRoomPuzzle().get(0).getAttemptsLeft() - 1);
                    System.out.println("Incorrect. Try again! You have: " + currentRoom.getRoomPuzzle().get(0).getAttemptsLeft() + " attempts left.");
                }
            }
        } else {
            System.out.println("There is no puzzle in this room.");
        }
    }

    //Method to reward the player after a puzzle
    // Thuy Vy Pham - Lincoln Bruce
    public void rewardPlayer(Rooms currentRoom, ArrayList<Item> listOfItems, ArrayList<Puzzle> roomPuzzle) {
        if (currentRoom.getRoomPuzzle().get(0).getItemReward() != null) {
            System.out.println("You have received a " + currentRoom.getRoomPuzzle().get(0).getItemReward() + "!");
            for (Item item : listOfItems) {
                if (item.getItemName().equalsIgnoreCase(currentRoom.getRoomPuzzle().get(0).getItemReward())) {
                    playerInventory.add(item);
                }
            }
        }
        if (currentRoom.getRoomPuzzle().get(0).getCoinsReward() != 0) {
            System.out.println("You have received " + currentRoom.getRoomPuzzle().get(0).getCoinsReward() + " coins!");
            setPlayerCoins(getPlayerCoins() + currentRoom.getRoomPuzzle().get(0).getCoinsReward());
        }
        if (currentRoom.getRoomPuzzle().get(0).getMagicReward() != 0) {
            System.out.println("You have received " + currentRoom.getRoomPuzzle().get(0).getMagicReward() + " magic points!");
            setMagic(getMagic() + currentRoom.getRoomPuzzle().get(0).getMagicReward());
        }
    }

    //Method to malus the player after a puzzle
    // Thuy Vy Pham - Lincoln Bruce
    public void malusPlayer(Rooms currentRoom, ArrayList<Puzzle> roomPuzzle) {
        if (currentRoom.getRoomPuzzle().get(0).getDamageTaken() != 0) {
            System.out.println("You have taken " + currentRoom.getRoomPuzzle().get(0).getDamageTaken() + " damage!");
            setHealth(getHealth() - currentRoom.getRoomPuzzle().get(0).getDamageTaken());
        }
    }

    // Method to check if the player have the ring of regeneration
    // Lincoln Bruce
    public void ringOfRegeneration (ArrayList<Item> playerInventory) {
        boolean hasRing = false;
        for (Item item : playerInventory) {
            if (item.getItemName().equalsIgnoreCase("Ring of Regeneration")) {
                hasRing = true;
            }
        }
        if (hasRing) {
            System.out.println("You have the Ring of Regeneration. You will regenerate 3 health points every turn.");
            setHealth(getHealth() + 3);
        }
    }

}
