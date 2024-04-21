import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

//Thuy Vy Pham
public class Spells implements Serializable {
    private String name;
    private String description;
    private int effects;
    private int manaCost;

    private int levelNeeded;

    public Spells(String name, String description, int effects, int manaCost,int levelNeeded) {
        this.name = name;
        this.description = description;
        this.effects = effects;
        this.manaCost = manaCost;
        this.levelNeeded = levelNeeded;
    }


    public String getName() {
        return name;
    }
    public int getEffects(){return effects;}
    public int getLevelNeeded(){return levelNeeded;}
    public int getManaCost(){return manaCost;}

    public void setManaCost(int manaCost){this.manaCost = manaCost;}
    public void setEffects(int effects){this.effects = effects;}
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //Read Spells from file
    //Thuy Vy Pham
    public static void readSpells(String filePath, ArrayList<Spells> listOfSpells) {
        try{
            File mySpells = new File(filePath);
            Scanner myReader = new Scanner(mySpells);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.split("-");
                String name = line[0];
                String description = line[1];
                int levelNeed = Integer.parseInt(line[2]);
                int effects = Integer.parseInt(line[3]);
                int manaCost = Integer.parseInt(line[4]);
                Spells spells = new Spells(name, description,effects,manaCost,levelNeed);
                listOfSpells.add(spells);

            }
        } catch(Exception e){
            System.out.println("An error occurred with the spell file.");
        }
    }
}