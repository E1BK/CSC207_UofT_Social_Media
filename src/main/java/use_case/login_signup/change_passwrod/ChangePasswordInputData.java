package use_case.login_signup.change_passwrod;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;
    private final String email;
    private final String name;

    public ChangePasswordInputData(String password, String username, String email, String name) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.name = name;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getEmail() {return email;}

    String getName() {return name;}

}
