import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**Class: Monster
 * @author Team Avengers / Lincoln Bruce
 * @version 1.0
 * Course: ITEC 3860 Spring 2024
 * Written: Apr 9, 2024
 * This class represents a monster entity within a game. Each monster object encapsulates information
 */
public class Monster implements Serializable {

    //The following variables are the common attribute between all monsters
    //Lincoln Bruce
    private String name;
    private String description;
    private int health;
    private int attack;
    private int dexterity;
    private int speed;
    private int expDrop;
    private int goldDrop;
    private String monsterID;
    private int hitRate;
    private int AvoidRate;

    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public Monster(String name, String description, int health, int attack, int dexterity, int speed, int expDrop, int goldDrop, String monsterID) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.dexterity = dexterity;
        this.speed = speed;
        this.expDrop = expDrop;
        this.goldDrop = goldDrop;
        this.monsterID = monsterID;
        this.hitRate = 4 * dexterity;
        this.AvoidRate = 4 * speed;

    }

    //Getters and Setters
    //Lincoln Bruce
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

    public String getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(String monsterID) {
        this.monsterID = monsterID;
    }

    public int getHitRate() {
        return hitRate;
    }

    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    public int getAvoidRate() {
        return AvoidRate;
    }

    public void setAvoidRate(int AvoidRate) {
        this.AvoidRate = AvoidRate;
    }

    //toString method
    @Override
    public String toString() {
        return name;
    }

    //Method to read characters from file
    //Lincoln Bruce
    public static void readMonsters(String filePath, ArrayList<Monster> listOfMonsters) {
        try {
            File myCharacters = new File(filePath);
            Scanner myReader = new Scanner(myCharacters);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] MonsterData = data.split("-");
                String name = MonsterData[0];
                String description = MonsterData[1];
                int health = Integer.parseInt(MonsterData[2]);
                int attack = Integer.parseInt(MonsterData[3]);
                int dexterity = Integer.parseInt(MonsterData[4]);
                int speed = Integer.parseInt(MonsterData[5]);
                int expDrop = Integer.parseInt(MonsterData[6]);
                int goldDrop = Integer.parseInt(MonsterData[7]);
                String monsterID = MonsterData[8];
                Monster monster = new Monster(name, description, health, attack, dexterity, speed, expDrop, goldDrop, monsterID);
                listOfMonsters.add(monster);


            }
        } catch (Exception e) {
            System.out.println("An error occurred with the characters file.");
        }
    }
}