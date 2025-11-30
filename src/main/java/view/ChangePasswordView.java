package view;

import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.change_password.ChangePasswordController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangePasswordView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "change password";
    private ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordController changePasswordController = null;

    // Password fields
    private final JPasswordField currentPasswordField;
    private final JPasswordField newPasswordField;
    private final JPasswordField confirmPasswordField;

    // Buttons
    private final JButton changePasswordButton;
    private final JButton backButton;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        changePasswordViewModel.addPropertyChangeListener(this);

        // Top panel
        JLabel title = new JLabel("Change Password");
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        JPanel topPanel = new JPanel();
        topPanel.add(title);
        topPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        // Current password field
        JLabel currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        currentPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        currentPasswordField = new JPasswordField(20);
        currentPasswordField.setFont(new Font("Helvetica", Font.PLAIN, 16));
        currentPasswordField.setMaximumSize(new Dimension(300, 35));
        currentPasswordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // New password field
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        newPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        newPasswordField = new JPasswordField(20);
        newPasswordField.setFont(new Font("Helvetica", Font.PLAIN, 16));
        newPasswordField.setMaximumSize(new Dimension(300, 35));
        newPasswordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Confirm password field
        JLabel confirmPasswordLabel = new JLabel("Confirm New Password:");
        confirmPasswordLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        confirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setFont(new Font("Helvetica", Font.PLAIN, 16));
        confirmPasswordField.setMaximumSize(new Dimension(300, 35));
        confirmPasswordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        changePasswordButton = new JButton(ChangePasswordViewModel.CHANGE_PASSWORD_BUTTON_LABEL);
        changePasswordButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        changePasswordButton.setMargin(new Insets(10, 20, 10, 20));

        backButton = new JButton(ChangePasswordViewModel.BACK_BUTTON_LABEL);
        backButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        backButton.setMargin(new Insets(10, 20, 10, 20));

        buttonsPanel.add(changePasswordButton);
        buttonsPanel.add(backButton);

        // Add components to main panel with spacing
        mainPanel.add(currentPasswordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(currentPasswordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(newPasswordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(newPasswordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        mainPanel.add(confirmPasswordLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(confirmPasswordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        mainPanel.add(buttonsPanel);

        // Set up main layout
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        // Action listeners
        changePasswordButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(changePasswordButton)) {
                            System.out.println("CLICKED 'CHANGE PASSWORD'!");
                            final ChangePasswordState state = changePasswordViewModel.getState();
                            if (changePasswordController != null) {
                                changePasswordController.execute(
                                        state.getUsername(),
                                        new String(currentPasswordField.getPassword()),
                                        new String(newPasswordField.getPassword()),
                                        new String(confirmPasswordField.getPassword())
                                );
                            }
                        }
                    }
                }
        );

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            System.out.println("CLICKED 'BACK'!");
                            // This would typically switch back to the previous view
                            // You might need a controller method for this
                            if (changePasswordController != null) {
                                // changePasswordController.switchToPreviousView();
                            }
                        }
                    }
                }
        );

        // Document Listeners
        setupDocumentListeners();
    }

    private void setupDocumentListeners() {
        // Current password listener
        currentPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                final ChangePasswordState state = changePasswordViewModel.getState();
                state.setCurrentPassword(new String(currentPasswordField.getPassword()));
                changePasswordViewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) { updateState(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateState(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateState(); }
        });

        // New password listener
        newPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                final ChangePasswordState state = changePasswordViewModel.getState();
                state.setNewPassword(new String(newPasswordField.getPassword()));
                changePasswordViewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) { updateState(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateState(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateState(); }
        });

        // Confirm password listener
        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            private void updateState() {
                final ChangePasswordState state = changePasswordViewModel.getState();
                state.setConfirmPassword(new String(confirmPasswordField.getPassword()));
                changePasswordViewModel.setState(state);
            }

            @Override
            public void insertUpdate(DocumentEvent e) { updateState(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateState(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateState(); }
        });
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
        // Update UI based on state changes if needed
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
            state.setError(null); // Clear error after displaying
            changePasswordViewModel.setState(state);
        } else if (state.isPasswordChanged()) {
            JOptionPane.showMessageDialog(this, "Password changed successfully!");
            currentPasswordField.setText("");
            newPasswordField.setText("");
            confirmPasswordField.setText("");
            state.setPasswordChanged(false);
            changePasswordViewModel.setState(state);
        }
    }

    public void setChangePasswordController(ChangePasswordController controller) {
        this.changePasswordController = controller;
    }
}