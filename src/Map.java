import Characters.Character;
import Characters.MainCharacter;
import Items.*;
import Items.Throwable;
import Puzzle.Puzzle;
import Room.Rooms;
import Puzzle.PuzzleItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

//Thuy Vy Pham
public class Map implements Serializable {

    // File paths for game elements
    private static final String ITEMS_FILE_PATH = "src/Items.txt";
    private static final String PUZZLES_FILE_PATH = "src/Puzzles.txt";
    private static final String CHARACTERS_FILE_PATH = "src/Characters.txt";
    private static final String ROOMS_FILE_PATH = "src/Rooms.txt";
    private static final String SPELLS_FILE_PATH = "src/Spells.txt";

    // ArrayList to store game elements
    private static final ArrayList<Item> listOfItems = new ArrayList<>();
    private static final ArrayList<Puzzle> listOfPuzzles = new ArrayList<>();
    private static final ArrayList<Character> listOfCharacters = new ArrayList<>();
    private static final ArrayList<Rooms> listOfRooms = new ArrayList<>();
    private static final ArrayList<Spells> listOfSpells = new ArrayList<>();

    //Constructor
    public Map() throws FileNotFoundException {
        Item.readItems(ITEMS_FILE_PATH, listOfItems);
        Puzzle.readPuzzles(PUZZLES_FILE_PATH, listOfPuzzles);
        Character.readCharacters(CHARACTERS_FILE_PATH,listOfCharacters);
        readRooms(ROOMS_FILE_PATH);
        Spells.readSpells(SPELLS_FILE_PATH, listOfSpells);
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




}
