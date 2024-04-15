import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Thuy Vy Pham
public class Map {
    private static ArrayList<Item> listOfItems;
    private static ArrayList<Puzzle> listOfPuzzles;
    private static ArrayList<Monster> listOfMonsters;
    private static ArrayList<Rooms> listOfRooms;
    private static ArrayList<Spells> listOfSpells;

    public Map() throws FileNotFoundException {
        listOfItems = new ArrayList<>();
        listOfPuzzles = new ArrayList<>();
        listOfMonsters = new ArrayList<>();
        listOfRooms = new ArrayList<>();
        listOfSpells = new ArrayList<>();

        readRooms("Rooms.txt");
        readItems("Items.txt");
        readPuzzles("puzzles.txt");
        readMonsters("Monsters.txt");


    }

    //Read rooms from file
    public static void readRooms(String filePath) {

    }

    // Read items from file
    // Huyen Pham
    public static void readItems(String filePath) {
        try {
            File myItems = new File(filePath);
            Scanner myReader = new Scanner(myItems);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] itemData = data.split("-");
                int itemID = Integer.parseInt(itemData[0].trim());
                String itemType = itemData[1].trim();
                String itemName = itemData[2].trim();
                String description = itemData[3].trim();
                int value = Integer.parseInt(itemData[4].trim());
                int itemRoomID = Integer.parseInt(itemData[5].trim());

                //Lincoln Bruce
                if (itemType.equalsIgnoreCase("decoration")) {
                    Item item = new Item(itemID, itemType, itemName, description, value, itemRoomID);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("equipable")) {
                    int addedHealth = Integer.parseInt(itemData[6].trim());
                    int addedMagic = Integer.parseInt(itemData[7].trim());
                    int addedDexterity = Integer.parseInt(itemData[8].trim());
                    int addedSpeed = Integer.parseInt(itemData[9].trim());
                    int addedDefense = Integer.parseInt(itemData[10].trim());
                    String itemUtility = itemData[11].trim();
                    Equipable item = new Equipable(itemID, itemType, itemName, description, value, itemRoomID, addedHealth, addedMagic, addedDexterity, addedSpeed, addedDefense, itemUtility);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("consumable")) {
                    int healedHealth = Integer.parseInt(itemData[6].trim());
                    Consumable item = new Consumable(itemID, itemType, itemName, description, value, itemRoomID, healedHealth);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("throwable")) {
                    int damageDealt = Integer.parseInt(itemData[6].trim());
                    int speedReduction = Integer.parseInt(itemData[7].trim());
                    Throwable item = new Throwable(itemID, itemType, itemName, description, value, itemRoomID, damageDealt, speedReduction);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("PuzzleItem")) {
                    int puzzleID = Integer.parseInt(itemData[6].trim());
                    PuzzleItem item = new PuzzleItem(itemID, itemType, itemName, description, value, itemRoomID, puzzleID);
                    listOfItems.add(item);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred with the items file.");
        }
    }

    // Read puzzles from file
    // Thuy Vy Pham
    public static void readPuzzles(String filePath) {
        try {
            File myPuzzles = new File(filePath);
            Scanner myReader = new Scanner(myPuzzles);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] puzzleData = data.split("-");
                String puzzleID = puzzleData[0];
                String name = puzzleData[1];
                String description = puzzleData[2];
                String failMessage = puzzleData[3];
                String hint = puzzleData[4];
                Puzzle puzzle = new Puzzle(puzzleID, name, description, failMessage, hint);
                listOfPuzzles.add(puzzle);
            }
        } catch (Exception e) {
            System.out.println("An error occurred with the puzzle file.");
        }
    }

    // Read monsters from file
    // Lincoln Bruce
    public static void readMonsters(String filePath) {
        try {
            File myMonsters = new File(filePath);
            Scanner myReader = new Scanner(myMonsters);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] monsterData = data.split("-");
                String name = monsterData[0];
                String roomID = monsterData[1];
                String description = monsterData[2];
                int health = Integer.parseInt(monsterData[3]);
                int attack = Integer.parseInt(monsterData[4]);
                int dexterity = Integer.parseInt(monsterData[5]);
                int speed = Integer.parseInt(monsterData[6]);
                int expDrop = Integer.parseInt(monsterData[7]);
                int goldDrop = Integer.parseInt(monsterData[8]);
                Monster monster = new Monster(name, roomID, description, health, attack, dexterity, speed, expDrop, goldDrop);
                listOfMonsters.add(monster);
            }
        } catch (Exception e) {
            System.out.println("An error occurred with the monsters file.");
        }
    }

    //Read Spells from file
    //Thuy Vy Pham
    public static void readSpells(String filePath) throws FileNotFoundException {
        try{
            File mySpells = new File(filePath);
            Scanner myReader = new Scanner(mySpells);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] spellData = data.split("-");
                String spellID = spellData[0];
                String name = spellData[1];
                String description = spellData[2];
                Spells spells = new Spells(spellID, name, description);
                listOfSpells.add(spells);
            }
        } catch(Exception e){
            System.out.println("An error occurred with the spell file.");
        }
    }
}
