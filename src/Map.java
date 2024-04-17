import java.io.Serializable;
import java.util.ArrayList;

//Thuy Vy Pham
public class Map implements Serializable {

    // File paths for game elements
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String CHARACTERS_FILE_PATH = "src/Monsters.txt";
    private static final String ROOMS_FILE_PATH = "src/Rooms.txt";
    private static final String SPELLS_FILE_PATH = "src/Spells.txt";

    // ArrayList to store game elements
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Character> listOfCharacters = new ArrayList<>();
    private static final ArrayList<Rooms> listOfRooms = new ArrayList<>();
    private static final ArrayList<Spells> listOfSpells = new ArrayList<>();

    //Constructor
    public Map() {
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Character.readCharacters(CHARACTERS_FILE_PATH,listOfCharacters);
        Rooms.readRooms(ROOMS_FILE_PATH, listOfRooms);
        Spells.readSpells(SPELLS_FILE_PATH, listOfSpells);
        //addItemsToRoom(listOfItems, listOfRooms);
       // System.out.println(listOfRooms.get(8).getRoomInventory());
    }

//    //Method to add items to the room
//    public void addItemsToRoom(ArrayList<Item> listOfItems, ArrayList<Rooms> listOfRooms) {
//        for (Rooms room : listOfRooms) {
//            for (Item item : listOfItems) {
//                if (room.getItemsIncluded().contains(item.getItemName())) {
//                    room.getRoomInventory().add(item);
//                }
//            }
//        }
//    }

}
