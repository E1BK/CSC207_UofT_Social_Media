package app;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(0x80ffdb), getWidth(), getHeight(), Color.CYAN);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    };
}
