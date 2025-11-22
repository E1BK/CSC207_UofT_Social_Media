package interface_adapter.my_profile;

import entity.Post;

import java.util.ArrayList;

public class MyProfileState {
    private String username = "";
    private String password = "";
    private String bio = "";
    private String email = "";
    private ArrayList<Post> posts = new  ArrayList<Post>();

    public MyProfileState(MyProfileState copy) {
        username = copy.username;
        password = copy.password;
        bio = copy.bio;
        email = copy.email;
        posts = copy.posts;
    }

    public MyProfileState() {

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

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}
