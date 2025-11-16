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
    private ArrayList<Post> posts = new ArrayList<Post>();

    // the default no-arg constructor:
    public LandingState() {
    }

    // the second constructor:
    public LandingState(LandingState copy) {
        username = copy.username;
        password = copy.password;
        posts = copy.posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Post> getPosts() {return posts;}

    public void setPosts(ArrayList<Post> newPosts) {
        posts.clear();
        posts.addAll(newPosts);
    }

//    public void addNewPost(Post p) {
//        posts.add(p);
//    }
}
