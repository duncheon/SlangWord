package ui;

import javax.swing.JFrame;

public class Swing {
    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("VTV");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    }
    public static void main(String[] args) {
        createAndShowGUI();
    }
}
