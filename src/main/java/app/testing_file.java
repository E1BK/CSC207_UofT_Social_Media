// hasan
// This file is only made to visualize any demos/ tests.

package app;


import javax.swing.*;

public class testing_file {

    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addLandingView()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
