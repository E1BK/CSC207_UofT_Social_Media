package entity;

import java.util.ArrayList;

public class User {

    private final String username;
    private final String password;
    private final String bio;
    private final String email;
    private final String name;
    private final ArrayList<Post> posts;

    public User(String username, String password, String bio, String email, String name, ArrayList<Post> posts) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.name = name;
        this.posts = posts;
    }

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.bio = "";
        this.email = email;
        this.name = name;
        this.posts = new ArrayList<Post>();
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getName() { return name; }

    public void addPost(Post post) {
        posts.add(post);
    }
}

