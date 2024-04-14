public class Monster extends Character{

    private int expDrop;
    private int goldDrop;

    //Constructor and Initialization of attributes
    public Monster(String characterType, String name, String roomID, String description, int health, int attack, int dexterity, int speed, int expDrop, int goldDrop) {
        super(characterType, name, roomID, description, health, attack, dexterity, speed);
        this.expDrop = expDrop;
        this.goldDrop = goldDrop;
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
}
