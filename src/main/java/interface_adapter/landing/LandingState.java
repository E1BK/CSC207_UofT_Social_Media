// hasan
package interface_adapter.landing;

import entity.Post;

import java.util.ArrayList;

/**
 * The State information representing the logged-in user.
 */
public class LandingState {

    private String username = "";
    private String password = "";
    private String newpost_username = "";
    private String newpost_title = "";
    private String newpost_body = "";
    private ArrayList<Post> posts = new ArrayList<Post>();
    private String passwordError = "";
    private String postError = "";

    // the default no-arg constructor:
    public LandingState() {
    }

    // the second constructor:
    public LandingState(LandingState copy) {
        username = copy.username;
        password = copy.password;
        posts = copy.posts;
        newpost_username = copy.newpost_username;
        newpost_title = copy.newpost_title;
        newpost_body = copy.newpost_body;
        passwordError = copy.passwordError;
        postError = copy.postError;
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

    public ArrayList<Post> getPosts() {return posts;}

    public void setPosts(ArrayList<Post> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
    }

    public String getNewpost_title() { return newpost_title; }

    public void setNewpost_title(String newpost_title) { this.newpost_title = newpost_title; }

    public String getNewpost_body() { return newpost_body; }

    public void setNewpost_body(String newpost_body) { this.newpost_body = newpost_body; }

    public String getNewpost_username() {
        return newpost_username;
    }

    public void setNewpost_username(String newpost_username) {
        this.newpost_username = newpost_username;
    }

    public String getpostError() { return postError; }
    public void setpostError(String postError) { this.postError = postError; }

//    public void addNewPost(Post p) {
//        posts.add(p);
//    }

}
