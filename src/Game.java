import java.io.*;
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
    private Player mainCharacter;

    // File paths for game elements
    //Ginette Wilson
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String MONSTERS_FILE_PATH = "src/Monsters.txt";
    private static final String ROOMS_FILE_PATH = "src/Rooms.txt";
    private static final String SPELLS_FILE_PATH = "src/Spells.txt";

    // ArrayList to store game elements
    //Ginette Wilson
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Monster> listOfMonsters = new ArrayList<>();
    private static final ArrayList<Rooms> listOfRooms = new ArrayList<>();
    private static final ArrayList<Spells> listOfSpells = new ArrayList<>();

    //Ginette Wilson
    public Game() {
        loadGameElements();// Initialize the game map
        System.out.println(listOfRooms.get(14).getRoomMonsters());
        gameOver = false; // Game over flag
        scanner = new Scanner(System.in); // Scanner for user input
        currentRoom = 0;
        mainCharacter = new Player(25, 10, 10, 7, 20, 5, listOfRooms.get(0));
    }

    //Method to load game elements
    //Ginette Wilson
    private static void loadGameElements() {
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Monster.readMonsters(MONSTERS_FILE_PATH, listOfMonsters);
        Rooms.readRooms(ROOMS_FILE_PATH, listOfRooms);
        Spells.readSpells(SPELLS_FILE_PATH, listOfSpells);
        addItemsToRoom(listOfItems, listOfRooms);
        addMonstersToRoom(listOfMonsters, listOfRooms);
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
    public void checkRoom(Rooms rooms){
        if (rooms.getHasBeenVisited()){
            System.out.println("You have been here before");
        }
        if(rooms.getShop()){
            shop(rooms);
        }
    }

    public void shop(Rooms rooms){
        mainCharacter.setPlayerCoins(100);
        System.out.println("Would you like to shop or sell?");
        scan = new Scanner(System.in);
        String command = scan.nextLine();
        if(command.equalsIgnoreCase("shop")){
            while(!command.equalsIgnoreCase("quit")){
                System.out.println("Which items would you like to buy?");
                displayItems(rooms);
                command = scan.nextLine();
                buyItem(command,mainCharacter,rooms);
            }
        }
        if(command.equalsIgnoreCase("sell")){
            System.out.println("Which items would you like to sell?");
            command = scan.nextLine();
            sell(command,mainCharacter,rooms);

        }

    }
    public void buyItem(String itemName, Player mainCharacter, Rooms currentRoom){
        for (Item item : currentRoom.getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName) & mainCharacter.getPlayerCoins() >= item.getItemValue()) {
                mainCharacter.getPlayerInventory().add(item);
                currentRoom.getRoomInventory().remove(item);
                System.out.println(itemName + " has been added to your inventory.");
                mainCharacter.setPlayerCoins(mainCharacter.getPlayerCoins() -item.getItemValue());
                return;
            }
        }
        if(itemName.equalsIgnoreCase("quit")){
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
            return currentRoom;
        }
        if (command.equalsIgnoreCase("inspect")) {
            System.out.println("Which item would you like to inspect?");
            command = scan.nextLine();
            inspectItem(command, mainCharacter);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("pickup")) {
            System.out.println("Which item would you like to pick up?");
            command = scan.nextLine();
            pickup(command, mainCharacter, rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("drop")) {
            System.out.println("Which item would you like to drop?");
            command = scan.nextLine();
            drop(command, mainCharacter, rooms);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("equip")) {
            System.out.println("Which item would you like to equip?");
            command = scan.nextLine();  // continues to use command variable
            mainCharacter.equipItem(command);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("unequip")) {
            mainCharacter.unequipItem();
            return currentRoom;
        }

        if(command.equalsIgnoreCase("stats")){
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
            System.out.println("Where would you like to teleport to?");
            String roomName = scanner.nextLine();
            teleport(roomName);
            return currentRoom;
        }
        if (command.equalsIgnoreCase("inventory")) {
            mainCharacter.inventory();
            return currentRoom;
        }
        if(command.equalsIgnoreCase("help")){
            helpCommand();
            return currentRoom;
        }

        return -1;
    }


    //Method to print monster stats
    //Ginette Wilson
    public void examine(Rooms currentRoom) {
        for (Monster monster : currentRoom.getRoomMonsters()) {
            System.out.println("Name: " + monster.getName());
            System.out.println("Description: " + monster.getDescription());
            System.out.println("Health Points: " + monster.getHealth());
            System.out.println("Attack Damage: " + monster.getAttack());
            System.out.println("Dexterity: " + monster.getDexterity());
            System.out.println("Speed: " + monster.getSpeed());
        }
    }


    //Method to display items in the room
    //Kenny Amador
    public void displayItems(Rooms rooms) {
        if (rooms.itemsIncluded.get(0).equalsIgnoreCase("n/a")) {
            System.out.println("There are no items in this room");
        } else if(rooms.getShop()){
            for (Item item : rooms.getRoomInventory()) {
                System.out.println("["+ item + "]" + " cost: " + item.getItemValue());
            }
        }else {
            System.out.print("Items in this room: ");
            for (Item item : rooms.getRoomInventory()) {
                System.out.print("["+ item + "]");
            }
            System.out.println();
        }
    }//end displayItems

    // Method to inspect an item in the current room
    //Huyen Pham
    public void inspectItem(String itemName, Player mainCharacter) {
        for (Item item : mainCharacter.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                System.out.println("Inspecting: " + itemName);
                System.out.println("Description: " + item.getItemDescription());
                System.out.println("Type: " + item.getItemType());
                System.out.println("Value: " + item.getItemValue());
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
                mainCharacter.setPlayerCoins(mainCharacter.getPlayerCoins()+item.getItemValue());
                System.out.println(mainCharacter.getPlayerCoins());
                return;
            }
        }
        if(itemName.equalsIgnoreCase("quit")){
            System.out.println("~~~~~~~~~~");
        }
        System.out.println("You have nothing to sell");
    }

    //Method to drop an item
    //Thuy Vy
    public void drop(String itemName, Player mainCharacter, Rooms currentRoom) {
        for (Item item : mainCharacter.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                mainCharacter.getPlayerInventory().remove(item);
                currentRoom.getRoomInventory().add(item);
                System.out.println(itemName + " has been dropped from your inventory.");
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
                return;
            }
        }
        System.out.println("There is no " + itemName + " in this room.");
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



    public void displayStats(){
        System.out.println("~~~~~~~~~~");
        System.out.println("Health: " + mainCharacter.getHealth());
        System.out.println("Magic: " + mainCharacter.getMagic());
        System.out.println("Dexterity: " + mainCharacter.getDexterity());
        System.out.println("Speed: " + mainCharacter.getSpeed());
        System.out.println("Mana: " + mainCharacter.getMana());
        System.out.println("Defense: " + mainCharacter.getDefense());
        System.out.println("~~~~~~~~~~");
    }

    // Method to check who attack first

    // Method for player to attack a monster
    public void attackMonster() {

    }

    // Method for player to attack a monster

    //Method for monster to attack player
    public void attackPlayer() {

    }

    //help command
    public void helpCommand(){
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
    }

}//end Game