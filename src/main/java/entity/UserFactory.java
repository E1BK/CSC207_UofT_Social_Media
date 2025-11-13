package entity;

import java.util.ArrayList;

public class UserFactory{
    public User create(String username, String password, String bio, String email, ArrayList<Post> posts){ return new User(username, password, bio, email, posts);}
}