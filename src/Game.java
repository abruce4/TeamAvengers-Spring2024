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
    private boolean gameOver;
    private transient Scanner scanner;
    private final Player mainCharacter;

    // File paths for game elements
    // Ginette Wilson - Lincoln Bruce
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String MONSTERS_FILE_PATH = "src/Monsters.txt";
    private static final String ROOMS_FILE_PATH = "src/Rooms.txt";
    private static final String SPELLS_FILE_PATH = "src/spells.txt";


    // ArrayList to store game elements
    // Ginette Wilson - Lincoln Bruce
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Monster> listOfMonsters = new ArrayList<>();
    private static final ArrayList<Rooms> listOfRooms = new ArrayList<>();
    private static final ArrayList<Spells> listOfSpells = new ArrayList<>();


    //Method to create the game
    //Ginette Wilson
    public Game() {
        loadGameElements();// Initialize the game map
        gameOver = false; // Game over flag
        scanner = new Scanner(System.in); // Scanner for user input
        currentRoom = 0;
        mainCharacter = new Player(25, 10, 10, 7, 20, 5, listOfRooms.getFirst());
    }


    // Method to run the game
    // Kenny Amador
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

    // Method to check if the room has been visited
    // Kenny Amador
    public void checkRoom(Rooms rooms) {
        if (rooms.getHasBeenVisited()) {
            System.out.println("You have been here before");
        }
        if (rooms.getShop()) {
            shop(rooms);
        }
    }

    // Method to shop
    // Kenny Amador
    public void shop(Rooms rooms) {
        System.out.println("Would you like to shop or sell?");
        scan = new Scanner(System.in);
        String command = scan.nextLine();
        if (command.equalsIgnoreCase("shop")) {
            while (!command.equalsIgnoreCase("quit")) {
                System.out.println("Which items would you like to buy?");
                mainCharacter.displayItems(rooms);
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

    // Method to buy items
    // Kenny Amador
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

    // Method to check the command
    // Kenny Amador
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
           mainCharacter.displayItems(rooms);
           mainCharacter.displayMonsters(rooms);
           mainCharacter.displayPuzzles(rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("solve")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("What is your answer?");
            mainCharacter.solvePuzzle(command, mainCharacter.getPlayerInventory(), listOfItems, rooms, rooms.getRoomPuzzle());
            return currentRoom;
        }
        if (command.equalsIgnoreCase("inspect")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to inspect?");
            command = scan.nextLine();
            mainCharacter.inspectItem(command, mainCharacter);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("pickup")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to pick up?");
            command = scan.nextLine();
            mainCharacter.pickup(command, mainCharacter, rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("drop")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to drop?");
            command = scan.nextLine();
            mainCharacter.drop(command, mainCharacter, rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("consume")) {
            System.out.println("~~~~~~~~~~");
            System.out.println("Which item would you like to consume?");
            command = scan.nextLine();
            mainCharacter.consume(command, mainCharacter);
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
        if (command.equalsIgnoreCase("Eye of truth")) {
            eyeOfTruth(rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("puzzle")) {
            mainCharacter.examinePuzzle(rooms);
            return currentRoom;
        }

        return -1;
    }

    // Method to print monster stats
    // Ginette Wilson
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


    // Method to sell items
    // Kenny Amador
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


    // Method to be teleported to a room
    // Ginette Wilson
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

    // Method to display player stats
    // Kenny Amador
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
    // Lincoln Bruce
    public void attackMonster(String monsterName, Player mainCharacter, Rooms currentRoom) {
        for (Monster monster : currentRoom.getRoomMonsters()) {
            if (monster.getName().equalsIgnoreCase(monsterName)) {
                mainCharacter.setInBattle(true);
                System.out.println("You are now in battle with the " + monsterName);
                while (mainCharacter.getInBattle()) {
                    if (mainCharacter.getHealth() <= 0) {
                        System.out.println("You have been defeated by the " + monsterName);
                        mainCharacter.setInBattle(false);
                        break;
                    } else if (monster.getHealth() <= 0) {
                        System.out.println("You have defeated the " + monsterName);
                        System.out.println("~~~~~~~~~~");
                        mainCharacter.setPlayerExp(mainCharacter.getPlayerExp() + monster.getExpDrop());
                        System.out.println("You have gained " + monster.getExpDrop() + " experience points.");
                        System.out.println("~~~~~~~~~~");
                        mainCharacter.setPlayerCoins(mainCharacter.getPlayerCoins() + monster.getGoldDrop());
                        System.out.println("You have gained " + monster.getGoldDrop() + " coins.");
                        System.out.println("~~~~~~~~~~");
                        mainCharacter.levelUp();
                        mainCharacter.setInBattle(false);
                        break;
                    } else {
                        System.out.println("Choose an action: attack, consume, throw, spells or escape");
                        String action = scanner.nextLine();
                        if (action.equalsIgnoreCase("attack")) {
                            dealDamage(monster);
                            dealDamage2(monster);
                        }
                        else if (action.equalsIgnoreCase("examine")) {
                            examine(currentRoom);
                        }
                        else if (action.equalsIgnoreCase("consume")) {
                            System.out.println("Which item would you like to consume?");
                            String itemToConsume = scanner.nextLine();
                            mainCharacter.consume(itemToConsume, mainCharacter);
                        } else if (action.equalsIgnoreCase("escape")) {
                            mainCharacter.escape(currentRoom, mainCharacter.getInBattle());
                        } else if (action.equalsIgnoreCase("throw")) {
                            System.out.println("Which item would you like to throw?");
                            String itemToThrow = scanner.nextLine();
                            mainCharacter.throwItem(itemToThrow, monster, mainCharacter);
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
    // Lincoln Bruce
    public void dealDamage(Monster monster) {
        mainCharacter.setHitRate((4 * mainCharacter.getDexterity() + mainCharacter.getBaseHitRate()) - monster.getAvoidRate());
        if (mainCharacter.getHitRate() > 50) {
            monster.setHealth(monster.getHealth() - mainCharacter.getMagic());
            System.out.println("You dealt " + mainCharacter.getMagic() + " damage to the monster.");
            System.out.println("~~~~~~~~~~");
            mainCharacter.ringOfRegeneration(mainCharacter.getPlayerInventory());
            System.out.println(mainCharacter.getHealth() + "/" + mainCharacter.getMaxHealth());
        } else {
            System.out.println("You missed the monster.");
        }
    }

    // Method for the monster to deal damage to the player
    // Lincoln Bruce
    public void dealDamage2(Monster monster) {
        monster.setHitRate((4 * monster.getDexterity()) - mainCharacter.getAvoidRate());
        if (monster.getHitRate() > 50) {
            mainCharacter.setHealth(mainCharacter.getHealth() - monster.getAttack());
            System.out.println("The monster dealt " + monster.getAttack() + " damage to you.");
            System.out.println("~~~~~~~~~~");
            mainCharacter.ringOfRegeneration(mainCharacter.getPlayerInventory());
            System.out.println(monster.getHealth() + "/" + monster.getHealth());
        } else {
            System.out.println("The monster missed you.");
        }
    }

    // Method to display the help command
    // Kenny Amador
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
        System.out.println("(examine)--examine a monster");
        System.out.println("(Eye of truth/eot)--get a hint for a puzzle");
        System.out.println("(puzzle)--interact with a puzzle");
        System.out.println("(spells)--cast a spell");
    }

    public void eyeOfTruth(Rooms currentRoom) {
        if (!currentRoom.getPuzzleIncluded().contains("N/A")) {
            System.out.println(listOfPuzzles.get(listOfRooms.indexOf(currentRoom)).getHint());
        } else {
            System.out.println("There are no puzzles in this room");
        }
    }

    // Method to activate spells
    // Kenny Amador
    public void activateSpells(String spellName, Monster monster, Player mainCharacter) {
        if (spellName.equalsIgnoreCase("Ray of fire")) {
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()){
                    monster.setHealth(monster.getHealth() - spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have dealt " + spell.getEffects() + " damage to the monster");
                    System.out.println("mana: " + mainCharacter.getMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("Flame Shield")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()) {
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("Your defense has increased by " + spell.getEffects());
                    System.out.println("mana: " + mainCharacter.getMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("Heat Wave")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()) {
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setDefense(mainCharacter.getDefense() - spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have dealt " + spell.getEffects() + " damage to the monster");
                    System.out.println("mana: " + mainCharacter.getMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("Meteor Storm")){
            Random random = new Random();
            int meteors = random.nextInt(5);
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()) {
                    mainCharacter.setHealth(mainCharacter.getMaxHealth() - (meteors * spell.getEffects()));
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have casted " + meteors + " that deals 15 damage per meteor");
                    System.out.println("mana: " + mainCharacter.getMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("flame master")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()) {
                    mainCharacter.setHealth(mainCharacter.getHealth() + spell.getEffects());
                    mainCharacter.setDefense(mainCharacter.getDefense() + spell.getEffects());
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have gained" + spell.getEffects() + " health and defense");
                    System.out.println("mana: " + mainCharacter.getMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("Ice Shield")) {
            for (Spells spell : listOfSpells) {
                Random random = new Random();
                int temperature = random.nextInt(spell.getEffects());
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()) {
                    mainCharacter.setDefense(mainCharacter.getDefense() + temperature);
                    mainCharacter.setMana(mainCharacter.getMana() - spell.getManaCost());
                    System.out.println("You have gained " + temperature  + " defense");
                    System.out.println("mana: " + mainCharacter.getMana());
                    dealDamage2(monster);
                    return;
                }
            }
        }

        if(spellName.equalsIgnoreCase("channel energy")){
            for (Spells spell : listOfSpells) {
                if (mainCharacter.getPlayerLevel() >= spell.getLevelNeeded() & spell.getName().equalsIgnoreCase(spellName) & mainCharacter.getMana() >= spell.getManaCost()) {
                    mainCharacter.setMana(mainCharacter.getMana() + spell.getEffects());
                    System.out.println("You have gained " + spell.getEffects() + "mana");
                    return;
                }
            }
        }
        else{
            System.out.println("You cannot use this spell or have run out of mana");
        }
    }//end activateSpells

    // Method to load game elements
    // Ginette Wilson - Lincoln Bruce
    private static void loadGameElements() {
        Rooms.readRooms(ROOMS_FILE_PATH, listOfRooms);
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Monster.readMonsters(MONSTERS_FILE_PATH, listOfMonsters);
        addItemsToRoom(listOfItems, listOfRooms);
        addMonstersToRoom(listOfMonsters, listOfRooms);
        addPuzzlesToRoom(listOfPuzzles, listOfRooms);
        Spells.readSpells(SPELLS_FILE_PATH, listOfSpells);
    } // end loadGameElements


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
    }//end addPuzzlesToRoom

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
    }//end addItemsToRoom

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
    }//end addMonstersToRoom

}//end Game