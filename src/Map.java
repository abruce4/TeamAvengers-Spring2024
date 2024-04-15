import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Thuy Vy Pham
public class Map {
    private static ArrayList<Item> listOfItems;
    private static ArrayList<Puzzle> listOfPuzzles;
    private static ArrayList<Character> listOfCharacters;
    private static ArrayList<Rooms> listOfRooms;
    private static ArrayList<Spells> listOfSpells;

    public Map() throws FileNotFoundException {
        listOfItems = new ArrayList<>();
        listOfPuzzles = new ArrayList<>();
        listOfCharacters = new ArrayList<>();
        listOfRooms = new ArrayList<>();
        listOfSpells = new ArrayList<>();

        readItems("Items.txt");
        readPuzzles("puzzles.txt");
        readCharacters("Character.txt");


    }

    //Read rooms from file
    public ArrayList<Rooms> readRooms(String filePath) {
        Scanner infile;
        try{
            infile = new Scanner(new File(filePath));
            while(infile.hasNext()) {
                String[] line = infile.nextLine().split("~");
                int roomID = Integer.parseInt(line[0]);
                String roomName = line[1];
                String roomDesc = line[2];
                ArrayList<Integer> connects = parseExits(line[3]);
                ArrayList<String> monsterID = parseMonsterID(line[4]);
                ArrayList<String> itemsID = parseItemID(line[5]);
                String puzzleID = line[6];
                Boolean hasBeenVisited = Boolean.parseBoolean(line[7]);
                listOfRooms.add(new Rooms(roomID, roomName, roomDesc, connects, monsterID, itemsID, puzzleID, hasBeenVisited));
            }
        } catch (FileNotFoundException fnfe){
            System.out.println("Could not find the correct room file");
        }
        return listOfRooms;
    }

    public ArrayList<String> parseItemID(String id){
        ArrayList<String> itemID = new ArrayList<>();
        String[] line = id.split(",");
        for(String str: line){
            itemID.add(str);
        }
        return itemID;
    }

    //parse roomConnects
    public ArrayList<Integer> parseExits(String exit){
        ArrayList<Integer> roomConnects = new ArrayList<>();
        String [] line = exit.split(",");
        for(String str:line){
            roomConnects.add(Integer.parseInt(str));
        }
        return roomConnects;
    }

    //ArrayList of monsterID in the rooms
    public ArrayList<String> parseMonsterID(String monsterID){
        ArrayList<String> roomMonsterID = new ArrayList<>();
        String[] line = monsterID.split(",");
        for(String str:line){
            roomMonsterID.add(str);
        }
        return roomMonsterID;
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
                String itemID = itemData[0].trim();
                String itemType = itemData[1].trim();
                String itemName = itemData[2].trim();
                String description = itemData[3].trim();
                int value = Integer.parseInt(itemData[4].trim());
                int itemRoomID = Integer.parseInt(itemData[5].trim());

                //Lincoln Bruce
                if (itemType.equalsIgnoreCase("decoration")) {
                    Item item = new Item(itemID, itemType, itemName, description, value);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("equipable")) {
                    int addedHealth = Integer.parseInt(itemData[6].trim());
                    int addedMagic = Integer.parseInt(itemData[7].trim());
                    int addedDexterity = Integer.parseInt(itemData[8].trim());
                    int addedSpeed = Integer.parseInt(itemData[9].trim());
                    int addedDefense = Integer.parseInt(itemData[10].trim());
                    String itemUtility = itemData[11].trim();
                    Equipable item = new Equipable(itemID, itemType, itemName, description, value, addedHealth, addedMagic, addedDexterity, addedSpeed, addedDefense, itemUtility);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("consumable")) {
                    int healedHealth = Integer.parseInt(itemData[6].trim());
                    Consumable item = new Consumable(itemID, itemType, itemName, description, value, healedHealth);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("throwable")) {
                    int damageDealt = Integer.parseInt(itemData[6].trim());
                    int speedReduction = Integer.parseInt(itemData[7].trim());
                    Throwable item = new Throwable(itemID, itemType, itemName, description, value, damageDealt, speedReduction);
                    listOfItems.add(item);
                } else if (itemType.equalsIgnoreCase("PuzzleItem")) {
                    int puzzleID = Integer.parseInt(itemData[6].trim());
                    PuzzleItem item = new PuzzleItem(itemID, itemType, itemName, description, value, puzzleID);
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
                String solution = puzzleData[3]; // Assuming this is where the solution is defined.
                String failMessage = puzzleData[4];
                String hint = puzzleData[5];
                Puzzle puzzle = new Puzzle(puzzleID, name, description, solution, failMessage, hint);
                listOfPuzzles.add(puzzle);
            }
        } catch (Exception e) {
            System.out.println("An error occurred with the puzzle file.");
        }
    }

    // Read monsters from file
    // Lincoln Bruce
    public static void readCharacters(String filePath) {
        try {
            File myCharacters = new File(filePath);
            Scanner myReader = new Scanner(myCharacters);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] characterData = data.split("-");
                String characterType = characterData[0];
                String name = characterData[1];
                String description = characterData[2];
                int health = Integer.parseInt(characterData[3]);
                int attack = Integer.parseInt(characterData[4]);
                int dexterity = Integer.parseInt(characterData[5]);
                int speed = Integer.parseInt(characterData[6]);

                if (characterType.equalsIgnoreCase("monster")) {
                    int expDrop = Integer.parseInt(characterData[7]);
                    int goldDrop = Integer.parseInt(characterData[8]);
                    String monsterID = characterData[9];
                    Monster character = new Monster(characterType, name, description, health, attack, dexterity, speed, expDrop, goldDrop, monsterID);
                    listOfCharacters.add(character);
                }
                else if (characterType.equalsIgnoreCase("player")) {
                    int mana = Integer.parseInt(characterData[7]);
                    int defense = Integer.parseInt(characterData[8]);
                    MainCharacter character = new MainCharacter(characterType, name, description, health, attack, dexterity, speed, mana, defense);
                    listOfCharacters.add(character);
                }
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
