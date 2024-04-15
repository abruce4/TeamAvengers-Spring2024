import java.util.ArrayList;

public class Rooms{
    public String roomID;
    public String roomName;
    public String description;
    public ArrayList<Integer> roomExits;
    public ArrayList<String> monstersIncluded;
    public String puzzleIncluded;
    public ArrayList<String> itemsIncluded;

    public Rooms(String roomID, String roomName, String description, ArrayList<Integer> roomExits, ArrayList<String> monstersIncluded, String puzzleIncluded, ArrayList<String> itemsIncluded){
        this.roomID = roomID;
        this.roomName = roomName;
        this.description = description;
        this.roomExits = roomExits;
        this.monstersIncluded = monstersIncluded;
        this.puzzleIncluded = puzzleIncluded;
        this.itemsIncluded = itemsIncluded;
    }

    public void setRoomID(String roomID){
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

    public String getRoomID(){
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

}//end rooms class
