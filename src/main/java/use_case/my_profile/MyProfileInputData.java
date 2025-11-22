package use_case.my_profile;

import entity.Post;
import java.util.ArrayList;

public class MyProfileInputData{
    private final String username;
    private final String email;
    private final String bio;
    private final ArrayList<Post> posts;


    public MyProfileInputData(String username, String email, String bio, ArrayList<Post> posts) {
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.posts = posts;
    }

    public String getUsername(){ return username; }
    public String getEmail(){ return email; }
    public String getBio(){ return bio; }
    public ArrayList<Post> getPosts(){ return posts; }
}
