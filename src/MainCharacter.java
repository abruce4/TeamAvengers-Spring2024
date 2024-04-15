public class MainCharacter extends Character{

    private int mana;
    private int defense;

    //Constructor and Initialization of attributes
    //Lincoln Bruce
    public MainCharacter(String characterType, String name, String roomID, String description, int health, int attack, int dexterity, int speed, int mana, int defense) {
        super(characterType, name, roomID, description, health, attack, dexterity, speed);
        this.mana = mana;
        this.defense = defense;
    }

    //Getters and Setters
    //Lincoln Bruce
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
}
