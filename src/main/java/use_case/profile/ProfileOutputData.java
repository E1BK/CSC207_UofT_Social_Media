package use_case.profile;

import use_case.make_post.PostViewData;
import java.util.ArrayList;

public class ProfileOutputData {
    private final String username;
    private final String email;
    private final String bio;
    private final ArrayList<PostViewData> posts;
    private final String user;

    public ProfileOutputData(String username, String email, String bio, ArrayList<PostViewData> posts, String user) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
        this.user = user;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public ArrayList<PostViewData> getPosts() { return posts; }
    public String getUser() {return user;}
}
