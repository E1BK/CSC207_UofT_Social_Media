package use_case.login_signup.login;

import entity.Post;
import use_case.make_post.PostViewData;

import java.util.ArrayList;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String email;
    private final ArrayList<PostViewData> posts;
    public LoginOutputData(String username,
                           String password,
                           String bio,
                           String email,
                           ArrayList<PostViewData> posts) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() { return password; }
    public String getBio() { return bio; }
    public String getEmail() { return email; }
    public ArrayList<PostViewData> getPosts() { return posts; }
}
