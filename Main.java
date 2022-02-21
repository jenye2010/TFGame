import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;

public class Main {

    JButton b1, b2, b3, b4, b5;

    public static void main(String[] args) {
        Main program = new Main();
        SwingUtilities.invokeLater(program::run);
    }

    private void run() {
        Questions qu = new Questions();
        InsertQuestions iq = new InsertQuestions();
        JTabbedPane tabs = new JTabbedPane();
        JFrame z = new JFrame();

        z.setLayout(new BorderLayout());
        z.setDefaultCloseOperation(z.EXIT_ON_CLOSE);
        // do things
        tabs.addTab("Game", qu.game());// Playing
        tabs.addTab("Insert", iq.insert());// Insert New Questions
        // tabs.addTab("User", qu.game());//Check User Information / Register / Import
        // User Information

        z.getContentPane().add(tabs);
        // generating
        z.pack();
        z.setLocationByPlatform(true);
        z.setVisible(true);
        z.setSize(600, 500);
    }
}
