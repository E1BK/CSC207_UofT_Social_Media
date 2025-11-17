package app;

import javax.swing.*;

import View.SearchView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("UofT Social Media");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


            frame.setContentPane(new SearchView());

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}