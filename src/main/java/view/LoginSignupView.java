package view;

import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.login.LoginController;
import interface_adapter.logout.LogoutController;
import interface_adapter.change_password.ChangePasswordController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginSignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "login signup";

    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    // Login components
    private final JTextField loginUsernameInputField = new JTextField(15);
    private final JPasswordField loginPasswordInputField = new JPasswordField(15);
    private JButton logIn;

    // Signup components
    private final JTextField signupNameInputField = new JTextField(15);
    private final JTextField signupEmailInputField = new JTextField(15);
    private final JTextField signupUsernameInputField = new JTextField(15);
    private final JPasswordField signupPasswordInputField = new JPasswordField(15);
    private JButton signUp;

    // Logout & Change Password components (shown when logged in)
    private final JPanel loggedInPanel = new JPanel();
    private final JLabel welcomeLabel = new JLabel("Welcome!");
    private final JButton changePasswordButton = new JButton("Change Password");
    private final JButton logoutButton = new JButton("Logout");
    private final JPasswordField currentPasswordField = new JPasswordField(15);
    private final JPasswordField newPasswordField = new JPasswordField(15);
    private final JPasswordField confirmPasswordField = new JPasswordField(15);
    private final JButton submitPasswordChange = new JButton("Change Password");

    // Tab components
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JPanel loginPanel = new JPanel();
    private final JPanel signupPanel = new JPanel();

    private LoginController loginController;
    private SignupController signupController;
    private LogoutController logoutController;
    private ChangePasswordController changePasswordController;

    private boolean isLoggedIn = false;
    private String currentUsername = "";

    public LoginSignupView(LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;

        // Create buttons internally instead of receiving them as parameters
        this.logIn = new JButton("Log In");
        this.signUp = new JButton("Sign Up");

        this.loginViewModel.addPropertyChangeListener(this);
        this.signupViewModel.addPropertyChangeListener(this);

        // Continue with the rest of your setup...
        setupLoginPanel();
        setupSignupPanel();
        setupLoggedInPanel();

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Sign Up", signupPanel);

        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private void setupLoginPanel() {
        loginPanel.setLayout(new GridLayout(0, 2, 10, 10));

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUsernameInputField);

        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPasswordInputField);

        logIn = new JButton("Log In");
        loginPanel.add(new JLabel()); // Empty cell for alignment
        loginPanel.add(logIn);

        addLoginListeners();
        logIn.addActionListener(this);
    }

    private void setupSignupPanel() {
        signupPanel.setLayout(new GridLayout(0, 2, 10, 10));

        signupPanel.add(new JLabel("Full Name:"));
        signupPanel.add(signupNameInputField);

        signupPanel.add(new JLabel("UofT Email:"));
        signupPanel.add(signupEmailInputField);

        signupPanel.add(new JLabel("Username:"));
        signupPanel.add(signupUsernameInputField);

        signupPanel.add(new JLabel("Password:"));
        signupPanel.add(signupPasswordInputField);

        signUp = new JButton("Sign Up");
        signupPanel.add(new JLabel());
        signupPanel.add(signUp);

        addSignupListeners();
        signUp.addActionListener(this);
    }

    private void setupLoggedInPanel() {
        loggedInPanel.setLayout(new GridLayout(0, 2, 10, 10));

        loggedInPanel.add(welcomeLabel);
        loggedInPanel.add(new JLabel()); // Empty cell

        // Change Password section
        loggedInPanel.add(new JLabel("Current Password:"));
        loggedInPanel.add(currentPasswordField);

        loggedInPanel.add(new JLabel("New Password:"));
        loggedInPanel.add(newPasswordField);

        loggedInPanel.add(new JLabel("Confirm New Password:"));
        loggedInPanel.add(confirmPasswordField);

        loggedInPanel.add(submitPasswordChange);
        loggedInPanel.add(new JLabel()); // Empty cell

        // Logout button
        loggedInPanel.add(new JLabel());
        loggedInPanel.add(logoutButton);

        submitPasswordChange.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    private void addLoginListeners() {
        // Login username listener
        loginUsernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateLoginUsername(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateLoginUsername(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateLoginUsername(); }
            private void updateLoginUsername() {
                var state = loginViewModel.getState();
                state.setUsername(loginUsernameInputField.getText());
                loginViewModel.setState(state);
            }
        });

        // Login password listener
        loginPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateLoginPassword(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateLoginPassword(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateLoginPassword(); }
            private void updateLoginPassword() {
                var state = loginViewModel.getState();
                state.setPassword(new String(loginPasswordInputField.getPassword()));
                loginViewModel.setState(state);
            }
        });
    }

    private void addSignupListeners() {
        // Signup name listener
        signupNameInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSignupName(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSignupName(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSignupName(); }
            private void updateSignupName() {
                var state = signupViewModel.getState();
                state.setName(signupNameInputField.getText());
                signupViewModel.setState(state);
            }
        });

        // Signup email listener
        signupEmailInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSignupEmail(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSignupEmail(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSignupEmail(); }
            private void updateSignupEmail() {
                var state = signupViewModel.getState();
                state.setEmail(signupEmailInputField.getText());
                signupViewModel.setState(state);
            }
        });

        // Signup username listener
        signupUsernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSignupUsername(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSignupUsername(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSignupUsername(); }
            private void updateSignupUsername() {
                var state = signupViewModel.getState();
                state.setUsername(signupUsernameInputField.getText());
                signupViewModel.setState(state);
            }
        });

        // Signup password listener
        signupPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSignupPassword(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSignupPassword(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSignupPassword(); }
            private void updateSignupPassword() {
                var state = signupViewModel.getState();
                state.setPassword(new String(signupPasswordInputField.getPassword()));
                signupViewModel.setState(state);
            }
        });
    }

    public void setLoginController(LoginController controller) {
        this.loginController = controller;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }

    public void setLogoutController(LogoutController controller) {
        this.logoutController = controller;
    }

    public void setChangePasswordController(ChangePasswordController controller) {
        this.changePasswordController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == logIn) {
            onLogin();
        } else if (evt.getSource() == signUp) {
            onSignup();
        } else if (evt.getSource() == logoutButton) {
            onLogout();
        } else if (evt.getSource() == submitPasswordChange) {
            onChangePassword();
        }
    }

    private void onLogin() {
        String username = loginUsernameInputField.getText();
        String password = new String(loginPasswordInputField.getPassword());

        if (loginController != null) {
            loginController.execute(username, password);
        }
    }

    private void onSignup() {
        String name = signupNameInputField.getText();
        String email = signupEmailInputField.getText();
        String username = signupUsernameInputField.getText();
        String password = new String(signupPasswordInputField.getPassword());

        if (signupController != null) {
            signupController.execute(name, email, username, password, "");
        }
    }

    private void onLogout() {
        if (logoutController != null) {
            logoutController.execute();
            switchToLoginSignupView();
        }
    }

    private void onChangePassword() {
        String currentPassword = new String(currentPasswordField.getPassword());
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (changePasswordController != null && !currentUsername.isEmpty()) {
            changePasswordController.execute(
                    currentUsername,                // username
                    currentPassword,                // oldPassword (current password)
                    newPassword,                    // newPassword
                    confirmPassword                 // confirmPassword
            );
        } else {
            JOptionPane.showMessageDialog(this, "Please log in first.");
        }
    }

    private void switchToLoggedInView(String username) {
        this.isLoggedIn = true;
        this.currentUsername = username;
        welcomeLabel.setText("Welcome, " + username + "!");

        // Replace tabs with logged in panel
        tabbedPane.removeAll();
        tabbedPane.addTab("Account", loggedInPanel);

        // Clear password fields
        currentPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    private void switchToLoginSignupView() {
        this.isLoggedIn = false;
        this.currentUsername = "";

        // Restore login/signup tabs
        tabbedPane.removeAll();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Sign Up", signupPanel);

        // Clear all fields
        clearAllFields();
    }

    private void clearAllFields() {
        loginUsernameInputField.setText("");
        loginPasswordInputField.setText("");
        signupNameInputField.setText("");
        signupEmailInputField.setText("");
        signupUsernameInputField.setText("");
        signupPasswordInputField.setText("");
        currentPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == loginViewModel) {
            handleLoginStateChange();
        } else if (evt.getSource() == signupViewModel) {
            handleSignupStateChange();
        }
    }

    private void handleLoginStateChange() {
        var state = loginViewModel.getState();

        if (state.getLoginError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoginError());
            state.setLoginError(null);
            loginViewModel.setState(state);
        } else {
            // Login successful - switch to logged in view
            switchToLoggedInView(state.getUsername());
        }
    }

    private void handleSignupStateChange() {
        var state = signupViewModel.getState();

        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
            state.setUsernameError(null);
            signupViewModel.setState(state);
        } else if (state.getEmailError() != null) {
            JOptionPane.showMessageDialog(this, state.getEmailError());
            state.setEmailError(null);
            signupViewModel.setState(state);
        } else if (state.getNameError() != null) {
            JOptionPane.showMessageDialog(this, state.getNameError());
            state.setNameError(null);
            signupViewModel.setState(state);
        } else if (state.getPasswordError() != null) {
            JOptionPane.showMessageDialog(this, state.getPasswordError());
            state.setPasswordError(null);
            signupViewModel.setState(state);
        } else {
            // Signup successful - show success message and switch to log in tab
            JOptionPane.showMessageDialog(this, "Signup successful! Please log in.");
            tabbedPane.setSelectedIndex(0); // Switch to log in tab
            clearSignupForm();
        }
    }

    private void clearSignupForm() {
        signupNameInputField.setText("");
        signupEmailInputField.setText("");
        signupUsernameInputField.setText("");
        signupPasswordInputField.setText("");
    }

    public String getViewName() {
        return viewName;
    }
}