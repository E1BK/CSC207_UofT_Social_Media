package app;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();
        for (String f : fonts) {
            System.out.println(f);
        }


        AppBuilder appBuilder = new AppBuilder();

        JFrame application = appBuilder
                .addLandingView()
                .build();

        application.pack();
        application.setSize(1200, 800);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}