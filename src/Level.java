import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Thuy Vy Pham
public class Level {
    private int level;
    private int maxHP;
    private int maxMana;
    private int magic;
    private int speed;
    private int dexterity;
    private int defense;
    private int expToNextLevel;

    public Level(int level, int maxHP, int maxMana, int magic, int speed, int dexterity, int defense, int expToNextLevel) {
        this.level = level;
        this.maxHP = maxHP;
        this.maxMana = maxMana;
        this.magic = magic;
        this.speed = speed;
        this.dexterity = dexterity;
        this.defense = defense;
        this.expToNextLevel = expToNextLevel;
    }

    public int getLevel() { return level; }
    public int getMaxHP() { return maxHP; }
    public int getMaxMana() { return maxMana; }
    public int getMagic() { return magic; }
    public int getSpeed() { return speed; }
    public int getDexterity() { return dexterity; }
    public int getDefense() { return defense; }
    public int getExpToNextLevel() { return expToNextLevel; }

    //Method to read levels from file
    //Thuy VY Pham
    public static void readLevels(String filePath, ArrayList<Level> listOfLevels) {
        try {
            File myLevels = new File(filePath);
            Scanner myReader = new Scanner(myLevels);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] levelData = data.split("-");
                int level = Integer.parseInt(levelData[0]);
                int maxHP = Integer.parseInt(levelData[1]);
                int maxMana = Integer.parseInt(levelData[2]);
                int magic = Integer.parseInt(levelData[3]);
                int speed = Integer.parseInt(levelData[4]);
                int dexterity = Integer.parseInt(levelData[5]);
                int defense = Integer.parseInt(levelData[6]);
                int expToNextLevel = Integer.parseInt(levelData[7]);
                listOfLevels.add(new Level(level, maxHP, maxMana, magic, speed, dexterity, defense, expToNextLevel));
            }
        } catch (Exception e) {
            System.out.println("An error occurred with the level file.");
        }
    }
}

