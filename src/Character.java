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
public class Character {

    //The following variables are the common attribute between all monsters
    //Lincoln Bruce
    private String characterType;
    private String name;
    private String RoomID;
    private String description;
    private int health;
    private int attack;
    private int dexterity;
    private int speed;

    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public Character(String characterType, String name, String roomID, String description, int health, int attack, int dexterity, int speed) {
        this.characterType = characterType;
        this.name = name;
        this.RoomID = roomID;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.dexterity = dexterity;
        this.speed = speed;
    }

    //Getters and Setters
    //Lincoln Bruce
    public String getCharacterType() {
        return characterType;
    }
    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }
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

    //Method to read monsters from the file.
    //Lincoln Bruce
    public static void readMonsters(String filePath, ArrayList<Character> listOfCharacters) {
        try {
            File myCharacters = new File(filePath);
            Scanner myReader = new Scanner(myCharacters);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] characterData = data.split("-");
                String characterType = characterData[0];
                String name = characterData[1];
                String roomID = characterData[2];
                String description = characterData[3];
                int health = Integer.parseInt(characterData[4]);
                int attack = Integer.parseInt(characterData[5]);
                int dexterity = Integer.parseInt(characterData[6]);
                int speed = Integer.parseInt(characterData[7]);

                if (characterType.equalsIgnoreCase("monster")) {
                    int expDrop = Integer.parseInt(characterData[8]);
                    int goldDrop = Integer.parseInt(characterData[9]);
                    Monster character = new Monster(characterType, name, roomID, description, health, attack, dexterity, speed, expDrop, goldDrop);
                    listOfCharacters.add(character);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred with the monsters file.");
        }
    }
}