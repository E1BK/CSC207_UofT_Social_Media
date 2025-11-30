package use_case.my_profile;

import use_case.make_post.PostViewData;

import java.util.ArrayList;

public class MyProfileOutputData {
    private final String username;
    private final String password;
    private final String email;
    private final String bio;
    private final ArrayList<PostViewData> posts;

    public MyProfileOutputData(String username, String password, String email, String bio, ArrayList<PostViewData> posts) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getBio() { return bio; }
    public ArrayList<PostViewData> getPosts() { return posts; }
}
