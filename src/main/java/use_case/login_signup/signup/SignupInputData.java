package use_case.login_signup.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String email;
    private final String name;

    public SignupInputData(String username, String password, String repeatPassword, String email, String name) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.name = name;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    String getEmail() {
        return email;
    }

    String getName() {
        return name;
    }
}
