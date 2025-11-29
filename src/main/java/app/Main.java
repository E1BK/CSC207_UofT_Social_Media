package app;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        AppBuilder appBuilder = new AppBuilder();

        JFrame application = appBuilder

                .addLandingView()
                .addSearchUserView()
                .addProfileView()
                .addProfileUseCase()
                .addMyProfileView()
                .addClubsView()
                .addMyProfileUseCase()
                .addMyProfileChangePasswordUseCase()
                .addMakePostUseCase()
                .addSearchUserUseCase()
                .addLoginSignupView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addPostView()
                .addViewPostUseCase()
                .addClubsUseCase()
                .addLogoutUseCase()
                .build();

        application.pack();
        application.setSize(1200, 800);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}