import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("save.bin"); // this is the save/load feature that i was able to do with the help of ACE
        Game game;
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to load up the game?");
        String answer = scan.next();
        if(answer.equalsIgnoreCase("yes")) {
            try {
                InputStream is = new FileInputStream(f);
                ObjectInputStream fileIn = new ObjectInputStream(is);
                game = (Game) fileIn.readObject();
                fileIn.close();
                System.out.println("FILE READ SUCCESSFULLY");
            } catch (IOException e) {
                System.out.println("Save File not found, starting from beginning.");
                game = new Game();
            } catch (ClassNotFoundException e) {
                System.out.println("Save file corrupted");
                game = new Game();
            }
        }
        game = new Game();
        game.RunGame();
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream fileOut = new ObjectOutputStream(fos);
            fileOut.writeObject(game);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
