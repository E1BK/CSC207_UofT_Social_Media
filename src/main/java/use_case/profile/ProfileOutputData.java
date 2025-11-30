package use_case.profile;

import use_case.make_post.PostViewData;
import java.util.ArrayList;

public class ProfileOutputData {
    private final String username;
    private final String email;
    private final String bio;
    private final ArrayList<PostViewData> posts;

    public ProfileOutputData(String username, String email, String bio, ArrayList<PostViewData> posts) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public ArrayList<PostViewData> getPosts() { return posts; }
}
