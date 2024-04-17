package Characters;

public class Monster extends Character {

    private int expDrop;
    private int goldDrop;
    private String monsterID;

    //Constructor and Initialization of attributes
    public Monster(String characterType, String name, String description, int health, int attack, int dexterity, int speed, int expDrop, int goldDrop, String monsterID) {
        super(characterType, name,description, health, attack, dexterity, speed);
        this.expDrop = expDrop;
        this.goldDrop = goldDrop;
        this.monsterID = monsterID;
    }

    //Getters and Setters
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
}
