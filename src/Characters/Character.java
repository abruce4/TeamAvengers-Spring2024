package Characters;

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

}