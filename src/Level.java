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
}

