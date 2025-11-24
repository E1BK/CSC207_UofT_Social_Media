package interface_adapter.profile;

import entity.Comment;
import entity.Post;

import java.util.ArrayList;
import java.util.List;

public class ProfileState {
    private String username = "";
    private String password = "";
    private String bio = "";
    private String utorID = "";

    public ProfileState(ProfileState copy) {
        username = copy.username;

    }

    public ProfileState() {

    }

    public String getUsername() { return username; }
    public String getBio() { return bio; }
    public List<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(new Post(01,
//                "SorEgo",
//                "My First Post",
//                "This is my first post, loser.",
//                "11/18/25",
//                new ArrayList<Comment>()));
//
//        posts.add(new Post(02,
//                "SorEgo",
//                "My Second Post",
//                "I'm a pro at this!",
//                "11/18/25",
//                new ArrayList<Comment>()));
//
//        posts.add(new Post(03,
//                "SorEgo",
//                "My Third Post",
//                ":]",
//                "11/18/25",
//                new ArrayList<Comment>()));

        return posts;
    }
}
