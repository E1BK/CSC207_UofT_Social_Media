package entity;

import java.util.Map;

public class UserFactory{
    public User create(String username, String password, String bio, Map<String, Post> posts){ return new User(username, password, bio, posts);}
}