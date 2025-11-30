package use_case.my_profile;

import use_case.make_post.PostViewData;

import java.util.ArrayList;

public class MyProfileOutputData {
    private ArrayList<PostViewData> posts;
    public MyProfileOutputData(ArrayList<PostViewData> posts) {
        this.posts = posts;
    }

    public ArrayList<PostViewData> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<PostViewData> posts) {
        this.posts = posts;
    }
}
