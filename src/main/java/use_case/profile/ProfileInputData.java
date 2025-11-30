package use_case.profile;

public class ProfileInputData {
    private final String username;
    private final String email;
    private final String bio;
    private final String user;


    public ProfileInputData(String username, String email, String bio, String user) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.user = user;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public String getUser() { return user; }
}
