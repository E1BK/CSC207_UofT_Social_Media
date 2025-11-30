package use_case.profile;

import java.util.ArrayList;
import java.util.Map;

public class ProfileInputData {
    private final String username;
    private final String email;
    private final String bio;


    public ProfileInputData(String username, String email, String bio) {
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
}
