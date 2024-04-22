import java.io.Serializable;
import java.io.File;
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

    // Method to inspect an item in the player's inventory
    //Huyen Pham
    public void inspectItem(String itemName, Player mainCharacter) {
        for (Item item : mainCharacter.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println("Inspecting: " + itemName);
                System.out.println("Description: " + item.getItemDescription());
                System.out.println("Type: " + item.getItemType());
                System.out.println("Value: " + item.getItemValue());
                System.out.println("~~~~~~~~~~");
                return;
            }
        }
        System.out.println(itemName + " is not in your inventory.");
    }//end inspectItem

    //Method to pick up an item
    //Thuy Vy
    public void pickup(String itemName, Player mainCharacter, Rooms currentRoom) {
        for (Item item : currentRoom.getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                mainCharacter.getPlayerInventory().add(item);
                currentRoom.getRoomInventory().remove(item);
                System.out.println(itemName + " has been added to your inventory.");
                checkAura(item, mainCharacter);
                return;
            }
        }
        System.out.println("There is no " + itemName + " in this room.");
    }//end pickup

    //Method to check if the item picked up is an aura
    //Lincoln Bruce
    public void checkAura(Item item, Player mainCharacter) {
        for (Item aura : mainCharacter.getPlayerInventory()) {
            if (aura.getItemType().equalsIgnoreCase("aura")) {
                if (item instanceof Aura) {
                    Aura auraItem = (Aura) item;
                    System.out.println("This item boost your stats from the inventory");
                    mainCharacter.setMagic(mainCharacter.getMagic() + auraItem.getAddedMagic());
                    mainCharacter.setDexterity(mainCharacter.getDexterity() + auraItem.getAddedDex());
                    mainCharacter.setDefense(mainCharacter.getDefense() + auraItem.getAddedDefense());
                    System.out.println("Your magic has been boosted by " + auraItem.getAddedMagic());
                    System.out.println("Your dexterity has been boosted by " + auraItem.getAddedDex());
                    System.out.println("Your defense has been boosted by " + auraItem.getAddedDefense());
                }
            }
        }
    }

    // Method to drop an item
    // Thuy Vy
    public void drop(String itemName, Player mainCharacter, Rooms currentRoom) {
        for (Item item : mainCharacter.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println(itemName + " has been dropped from your inventory.");
                checkAuraDrop(item, mainCharacter);
                mainCharacter.getPlayerInventory().remove(item);
                currentRoom.getRoomInventory().add(item);
                return;
            }
        }
        System.out.println("You don't have " + itemName + " in your inventory.");
    }//end drop

    //Method to check if the item dropped is an aura
    //Lincoln Bruce
    public void checkAuraDrop(Item item, Player mainCharacter) {
        for (Item aura : mainCharacter.getPlayerInventory()) {
            if (aura.getItemType().equalsIgnoreCase("aura")) {
                if (item instanceof Aura) {
                    Aura auraItem = (Aura) item;
                    System.out.println("You lost your boosts");
                    mainCharacter.setMagic(mainCharacter.getMagic() - auraItem.getAddedMagic());
                    mainCharacter.setDexterity(mainCharacter.getDexterity() - auraItem.getAddedDex());
                    mainCharacter.setDefense(mainCharacter.getDefense() - auraItem.getAddedDefense());
                    System.out.println("Your magic has been reduced by " + auraItem.getAddedMagic());
                    System.out.println("Your dexterity has been reduced by " + auraItem.getAddedDex());
                    System.out.println("Your defense has been reduced by " + auraItem.getAddedDefense());
                }
            }
        }
    }

    //Method to display items in the room
    //Kenny Amador
    public void displayItems(Rooms rooms) {
        if (rooms.itemsIncluded.get(0).equalsIgnoreCase("n/a")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("There are no items in this room");
        } else if (rooms.getShop()) {
            for (Item item : rooms.getRoomInventory()) {
                System.out.println("[" + item + "]" + " cost: " + item.getItemValue());
            }
            System.out.println("~~~~~~~~~~");
        } else {
            System.out.print("Items in this room: ");
            for (Item item : rooms.getRoomInventory()) {
                System.out.println("[" + item + "]");
            }
        }
    }//end displayItems

    // Method to display monsters in the room
    // Lincoln Bruce
    public void displayMonsters(Rooms currentRoom) {
        if (!currentRoom.getRoomMonsters().isEmpty()) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Monsters in this room: ");
            for (Monster monster : currentRoom.getRoomMonsters()) {
                System.out.println("[" + monster.getName() + "]");
            }
            System.out.println("Enter 'fight' to fight the monster.");
        }
        else {
            System.out.println("~~~~~~~~~~");
            System.out.println("There are no monsters in this room.");
        }
    }

    // Method to display puzzles in room
    // Lincoln Bruce
    public void displayPuzzles(Rooms currentRoom) {
        if (!currentRoom.getRoomPuzzle().isEmpty()) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Puzzles in this room: ");
            for (Puzzle puzzle : currentRoom.getRoomPuzzle()) {
                System.out.println("[" + puzzle.getName() + "]");
            }
            System.out.println("Enter 'puzzle' to interact with the puzzle.");
        }
        else {
            System.out.println("~~~~~~~~~~");
            System.out.println("There are no puzzles in this room.");
        }
        System.out.println("~~~~~~~~~~");
    }

    // Method to use healing items
    // Ginette Wilson - Lincoln Bruce
    public void consume(String itemName, Player player) {
        for (Item item : player.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                if (item instanceof Consumable) {
                    Consumable consumable = (Consumable) item;
                    if (((Consumable) item).getHealedHealth() == 900) {
                        int healedHealth = player.getMaxHealth() / 2;
                        // Remove item from inventory
                        player.getPlayerInventory().remove(item);
                        System.out.println(itemName + " has been used.");
                        // Recover player's health
                        player.setHealth(player.getHealth() + healedHealth);
                        if (player.getHealth()>player.getMaxHealth()) {
                            player.setHealth(player.getMaxHealth());
                        }
                        System.out.println("You have been healed for " + healedHealth + " HP.");
                        return;
                    } else if (((Consumable) item).getHealedMana() == 900) {
                        int healedMana = player.getMaxMana() / 2;
                        // Remove item from inventory
                        player.getPlayerInventory().remove(item);
                        System.out.println(itemName + " has been used.");
                        // Recover player's mana
                        player.setMana(player.getMana() + healedMana);
                        if (player.getMana()>player.getMaxMana()) {
                            player.setMana(player.getMaxMana());
                        }
                        System.out.println("You have been healed for " + healedMana + " MP.");
                        return;
                    } else if (((Consumable) item).getHealedHealth() == 500) {
                        player.setHealth(player.getMaxHealth());
                        player.getPlayerInventory().remove(item);
                        System.out.println(itemName + " has been used.");
                        System.out.println("You have been healed to full health.");
                    } else if (((Consumable) item).getHealedMana() == 500) {
                        player.setMana(player.getMaxMana());
                        player.getPlayerInventory().remove(item);
                        System.out.println(itemName + " has been used.");
                        System.out.println("You have been healed to full mana.");
                    } else {
                        int healedHealth = consumable.getHealedHealth();
                        int healedMana = consumable.getHealedMana();
                        // Remove item from inventory
                        player.getPlayerInventory().remove(item);
                        System.out.println(itemName + " has been used.");
                        // Recover player's health and mana
                        player.setHealth(player.getHealth() + healedHealth);
                        player.setMana(player.getMana() + healedMana);
                        if (player.getHealth() > player.getMaxHealth()) {
                            player.setHealth(player.getMaxHealth());
                        }
                        if (player.getMana() > player.getMaxMana()) {
                            player.setMana(player.getMaxMana());
                        }
                        System.out.println("You have been healed for " + healedHealth + " HP and " + healedMana + " MP.");
                        return;
                    }

                } else {
                    System.out.println(itemName + " is not a healing item.");
                    return;
                }
            }
        }
        System.out.println(itemName + " not found in inventory.");
    }


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

    // Method to interact with a puzzle
    // Thuy Vy Pham
    public void examinePuzzle(Rooms currentRoom) {
        if (!currentRoom.getRoomPuzzle().isEmpty()) {
            System.out.println("~~~~~ Puzzle ~~~~~");
            System.out.println("You have encountered a puzzle in this room.");
            System.out.println("Puzzle: " + currentRoom.getRoomPuzzle().get(0).getName());
            System.out.println("Description: " + currentRoom.getRoomPuzzle().get(0).getDescription());
            System.out.println("~~~~~~~~~~");
            System.out.println("Enter 'solve' to solve the puzzle.");
            System.out.println("Enter 'exit' to exit the puzzle.");
        }
        else {
            System.out.println("There are no puzzles in this room.");
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

    // Method to use damaging items
    // Ginette Wilson
    public void throwItem(String itemName, Monster monster, Player player) {
        for (Item item : player.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                if (item instanceof Throwable) {
                    Throwable throwable = (Throwable) item;
                    int damageDealt = throwable.getDamageDealt();
                    int dexReduction = throwable.getSpeedReduction();
                    // Remove item from inventory
                    player.getPlayerInventory().remove(item);
                    System.out.println(itemName + " has been used.");
                    // Deal damage to the monster
                    monster.setHealth(monster.getHealth() - damageDealt);
                    monster.setDexterity(monster.getDexterity() - dexReduction);
                    System.out.println("You dealt " + damageDealt + " damage to the monster.");
                    System.out.println("You dealt " + dexReduction + " dexterity to the monster.");
                    return;
                } else {
                    System.out.println(itemName + " is not a damaging item.");
                    return;
                }
            }
        }
        System.out.println(itemName + " not found in inventory.");
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
