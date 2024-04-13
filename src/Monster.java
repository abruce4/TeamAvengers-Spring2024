import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Monster
 * @author Team Avengers
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 *
 * This class represents a monster entity within a game. Each monster object encapsulates information
 */
public class Monster {

    //The following variables are the common attribute between all monsters
    //Lincoln Bruce
    private String name;
    private String RoomID;
    private String description;
    private int health;
    private int attack;
    private int dexterity;
    private int speed;
    private int expDrop;
    private int goldDrop;

    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public Monster(String name, String roomID, String description, int health, int attack, int dexterity, int speed, int expDrop, int goldDrop) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.dexterity = dexterity;
        this.speed = speed;
        this.expDrop = expDrop;
        this.goldDrop = goldDrop;
    }

    //Getters and Setters
    //Lincoln Bruce
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomID() {
        return RoomID;
    }
    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }

    public int getGoldDrop() {
        return goldDrop;
    }

    public void setGoldDrop(int goldDrop) {
        this.goldDrop = goldDrop;
    }

    //Method to read monsters from the file.
    //Lincoln Bruce
    public static void readMonsters(String filePath, ArrayList<Monster> listOfMonsters) {
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
}