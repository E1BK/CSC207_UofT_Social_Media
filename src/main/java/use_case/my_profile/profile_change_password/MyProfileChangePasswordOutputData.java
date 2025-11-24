package use_case.my_profile.profile_change_password;

/**
 * Output Data for the Change Password Use Case.
 */
public class MyProfileChangePasswordOutputData {

    private final String username;

    public MyProfileChangePasswordOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
