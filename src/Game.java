import Characters.MainCharacter;
import Room.*;
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
    private Map map;
    private ArrayList<MainCharacter> mainCharacter;
    private boolean gameOver;
    private transient Scanner scanner;
    private ArrayList<Rooms> rooms;
    private transient Scanner scan;
    private int currentRoom;


    public Game() throws FileNotFoundException {
        map = new Map(); // Initialize the game map
        RoomParsing roomParsing = new RoomParsing();
        //mainCharacter = (map.readCharacters("Characters.txt")); // Initialize the main character
        gameOver = false; // Game over flag
        scanner = new Scanner(System.in); // Scanner for user input
        rooms = new ArrayList<>();
        rooms = (roomParsing.readRooms("Rooms.txt"));// read the arrayList and enter the file path
        currentRoom = 0;
    }

    public void RunGame() {
        System.out.println("Press q at any time if you wish to quit or y to continue");
        scan = new Scanner(System.in);
        Rooms currentRooms = rooms.get(currentRoom);
        currentRooms.setHasBeenVisited(true);
        System.out.println(currentRooms.getRoomName() + ": " + currentRooms.getDescription());
        String command = scan.next();
        while (!command.equalsIgnoreCase("save")) { //set a condition in which the player can exit the game when wanted and by doing this the save/load automatically gets executed
            System.out.println("Please enter a navigation command north,east,south,west to move around");
            scan = new Scanner(System.in);
            command = scan.nextLine();
            currentRoom = checkCommand(command, currentRooms);
            if(currentRoom == -1){
                System.out.println("You cannot go in this direction");
                continue;
            }
            currentRooms = rooms.get(currentRoom);
            System.out.println(currentRooms.getRoomName() + ": " + currentRooms.getDescription());
            currentRooms.setHasBeenVisited(true);
        }
    }

//    public void play() {
//        // Welcome message and game setup
//        System.out.println("Welcome to Arcane Realms!");
//        createCharacter();
//        while (!gameOver) {
//            displayLocation();
//            String userInput = scanner.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitivity
//            handleInput(userInput);
//        }
//    }

//    private void createCharacter() {
//        System.out.println("Create your character:");
//        System.out.print("Enter character name: ");
//        String player = scanner.nextLine();
//        mainCharacter = new Characters.MainCharacter(player, "alex", 10, "School of Ice best student", 10, 100, 20, 5, 12, 6); // Adjust attributes as needed
//        System.out.println("Characters.Character creation successful!");
//    }

//
//    private void displayLocation() {
//        Room.Rooms room = rooms.get(currentRoom);
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

    public int checkCommand(String command, Rooms rooms) {
        ArrayList<Integer> connects = rooms.roomExits;
        if(command.equalsIgnoreCase("north")) {
            return connects.get(0) - 1;
        }
        if(command.equalsIgnoreCase("east")) {
            return connects.get(1) - 1;
        }
        if(command.equalsIgnoreCase("south")) {
            return connects.get(2) - 1;
        }
        if(command.equalsIgnoreCase("west")) {
            return connects.get(3) - 1;
        }
        if(command.equalsIgnoreCase("look")){
            displayItems(rooms);
            return currentRoom;
        }
        if(command.equalsIgnoreCase("stats")){
            displayStatus();
            return currentRoom;
        }
        if(command.equalsIgnoreCase("save")){
            return currentRoom;
        }
        return -1;
    }

    public void displayItems(Rooms rooms){
        if(rooms.itemsIncluded.get(0).equalsIgnoreCase("n/a")){
            System.out.println("There are no items in this room");
        }
        else {
            System.out.print("Items in this room: ");
            for (String str: rooms.itemsIncluded){
                System.out.print(str + ",");
            }
            System.out.println();
        }
    }//end displayItems

//    private void lookAround() {
//        Room.Rooms currentRoom = map.getRoomById(mainCharacter.getRoomID());
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
//        ArrayList<Characters.Monster> monsters = currentRoom.getMonsters();
//        if (!monsters.isEmpty()) {
//            System.out.println("You see monsters in the room!");
//            for (Characters.Monster monster : monsters) {
//                System.out.println("A " + monster.getName() + " appears!");
//                startCombat(monster);
//            }
//        }
//    }
//
//    private void movePlayer(String direction) {
//        Room.Rooms currentRoom = map.getRoomById(mainCharacter.getRoomID());
//        int nextRoomID = currentRoom.getExit(direction);
//        if (nextRoomID != -1) {
//            mainCharacter.setRoomID(nextRoomID);
//            displayLocation();
//        } else {
//            System.out.println("You cannot move in that direction.");
//        }
//    }
//
//    private void startCombat(Characters.Monster monster) {
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
//            // Characters.Monster's turn
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
//        ArrayList<Items.Item> inventory = mainCharacter.getInventory();
//        if (inventory.isEmpty()) {
//            System.out.println("Your inventory is empty.");
//        } else {
//            for (Items.Item item : inventory) {
//                System.out.println("- " + item.getItemName());
//            }
//        }
//    }
//
    private void displayStatus() {
        System.out.println("Status:");
        System.out.println("Name: " + mainCharacter.get(0).getName());
        System.out.println("Health: " + mainCharacter.get(0).getHealth() + "/" + mainCharacter.get(0).getHealth());
        System.out.println("Attack: " + mainCharacter.get(0).getAttack());
        System.out.println("Defense: " + mainCharacter.get(0).getDefense());
        System.out.println("Speed: " + mainCharacter.get(0).getSpeed());
        System.out.println("Gold: " + mainCharacter.get(0).getPlayerCoins());
        //System.out.println("Experience: " + mainCharacter.get);
    }
}
//
//    private void quitGame() {
//        gameOver = true;
//        System.out.println("Thank you for playing Arcane Realms! Goodbye.");
//    }
//}