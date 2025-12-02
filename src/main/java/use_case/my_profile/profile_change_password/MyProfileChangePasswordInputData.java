package use_case.my_profile.profile_change_password;

public class MyProfileChangePasswordInputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String passOrBio;

    public MyProfileChangePasswordInputData(String username,
                                            String password,
                                            String bio,
                                            String passOrBio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.passOrBio = passOrBio;
    }

    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    String getBio() {
        return bio;
    }
    String getPassOrBio() { return passOrBio; }
}
