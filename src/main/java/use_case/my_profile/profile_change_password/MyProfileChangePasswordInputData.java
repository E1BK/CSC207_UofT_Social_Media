package use_case.my_profile.profile_change_password;

import entity.Post;
import java.util.ArrayList;

public class MyProfileChangePasswordInputData {

    private final String username;
    private final String password;
    private final String bio;
    private final String email;
    private final String name;
    private final String passOrBio;
    private final ArrayList<Post> posts;

    public MyProfileChangePasswordInputData(String username,
                                            String password,
                                            String bio,
                                            String email,
                                            String name,
                                            String passOrBio,
                                            ArrayList<Post> posts) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.email = email;
        this.name = name;
        this.posts = posts;
        this.passOrBio = passOrBio;
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
    String getPassOrBio() { return passOrBio; }
    ArrayList<Post> getPosts() {
        return posts;
    }
}
