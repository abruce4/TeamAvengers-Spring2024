import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**Class: Game
 * @author Team Avengers / Ginette Wilson
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 10, 2024
 * This class handles the game logic and user input
 */

public class Game implements Serializable {


    private int currentRoom;
    private transient Scanner scan;
    private transient Scanner scanner;
    private final Player mainCharacter;

    // File paths for game elements
    //Ginette Wilson
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String MONSTERS_FILE_PATH = "src/Monsters.txt";
    private static final String ROOMS_FILE_PATH = "src/Rooms.txt";
    private static final String SPELLS_FILE_PATH = "src/spells.txt";


    // ArrayList to store game elements
    //Ginette Wilson
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Monster> listOfMonsters = new ArrayList<>();
    private static final ArrayList<Rooms> listOfRooms = new ArrayList<>();
    private static final ArrayList<Spells> listOfSpells = new ArrayList<>();


    //Method to initialize the game
    //Ginette Wilson
    public Game() {
        loadGameElements();// Initialize the game map
        System.out.println("Welcome to the Arcane Odyssey");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        loadGameStory();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        scanner = new Scanner(System.in); // Scanner for user input
        currentRoom = 0;
        mainCharacter = new Player(25, 10, 10, 7, 20, 5, listOfRooms.get(0));
        loadInventory(listOfItems, mainCharacter);
    }

    // Method to initialize the player inventory
    // Lincoln Bruce
    public void loadInventory(ArrayList<Item> listOfItems, Player mainCharacter) {
        for (Item item : listOfItems) {
            if (item.getItemName().equalsIgnoreCase("Spellbook")) {
                mainCharacter.getPlayerInventory().add(item);
            }
        }
    }

    //Method to load game elements
    //Ginette Wilson
    private static void loadGameElements() {
        Rooms.readRooms(ROOMS_FILE_PATH, listOfRooms);
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Monster.readMonsters(MONSTERS_FILE_PATH, listOfMonsters);
        addItemsToRoom(listOfItems, listOfRooms);
        addMonstersToRoom(listOfMonsters, listOfRooms);
        addPuzzlesToRoom(listOfPuzzles, listOfRooms);
        Spells.readSpells(SPELLS_FILE_PATH, listOfSpells);
    }


    //Method to add puzzles to the room
    //Lincoln Bruce
    public static void addPuzzlesToRoom(ArrayList<Puzzle> listOfPuzzles, ArrayList<Rooms> listOfRooms) {
        for (Rooms room : listOfRooms) {
            for (Puzzle puzzle : listOfPuzzles) {
                if (room.getPuzzleIncluded().contains(puzzle.getPuzzleID())) {
                    room.getRoomPuzzle().add(puzzle);
                }
            }
        }
    }

    //Method to add items to the room
    //Lincoln Bruce
    public static void addItemsToRoom(ArrayList<Item> listOfItems, ArrayList<Rooms> listOfRooms) {
        for (Rooms room : listOfRooms) {
            for (Item item : listOfItems) {
                if (room.getItemsIncluded().contains(item.getItemID())) {
                    room.getRoomInventory().add(item);
                }
            }
        }
    }

    //Method to add monsters to the room
    //Lincoln Bruce
    public static void addMonstersToRoom(ArrayList<Monster> listOfMonsters, ArrayList<Rooms> listOfRooms) {
        for (Rooms room : listOfRooms) {
            for (Monster monster : listOfMonsters) {
                if (room.getMonstersIncluded().contains(monster.getMonsterID())) {
                    room.getRoomMonsters().add(monster);
                }
            }
        }
    }

    //Method to run the game
    //Kenny Amador
    public void RunGame() {
        System.out.println("Press q at any time if you wish to quit or y to continue");
        scan = new Scanner(System.in);
        Rooms currentRooms = listOfRooms.get(currentRoom);
        currentRooms.setHasBeenVisited(true);
        System.out.println(currentRooms.getRoomName() + ": " + currentRooms.getDescription());
        String command = scan.next();
        while (!command.equalsIgnoreCase("save")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Please enter a navigation command north,east,south,west to move around");
            scan = new Scanner(System.in);
            command = scan.nextLine();
            currentRoom = checkCommand(command, currentRooms, mainCharacter);
            if (currentRoom == -1) {
                System.out.println("You cannot go in this direction");
                continue;
            }
            currentRooms = listOfRooms.get(currentRoom);
            checkRoom(currentRooms);
            System.out.println(currentRooms.getRoomName() + ": " + currentRooms.getDescription());
            currentRooms.setHasBeenVisited(true);
        }
    }

    public void checkRoom(Rooms rooms) {
        if (rooms.getHasBeenVisited()) {
            System.out.println("You have been here before");
        }
        if (rooms.getShop()) {
            shop(rooms);
        }
    }

    public void shop(Rooms rooms) {
        System.out.println("Would you like to shop or sell?");
        scan = new Scanner(System.in);
        String command = scan.nextLine();
        if (command.equalsIgnoreCase("shop")) {
            while (!command.equalsIgnoreCase("quit")) {
                System.out.println("Which items would you like to buy?");
                displayItems(rooms);
                command = scan.nextLine();
                buyItem(command, mainCharacter, rooms);
            }
        }
        if (command.equalsIgnoreCase("sell")) {
            System.out.println("Which items would you like to sell?");
            command = scan.nextLine();
            sell(command, mainCharacter, rooms);

        }

    }

    public void buyItem(String itemName, Player mainCharacter, Rooms currentRoom) {
        for (Item item : currentRoom.getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName) & mainCharacter.getPlayerCoins() >= item.getItemValue()) {
                mainCharacter.getPlayerInventory().add(item);
                currentRoom.getRoomInventory().remove(item);
                System.out.println(itemName + " has been added to your inventory.");
                mainCharacter.setPlayerCoins(mainCharacter.getPlayerCoins() - item.getItemValue());
                return;
            }
        }
        if (itemName.equalsIgnoreCase("quit")) {
            System.out.println("~~~~~~~~~~~~~");
            return;
        }
        System.out.println("You do not have enough coins");
    }


    //Method to check the command
    //Kenny Amador
    public int checkCommand(String command, Rooms rooms, Player mainCharacter) {
        ArrayList<Integer> connects = rooms.roomExits;
        if (command.equalsIgnoreCase("north")) {
            return connects.get(0) - 1;
        }
        if (command.equalsIgnoreCase("east")) {
            return connects.get(1) - 1;
        }
        if (command.equalsIgnoreCase("south")) {
            return connects.get(2) - 1;
        }
        if (command.equalsIgnoreCase("west")) {
            return connects.get(3) - 1;
        }
        if (command.equalsIgnoreCase("look")) {
            displayItems(rooms);
            displayMonsters(rooms);
            displayPuzzles(rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("solve")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("What is your answer?");
            command = scan.nextLine();
            mainCharacter.solvePuzzle(command, mainCharacter.getPlayerInventory(), listOfItems, rooms, rooms.getRoomPuzzle());
            return currentRoom;
        }
        if (command.equalsIgnoreCase("inspect")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to inspect?");
            command = scan.nextLine();
            inspectItem(command, mainCharacter);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("pickup")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to pick up?");
            command = scan.nextLine();
            pickup(command, mainCharacter, rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("drop")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to drop?");
            command = scan.nextLine();
            drop(command, mainCharacter, rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("consume")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to consume?");
            command = scan.nextLine();
            consume(command, mainCharacter);
            return currentRoom;

        }
        if (command.equalsIgnoreCase("equip")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to equip?");
            command = scan.nextLine();  // continues to use command variable
            mainCharacter.equipItem(command);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("unequip")) {
            mainCharacter.unequipItem();
            return currentRoom;
        }
        if (command.equalsIgnoreCase("inventory")) {
            mainCharacter.inventory();
            return currentRoom;
        }
        if (command.equalsIgnoreCase("stats")) {
            displayStats();
            return currentRoom;
        }
        if (command.equalsIgnoreCase("save")) {
            return currentRoom;
        }
        if (command.equalsIgnoreCase("examine")) {
            examine(rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("teleport")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Where would you like to teleport to?");
            String roomName = scanner.nextLine();
            teleport(roomName);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("items")) {
            mainCharacter.inventory();
            return currentRoom;
        }
        if (command.equalsIgnoreCase("grimoire")) {
            grimoire();
            return currentRoom;
        }
        if (command.equalsIgnoreCase("help")) {
            helpCommand();
            return currentRoom;
        }
        if (command.equalsIgnoreCase("fight")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which monster would you like to fight?");
            command = scan.nextLine();
            attackMonster(command, mainCharacter, rooms);
            return currentRoom;

        }
        if (command.equalsIgnoreCase("puzzle")) {
            interactPuzzle(rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("Eye of truth")) {
            eyeOfTruth(rooms);
            return currentRoom;
        }

        return -1;
    }

    //Method to display grimoire
    //Lincoln Bruce
    public void grimoire() {
        System.out.println("~~~~~~~~~~");
        System.out.println("Grimoire");
        for (Spells spell : listOfSpells) {
            System.out.println("Name: " + spell.getName());
            System.out.println("Description: " + spell.getDescription());
            System.out.println("Magic Cost: " + spell.getManaCost());
            System.out.println("Level Needed: " + spell.getLevelNeeded());
            System.out.println("~~~~~~~~~~");
        }
    }


    //Method to print monster stats
    //Ginette Wilson
    public void examine(Rooms currentRoom) {
        for (Monster monster : currentRoom.getRoomMonsters()) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Name: " + monster.getName());
            System.out.println("Description: " + monster.getDescription());
            System.out.println("Health Points: " + monster.getHealth());
            System.out.println("Attack Damage: " + monster.getAttack());
            System.out.println("Dexterity: " + monster.getDexterity());
            System.out.println("Speed: " + monster.getSpeed());
            System.out.println("~~~~~~~~~~");
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
            System.out.println("~~~~~~~~~~");
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

    // Method to interact with puzzle
    // Thuy Vy Pham
    public void interactPuzzle(Rooms currentRoom) {
        System.out.println("~~~~~ Puzzle ~~~~~");
        System.out.println("You have encountered a puzzle in this room.");
        System.out.println("~~~~~~~~~~");
        System.out.println("Puzzle: " + currentRoom.getRoomPuzzle().get(0).getName());
        System.out.println("Description: " + currentRoom.getRoomPuzzle().get(0).getDescription());
        System.out.println("~~~~~~~~~~");
        System.out.println("Enter 'solve' to solve the puzzle.");
    }



    // Method to inspect an item in the current room
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

    public void sell(String itemName, Player mainCharacter, Rooms currentRoom) {
        for (Item item : mainCharacter.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                mainCharacter.getPlayerInventory().remove(item);
                currentRoom.getRoomInventory().add(item);
                System.out.println(itemName + " has been dropped from your inventory.");
                mainCharacter.setPlayerCoins(mainCharacter.getPlayerCoins() + item.getItemValue());
                System.out.println(mainCharacter.getPlayerCoins());
                return;
            }
        }
        if (itemName.equalsIgnoreCase("quit")) {
            System.out.println("~~~~~~~~~~");
        }
        System.out.println("You have nothing to sell");
    }

    //Method to drop an item
    //Thuy Vy
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
    }

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

    //method to be teleported to a room
    //Ginette Wilson
    public void teleport(String roomName) {
        boolean roomFound = false;
        for (Rooms room : listOfRooms) {
            if (room.getRoomName().equalsIgnoreCase(roomName) && room.getHasBeenVisited()) {
                roomFound = true;
                currentRoom = room.getRoomID() - 1;
                System.out.println("Teleported to: " + roomName);
                return;
            }
        }
        if (!roomFound) {
            System.out.println("Room " + roomName + " not found or hasn't been visited yet.");
        }
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

    // method to use damaging items
    //Ginette Wilson
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

    // Method to display player stats
    //Kenny Amador
    public void displayStats() {
        System.out.println("~~~~~~~~~~");
        System.out.println("Health: " + mainCharacter.getHealth() + "/" + mainCharacter.getMaxHealth());
        System.out.println("Magic: " + mainCharacter.getMagic());
        System.out.println("Dexterity: " + mainCharacter.getDexterity());
        System.out.println("Speed: " + mainCharacter.getSpeed());
        System.out.println("Mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
        System.out.println("Defense: " + mainCharacter.getDefense());
        System.out.println("Coins: " + mainCharacter.getPlayerCoins());
        System.out.println("Experience: " + mainCharacter.getPlayerExp() + "/" + mainCharacter.getPlayerMaxExp());
        System.out.println("~~~~~~~~~~");
    }

    // Method for player to attack a monster
    //Lincoln Bruce
    public void attackMonster(String monsterName, Player mainCharacter, Rooms currentRoom) {
        for (Monster monster : currentRoom.getRoomMonsters()) {
            if (monster.getName().equalsIgnoreCase(monsterName)) {
                mainCharacter.setInBattle(true);
                System.out.println("You are now in battle with the " + monsterName);
                while (mainCharacter.getInBattle()) {
                    if (mainCharacter.getHealth() <= 0) {
                        System.out.println("You have been defeated by the " + monsterName);
                        mainCharacter.setInBattle(false);
                        gameOver();
                        break;
                    } else if (monster.getHealth() <= 0) {
                        if (monster.getName().equalsIgnoreCase("Dragon")) {
                            gameWin();
                        }
                        else {
                            System.out.println("You have defeated the " + monsterName);
                            System.out.println("~~~~~~~~~~");
                            mainCharacter.setPlayerExp(mainCharacter.getPlayerExp() + monster.getExpDrop());
                            System.out.println("You have gained " + monster.getExpDrop() + " experience points.");
                            System.out.println("~~~~~~~~~~");
                            mainCharacter.setPlayerCoins(mainCharacter.getPlayerCoins() + monster.getGoldDrop());
                            System.out.println("You have gained " + monster.getGoldDrop() + " coins.");
                            System.out.println("~~~~~~~~~~");
                            mainCharacter.levelUp(listOfSpells);
                            mainCharacter.setInBattle(false);
                            break;
                        }
                    } else {
                        System.out.println("Choose an action: attack, consume, spells, throw or escape");
                        String action = scanner.nextLine();
                        if (action.equalsIgnoreCase("attack")) {
                            if (mainCharacter.getSpeed() > monster.getSpeed()) {
                                dealDamage(monster);
                                if (monster.getHealth() > 0) {
                                    dealDamage2(monster);
                                }
                            }
                            else {
                                dealDamage2(monster);
                                if (mainCharacter.getHealth() > 0) {
                                    dealDamage(monster);
                                }
                            }
                        }
                        else if (action.equalsIgnoreCase("examine")) {
                            examine(currentRoom);
                        }
                        else if (action.equalsIgnoreCase("consume")) {
                            System.out.println("Which item would you like to consume?");
                            String itemToConsume = scanner.nextLine();
                            consume(itemToConsume, mainCharacter);
                        }
                        else if (action.equalsIgnoreCase("inventory")) {
                            mainCharacter.inventory();
                        }
                        else if (action.equalsIgnoreCase("escape")) {
                            mainCharacter.escape(currentRoom, mainCharacter.getInBattle());
                        } else if (action.equalsIgnoreCase("throw")) {
                            System.out.println("Which item would you like to throw?");
                            String itemToThrow = scanner.nextLine();
                            throwItem(itemToThrow, monster, mainCharacter);
                        } else if (action.equalsIgnoreCase("spells")) {
                            System.out.println("Which spell will you like to case");
                            String spells = scanner.nextLine();
                            activateSpells(spells, monster, mainCharacter);
                        } else {
                            System.out.println("You can't do that in battle. Please try again.");
                        }
                    }
                }
            }
        }
    }

    // Method for the player to deal damage to a monster
    public void dealDamage(Monster monster) {
        mainCharacter.setHitRate((4 * mainCharacter.getDexterity() + mainCharacter.getBaseHitRate()) - monster.getAvoidRate());
        // initialize a random number between 1 and 100
        int randomNum = new Random().nextInt(100);
        if (randomNum < mainCharacter.getHitRate()) {
            monster.setHealth(monster.getHealth() - mainCharacter.getMagic());
            System.out.println("You dealt " + mainCharacter.getMagic() + " damage to the monster.");
            if (monster.getHealth() < 0) {
                monster.setHealth(0);
                System.out.println(monster.getName() + " HP: " + monster.getHealth() + "/" + monster.getMaxHealth());
                mainCharacter.ringOfRegeneration(mainCharacter.getPlayerInventory());
                System.out.println("~~~~~~~~~~");
            }
            else {
                System.out.println(monster.getName() + " HP: " + monster.getHealth() + "/" + monster.getMaxHealth());
                mainCharacter.ringOfRegeneration(mainCharacter.getPlayerInventory());
                System.out.println("~~~~~~~~~~");
            }
        } else {
            System.out.println("You missed the monster.");
        }
    }

    //Method for the monster to deal damage to the player
    public void dealDamage2(Monster monster) {
        monster.setHitRate((4 * monster.getDexterity()) - mainCharacter.getAvoidRate());
        // initialize a random number between 1 and 100
        int randomNum = new Random().nextInt(100);
        if (randomNum < monster.getHitRate()) {
            if (mainCharacter.getDefense() > monster.getAttack()) {
                mainCharacter.setHealth(mainCharacter.getHealth() - 1);
                System.out.println("The monster dealt 1 damage to you.");
                System.out.println("Player HP: " + mainCharacter.getHealth() + "/" + mainCharacter.getMaxHealth());
                System.out.println("~~~~~~~~~~");
            }
            else {
                int damage = monster.getAttack() - mainCharacter.getDefense();
                mainCharacter.setHealth(mainCharacter.getHealth() - damage);
                System.out.println("The monster dealt " + damage + " damage to you.");
                if (mainCharacter.getHealth() < 0) {
                    mainCharacter.setHealth(0);
                    System.out.println("Player HP: " + mainCharacter.getHealth() + "/" + mainCharacter.getMaxHealth());
                    System.out.println("~~~~~~~~~~");
                }
                else {
                    System.out.println("Player HP: " + mainCharacter.getHealth() + "/" + mainCharacter.getMaxHealth());
                    System.out.println("~~~~~~~~~~");
                }
            }
        } else {
            System.out.println("The monster missed you.");
        }
    }


    //help command
    public void helpCommand() {
        System.out.println("(north,east,south,west)--move around");
        System.out.println("(look)--examine the room");
        System.out.println("(inspect)--inspect an item");
        System.out.println("(pickup)--pickup an item");
        System.out.println("(drop)--drop an item");
        System.out.println("(equip)--equip an item");
        System.out.println("(unequip)--unequip an item");
        System.out.println("(stats)--view player stats");
        System.out.println("(save)--save current progress");
        System.out.println("(inventory)--view inventory");
        System.out.println("(consume)--consume an item");
        System.out.println("(fight)--fight a monster");
        System.out.println("(teleport)--teleport to a room");
    }


    public void eyeOfTruth(Rooms currentRoom) {
        if (!currentRoom.getPuzzleIncluded().contains("N/A")) {
            System.out.println(listOfPuzzles.get(listOfRooms.indexOf(currentRoom)).getHint());
        } else {
            System.out.println("There are no puzzles in this room");
        }
    }

    public void activateSpells(String spellName, Monster monster, Player mainCharacter) {
        if (spellName.equalsIgnoreCase("Ray of fire")) {
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    monster.setHealth(monster.getHealth() - spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have dealt " + spell.getEffects() + " damage");
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }//end if
        if(spellName.equalsIgnoreCase("Flame Shield")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have gained " + spell.getEffects() + " amount of defense");
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    return;
                }
            }
        }//end if

        if(spellName.equalsIgnoreCase("Heat Wave")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setDefense(mainCharacter.getDefense() - spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have dealt " + spell.getEffects() + " damage");
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }
        if(spellName.equalsIgnoreCase("Meteor Storm")){
            Random random = new Random();
            int meteors = random.nextInt();
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    mainCharacter.setHealth(mainCharacter.getMaxHealth() - (meteors * spell.getEffects()));
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have casted " + meteors + " that deals 15 damage for each");
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }
        if(spellName.equalsIgnoreCase("flame master")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    mainCharacter.setHealth(mainCharacter.getHealth() + spell.getEffects());
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have gained " + spell.getEffects() + " health and defense");
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("Ice Shield")) {
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have gained " + spell.getEffects() + " amount of defense");
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("Channel Energy")) {
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() > spell.getManaCost()) {
                    mainCharacter.setMana(mainCharacter.getMana() + spell.getEffects());
                    System.out.println("Remaining mana: " + mainCharacter.getMana() + "/" + mainCharacter.getMaxMana());
                    return;
                }
            }
        }
        else{
            System.out.println("You cannot use this spell or you have ran of out mana");
        }
    }

    // Method to load game story
    //Ginette Wilson
    public void loadGameStory() {
        System.out.println("In the mystical world of Arcane Realms, where magic reigns supreme,\n You takes on the role of a young and promising wizard apprentice, recently accepted\n" +
                "into the Wizard City's Academy of Magical Arts. As you embark on this journey, \n you uncover dark secrets hidden within the walls of the academy" );
        System.out.println("Mysterious disappearances, ancient artifacts,\n" +
                "and whispers of a looming threat begin to surface, threatening the delicate balance of power\n" +
                "in Arcane Realms.");
        System.out.println("A sinister force, long thought to be mere legend, awakens from its slumber, seeking to plunge\n" +
                "the world into eternal darkness.");
    }

    // Method for game over
    // Lincoln Bruce
    public void gameOver() {
        System.out.println("Game Over");
        System.out.println("You have been defeated");
        System.out.println("Would you like to play again?");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("yes")) {
            RunGame();
        } else {
            System.out.println("Thank you for playing");
            System.exit(0);
        }
    }

    // Method for winning the game
    // Lincoln Bruce
    public void gameWin() {
        System.out.println("You defeated the beast causing chaos to the lands!");
        System.out.println("Would you like to play again?");
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("yes")) {
            RunGame();
        } else {
            System.out.println("Thank you for playing");
            System.exit(0);
        }
    }

}//end Game