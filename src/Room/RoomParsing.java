package Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomParsing {
    private static ArrayList<Rooms> listOfRooms;
    public ArrayList<Rooms> readRooms(String filePath) {
        Scanner infile;
        listOfRooms = new ArrayList<>();
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
