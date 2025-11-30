package interface_adapter.profile;

import java.util.ArrayList;
import java.util.Map;

import use_case.make_post.PostViewData;

public class ProfileState {
    private String username = "";
    private String bio = "";
    private String email = "";
    private String name = "";
    private ArrayList<PostViewData> posts = new  ArrayList<>();

    public ProfileState(ProfileState copy) {
        username = copy.username;
        bio = copy.bio;
        email = copy.email;
        name = copy.name;
        posts = copy.posts;
    }

    public ProfileState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPosts(ArrayList<PostViewData> posts) {
        this.posts = posts;
    }
    public ArrayList<PostViewData> getPosts() {
        return posts;
    }

    public void logout() {
        username = "";
        bio = "";
        email = "";
        name = "";
        posts = new ArrayList<>();
    }
}
