package View;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {

    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }

    LabelTextPanel(JLabel label, JTextField textField, JButton button) {
        this.add(label);
        this.add(textField);
        this.add(button);
    }

}
