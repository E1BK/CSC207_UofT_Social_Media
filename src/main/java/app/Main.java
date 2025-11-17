package app;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        AppBuilder appBuilder = new AppBuilder();

        JFrame application = appBuilder
                .addLandingView()
                .addSearchUserView()
                .addMakePostUseCase()
                .addSearchUserUseCase()
                .addProfileView()
                .addProfileUseCase()
                .addMyProfileView()
                .addMyProfileUseCase()
                .build();

        application.pack();
        application.setSize(1200, 800);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}