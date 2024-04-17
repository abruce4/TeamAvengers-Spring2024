package Room;
import Items.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**Class: Rooms
 * @author Team Avengers / Kenny Amador
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 * This class represents a room entity within a game. Each room object encapsulates information
 */

public class Rooms implements Serializable {

    //The following variables are the common attribute between all rooms
    public int roomID;
    public String roomName;
    public String description;
    public ArrayList<Integer> roomExits;
    public ArrayList<String> monstersIncluded;
    public String puzzleIncluded;
    public ArrayList<String> itemsIncluded;
    public boolean hasBeenVisited;
    private ArrayList<Item> roomInventory;

    //Constructor and Initialization of attributes
    public Rooms(int roomID, String roomName, String description, ArrayList<Integer> roomExits, ArrayList<String> monstersIncluded, ArrayList<String> itemsIncluded,String puzzleIncluded, boolean hasBeenVisited){
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.roomExits = roomExits;
        this.monstersIncluded = monstersIncluded;
        this.puzzleIncluded = puzzleIncluded;
        this.itemsIncluded = itemsIncluded;
        this.hasBeenVisited = hasBeenVisited;
        this.roomInventory = new ArrayList<>();
    }

    //Getters and Setters
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

    public void addItem(Item item) {roomInventory.add(item);}

    public void removeItem(Item item) {roomInventory.remove(item);}

    public ArrayList<Item> getRoomInventory() {return roomInventory;}

    public void setRoomInventory(ArrayList<Item> roomInventory) {this.roomInventory = roomInventory;}

    //Method to read the room file




}//end rooms class
