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


    private int currentRoom = 0;
    private transient Scanner scan;
    private boolean gameOver;
    private transient Scanner scanner;

    // File paths for game elements
    //Ginette Wilson
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String MONSTERS_FILE_PATH = "src/Monsters.txt";
    private static final String ROOMS_FILE_PATH = "src/Rooms.txt";
    private static final String SPELLS_FILE_PATH = "src/Spells.txt";
    private static final String PLAYER_FILE_PATH = "src/Players.txt";

    // ArrayList to store game elements
    //Ginette Wilson
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Monster> listOfMonsters = new ArrayList<>();
    private static final ArrayList<Rooms> listOfRooms = new ArrayList<>();
    private static final ArrayList<Spells> listOfSpells = new ArrayList<>();
    private static final ArrayList<Player> listOfPlayers = new ArrayList<>();

    //Ginette Wilson
    public Game() {
        loadGameElements();// Initialize the game map
        RoomParsing roomParsing = new RoomParsing();
        gameOver = false; // Game over flag
        scanner = new Scanner(System.in); // Scanner for user input
        currentRoom = 0;
    }

    //Method to load game elements
    //Ginette Wilson
    private static void loadGameElements() {
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Monster.readMonsters(MONSTERS_FILE_PATH, listOfMonsters);
        Rooms.readRooms(ROOMS_FILE_PATH, listOfRooms);
        Spells.readSpells(SPELLS_FILE_PATH, listOfSpells);
        Player.readPlayers(PLAYER_FILE_PATH, listOfPlayers);
        addItemsToRoom(listOfItems, listOfRooms);
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

//    //Method to choose character
//    //Lincoln Bruce
//    public void chooseCharacter() {
//        System.out.println("Choose your character: ");
//        System.out.println(listOfPlayers);
//        Scanner scanner = new Scanner(System.in);
//        String scanner1 = scanner.nextLine();
//
//        for (Player player : listOfPlayers) {
//            if (player.getName().equalsIgnoreCase(scanner1)) {
//                System.out.println("You have chosen " + player.getName());
//                Player player1 = listOfPlayers.get(pl)
//                break;
//            }
//        }
//    }

    //Method to run the game
    //Kenny Amador
    public void RunGame() {
        System.out.println("Press q at any time if you wish to quit or y to continue");
        scan = new Scanner(System.in);
        Rooms currentRooms = listOfRooms.get(currentRoom);
        currentRooms.setHasBeenVisited(true);
        System.out.println(currentRooms.getRoomName() + ": " + currentRooms.getDescription());
        String command = scan.next();
        while (!command.equalsIgnoreCase("save")) { //set a condition in which the player can exit the game when wanted and by doing this the save/load automatically gets executed
            System.out.println("Please enter a navigation command north,east,south,west to move around");
            scan = new Scanner(System.in);
            command = scan.nextLine();
            currentRoom = checkCommand(command, currentRooms);
            if (currentRoom == -1) {
                System.out.println("You cannot go in this direction");
                continue;
            }
            currentRooms = listOfRooms.get(currentRoom);
            System.out.println(currentRooms.getRoomName() + ": " + currentRooms.getDescription());
            currentRooms.setHasBeenVisited(true);
        }
    }

//
//    private void displayLocation() {
//        Rooms room = rooms.get(currentRoom);
//        System.out.println("You are in " + room.getRoomName() + ".");
//        System.out.println(room.getDescription());
//        System.out.println("Available actions: look, north, south, east, west, items, status, quit");
//    }

//    private void handleInput(String userInput) {
//        switch (userInput) {
//            case "look":
//                lookAround();
//                break;
//            case "items":
//                displayInventory();
//                break;
//            case "status":
//                displayStatus();
//                break;
//            case "north":
//            case "south":
//            case "east":
//            case "west":
//                movePlayer(userInput);
//                break;
//            case "quit":
//                quitGame();
//                break;
//            default:
//                System.out.println("Invalid action. Type 'help' for available actions.");
//                break;
//        }
//    }

    //Method to check the command
    //Kenny Amador
    public int checkCommand(String command, Rooms rooms) {
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
//        if (command.equalsIgnoreCase("stats")) {
//            displayStatus();
//            return currentRoom;
//        }
        if (command.equalsIgnoreCase("save")) {
            return currentRoom;
        }

        return -1;
    }

    public void displayItems(Rooms rooms) {
        if (rooms.itemsIncluded.get(0).equalsIgnoreCase("n/a")) {
            System.out.println("There are no items in this room");
        } else {
            System.out.print("Items in this room: ");
            for (Item item : rooms.getRoomInventory()) {
                System.out.print(item + ",");
            }
            System.out.println();
        }
    }//end displayItems

//    private void lookAround() {
//        Rooms currentRoom = map.getRoomById(mainCharacter.getRoomID());
//        ArrayList<String> objects = currentRoom.getObjects();
//        if (objects.isEmpty()) {
//            System.out.println("There is nothing here to interact with.");
//        } else {
//            System.out.print("There is ");
//            for (int i = 0; i < objects.size(); i++) {
//                System.out.print(objects.get(i));
//                if (i < objects.size() - 1) {
//                    System.out.print(", ");
//                }
//            }
//            System.out.println(" in the room.");
//        }
//
//        // Check for monsters
//        ArrayList<Monster> monsters = currentRoom.getMonsters();
//        if (!monsters.isEmpty()) {
//            System.out.println("You see monsters in the room!");
//            for (Monster monster : monsters) {
//                System.out.println("A " + monster.getName() + " appears!");
//                startCombat(monster);
//            }
//        }
//    }
//
//    private void movePlayer(String direction) {
//        Rooms currentRoom = map.getRoomById(mainCharacter.getRoomID());
//        int nextRoomID = currentRoom.getExit(direction);
//        if (nextRoomID != -1) {
//            mainCharacter.setRoomID(nextRoomID);
//            displayLocation();
//        } else {
//            System.out.println("You cannot move in that direction.");
//        }
//    }
//
//    private void startCombat(Monster monster) {
//        while (mainCharacter.getHealth() > 0 && monster.getHealth() > 0) {
//            // Player's turn
//            int playerDamage = calculateDamage(mainCharacter.getAttack(), monster.getDefense());
//            monster.takeDamage(playerDamage);
//            System.out.println("You deal " + playerDamage + " damage to the " + monster.getName() + ".");
//
//            // Check if the monster is defeated
//            if (monster.getHealth() <= 0) {
//                System.out.println("You defeated the " + monster.getName() + "!");
//                return;
//            }
//
//            // Monster's turn
//            int monsterDamage = calculateDamage(monster.getAttack(), mainCharacter.getDefense());
//            mainCharacter.takeDamage(monsterDamage);
//            System.out.println("The " + monster.getName() + " deals " + monsterDamage + " damage to you.");
//
//            // Check if the player is defeated
//            if (mainCharacter.getHealth() <= 0) {
//                gameOver = true;
//                System.out.println("You were defeated by the " + monster.getName() + ". Game over!");
//                return;
//            }
//        }
//    }
//
//    private int calculateDamage(int attackerAttack, int defenderDefense) {
//        Random rand = new Random();
//        int damage = attackerAttack - defenderDefense;
//        // Add randomness to damage calculation
//        int randomModifier = rand.nextInt(5) - 2; // Random value between -2 and 2
//        damage += randomModifier;
//        return Math.max(damage, 0); // Damage cannot be negative
//    }
//
//    private void displayInventory() {
//        System.out.println("Inventory:");
//        ArrayList<Item> inventory = mainCharacter.getInventory();
//        if (inventory.isEmpty()) {
//            System.out.println("Your inventory is empty.");
//        } else {
//            for (Item item : inventory) {
//                System.out.println("- " + item.getItemName());
//            }
//        }
//    }
//
//
//    private void quitGame() {
//        gameOver = true;
//        System.out.println("Thank you for playing Arcane Realms! Goodbye.");
//    }
//}
}