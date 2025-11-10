package entity;

import java.util.Map;

public class User {

    private final String username;
    private final String password;
    private final String bio;
    private final Map<String, Post> posts;

    public User(String username, String password, String bio, Map<String, Post> posts){
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.posts = posts;
    }
}

