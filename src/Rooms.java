import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Rooms
 * @author Team Avengers / Kenny Amador
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 * This class represents a room entity within a game. Each room object encapsulates information
 */

public class Rooms implements Serializable {

    //The following variables are the common attribute between all rooms
    //Kenny Amador
    public int roomID;
    public String roomName;
    public String description;
    public ArrayList<Integer> roomExits;
    public ArrayList<String> monstersIncluded;
    public String puzzleIncluded;
    public ArrayList<String> itemsIncluded;
    public boolean hasBeenVisited;
    private ArrayList<Item> roomInventory;
    private boolean shop;
    private ArrayList<Monster> roomMonsters;

    //Constructor and Initialization of attributes
    //Kenny Amador
    public Rooms(int roomID, String roomName, String description, ArrayList<Integer> roomExits, ArrayList<String> monstersIncluded, ArrayList<String> itemsIncluded,String puzzleIncluded, boolean hasBeenVisited,boolean shop){
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.roomExits = roomExits;
        this.monstersIncluded = monstersIncluded;
        this.puzzleIncluded = puzzleIncluded;
        this.itemsIncluded = itemsIncluded;
        this.hasBeenVisited = hasBeenVisited;
        this.roomInventory = new ArrayList<>();
        this.shop = shop;
        this.roomMonsters = new ArrayList<>();
    }

    //Getters and Setters
    //Kenny Amador
    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public void setRoomExits(ArrayList<Integer> roomExits){
        this.roomExits = roomExits;
    }

    public void setMonstersIncluded(ArrayList<String> monstersIncluded){
        this.monstersIncluded = monstersIncluded;
    }

    public void setPuzzleIncluded(String puzzleIncluded){
        this.puzzleIncluded = puzzleIncluded;
    }

    public void setItemsIncluded(ArrayList<String> itemsIncluded){this.itemsIncluded = itemsIncluded;}

    public void setHasBeenVisited(boolean hasBeenVisited){this.hasBeenVisited = hasBeenVisited;}
    public boolean getShop(){return shop;}

    public boolean getHasBeenVisited(){return hasBeenVisited;}

    public int getRoomID(){
        return roomID;
    }

    public String getDescription(){
        return description;
    }

    public String getRoomName(){
        return roomName;
    }

    public ArrayList<Integer> getRoomExits(){
        return roomExits;
    }

    public ArrayList<String> getMonstersIncluded(){
        return monstersIncluded;
    }

    public ArrayList<String> getItemsIncluded(){
        return itemsIncluded;
    }

    public String getPuzzleIncluded(){
        return puzzleIncluded;
    }

    public ArrayList<Item> getRoomInventory() {return roomInventory;}

    public void setRoomInventory(ArrayList<Item> roomInventory) {this.roomInventory = roomInventory;}

    public ArrayList<Monster> getRoomMonsters() {return roomMonsters;}

    public void setRoomMonsters(ArrayList<Monster> roomMonsters) {this.roomMonsters = roomMonsters;}

    //toString method
    @Override
    public String toString(){
        return roomName;
    }

    //Method to read the room file
    //Kenny Amador
        public static void readRooms(String filePath, ArrayList<Rooms> listOfRooms) {
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
                    boolean shop = Boolean.parseBoolean(line[8]);
                    listOfRooms.add(new Rooms(roomID, roomName, roomDesc, connects, monsterID, itemsID, puzzleID, hasBeenVisited,shop));
                }
            } catch (FileNotFoundException fnfe){
                System.out.println("Could not find the correct room file");
            }
        }

        public static ArrayList<String> parseItemID(String id){
            ArrayList<String> itemID = new ArrayList<>();
            String[] line = id.split(",");
            for(String str: line){
                itemID.add(str);
            }
            return itemID;
        }

        //parse roomConnects
        public static ArrayList<Integer> parseExits(String exit){
            ArrayList<Integer> roomConnects = new ArrayList<>();
            String [] line = exit.split(",");
            for(String str:line){
                roomConnects.add(Integer.parseInt(str));
            }
            return roomConnects;
        }

        //ArrayList of monsterID in the rooms
        public static ArrayList<String> parseMonsterID(String monsterID){
            ArrayList<String> roomMonsterID = new ArrayList<>();
            String[] line = monsterID.split(",");
            for(String str:line){
                roomMonsterID.add(str);
            }
            return roomMonsterID;
        }



}//end rooms class
