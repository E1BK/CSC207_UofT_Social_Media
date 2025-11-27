package view;

import app.GradientPanel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.login.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private final JPasswordField signupPassword1InputField = new JPasswordField(15);
    private final JPasswordField signupPassword2InputField = new JPasswordField(15);
    private JButton signUp;

    // Tab components
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JPanel loginPanel = new JPanel();
    private final JPanel signupPanel = new JPanel();

    private LoginController loginController;
    private SignupController signupController;

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

        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Sign Up", signupPanel);

        // top panel
        JLabel name = new JLabel("ChatUofT");
        name.setFont(new Font("Helvetica", Font.PLAIN, 30));
        GradientPanel topPanel = new GradientPanel();
        topPanel.add(name);
        topPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // bottom panel
        GradientPanel bottomPanel = new GradientPanel();
        JLabel slogan = new JLabel("Est. 2025");
        slogan.setFont(new Font("Helvetica", Font.PLAIN, 20));
        bottomPanel.add(slogan);
        bottomPanel.setBorder(new EmptyBorder(15, 0, 15, 0));


        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupLoginPanel() {
        loginPanel.setLayout(new BorderLayout());

        JLabel usernamePrompt = new JLabel("Username:");
        usernamePrompt.setFont(new Font("Helvetica", Font.PLAIN, 20));
        JLabel passwordPrompt = new JLabel("Password");
        passwordPrompt.setFont(new Font("Helvetica", Font.PLAIN, 20));

        LabelTextPanel thingy = new LabelTextPanel(usernamePrompt, loginUsernameInputField);
        loginPanel.add(thingy, BorderLayout.NORTH);
        LabelTextPanel thingy2 = new LabelTextPanel(passwordPrompt, loginPasswordInputField);
        loginPanel.add(thingy2, BorderLayout.CENTER);


        logIn = new JButton("Log In");
        loginPanel.add(logIn, BorderLayout.SOUTH);

        addLoginListeners();
        logIn.addActionListener(this);
//        loginPanel.setLayout(new GridLayout(0, 2, 10, 10));
//
//        loginPanel.add(new JLabel("Username:"));
//        loginPanel.add(loginUsernameInputField);
//
//        loginPanel.add(new JLabel("Password:"));
//        loginPanel.add(loginPasswordInputField);
//
//        logIn = new JButton("Log In");
//        loginPanel.add(new JLabel()); // Empty cell for alignment
//        loginPanel.add(logIn);
//
//        addLoginListeners();
//        logIn.addActionListener(this);
    }

    private void setupSignupPanel() {
        signupPanel.setLayout(new GridLayout(0, 2, 10, 10));

        signupPanel.add(new JLabel("Full Name:"));
        signupPanel.add(signupNameInputField);

        signupPanel.add(new JLabel("UofT Email:"));
        signupPanel.add(signupEmailInputField);

        signupPanel.add(new JLabel("Username:"));
        signupPanel.add(signupUsernameInputField);

        signupPanel.add(new JLabel("Password1:"));
        signupPanel.add(signupPassword1InputField);

        signupPanel.add(new JLabel("Password2:"));
        signupPanel.add(signupPassword2InputField);

        signUp = new JButton("Sign Up");
        signupPanel.add(new JLabel());
        signupPanel.add(signUp);

        addSignupListeners();
        signUp.addActionListener(this);
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

        // Signup password1 listener
        signupPassword1InputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSignupPassword(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSignupPassword(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSignupPassword(); }
            private void updateSignupPassword() {
                var state = signupViewModel.getState();
                state.setPassword(new String(signupPassword1InputField.getPassword()));
                signupViewModel.setState(state);
            }
        });

        // Signup password2 listener
        signupPassword2InputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSignupPassword(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSignupPassword(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSignupPassword(); }
            private void updateSignupPassword() {
                var state = signupViewModel.getState();
                state.setRepeatPassword(new String(signupPassword2InputField.getPassword()));
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == logIn) {
            onLogin();
        } else if (evt.getSource() == signUp) {
            onSignup();
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
        String password1 = new String(signupPassword1InputField.getPassword());
        String password2 = new String(signupPassword2InputField.getPassword());

        if (signupController != null) {
            signupController.execute(username, password1, password2, email, name);
        }
    }

    private void clearAllFields() {
        loginUsernameInputField.setText("");
        loginPasswordInputField.setText("");
        signupNameInputField.setText("");
        signupEmailInputField.setText("");
        signupUsernameInputField.setText("");
        signupPassword1InputField.setText("");
        signupPassword2InputField.setText("");
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
        }
        // else: success; presenter has already told ViewManager to show LandingView
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
        signupPassword1InputField.setText("");
        signupPassword2InputField.setText("");
    }

    public String getViewName() {
        return viewName;
    }
}