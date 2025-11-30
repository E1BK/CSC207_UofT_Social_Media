package use_case.my_profile.profile_change_password;

public class MyProfileChangePasswordInputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String email;
    private final String name;
    private final String passOrBio;

    public MyProfileChangePasswordInputData(String username,
                                            String password,
                                            String bio,
                                            String email,
                                            String name,
                                            String passOrBio) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.name = name;
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
    String getEmail() {
        return email;
    }
    String getName() {
        return name;
    }
    String getPassOrBio() { return passOrBio; }
}
