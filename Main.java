import java.io.IOException;

import menu.Menu;

public class Main {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Menu mainProgram = new Menu(); 
        mainProgram.launchSetup();
        mainProgram.printMenu();
    }
}