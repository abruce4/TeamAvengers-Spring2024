package Characters;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Character
 * @author Team Avengers / Lincoln Bruce
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 *
 * This class is the parent class for all characters in the game
 */
public class Character {

    //The following variables are the common attribute between all monsters
    //Lincoln Bruce
    private String characterType;
    private String name;
    private String description;
    private int health;
    private int attack;
    private int dexterity;
    private int speed;

    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public Character(String characterType, String name, String description, int health, int attack, int dexterity, int speed) {
        this.characterType = characterType;
        this.name = name;
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

    //Method to read characters from file
    public static void readCharacters(String filePath, ArrayList<Character> listOfCharacters) {
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
            System.out.println("An error occurred with the characters file.");
        }
    }
}