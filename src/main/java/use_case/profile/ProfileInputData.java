package use_case.profile;

import java.util.ArrayList;
import java.util.Map;

public class ProfileInputData {
    private final String username;
    private final String email;
    private final String bio;
    private final ArrayList<Map> posts;


    public ProfileInputData(String username, String email, String bio, ArrayList<Map> posts) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public ArrayList<Map> getPosts() {return posts; }
}
