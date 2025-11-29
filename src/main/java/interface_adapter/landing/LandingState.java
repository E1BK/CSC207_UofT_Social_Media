// hasan
package interface_adapter.landing;

import use_case.make_post.PostViewData;

import java.util.ArrayList;

/**
 * The State information representing the logged-in user.
 */
public class LandingState {

    private String username = "";
    private String password = "";
    private ArrayList<PostViewData> posts = new ArrayList<PostViewData>();
    private String passwordError = "";
    private boolean isInitialized = false;


    // the default no-arg constructor:
    public LandingState() {
    }

    // the second constructor:
    public LandingState(LandingState copy) {
        username = copy.username;
        password = copy.password;
        posts = copy.posts;
        passwordError = copy.passwordError;
        isInitialized = copy.isInitialized;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public ArrayList<PostViewData> getPosts() {return posts;}

    public void setPosts(ArrayList<PostViewData> posts) {this.posts = posts;}

    public void addPost(PostViewData post) {
        posts.addFirst(post);
    }

    public boolean isInitialized() {return isInitialized;}

    public void setInitialized(boolean isInitialized) { this.isInitialized = isInitialized; }

}
