package use_case.my_profile.profile_change_password;

import entity.Post;
import java.util.ArrayList;

public class MyProfileChangePasswordInputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String email;
    private final String name;
    private final ArrayList<Post> posts;

    public MyProfileChangePasswordInputData(String username,
                                            String password,
                                            String bio,
                                            String email,
                                            String name,
                                            ArrayList<Post> posts) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.name = name;
        this.posts = posts;
    }

    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    String getBio() {
        return bio;
    }
    String getEmail() {
        return email;
    }
    String getName() {
        return name;
    }
    ArrayList<Post> getPosts() {
        return posts;
    }
}
