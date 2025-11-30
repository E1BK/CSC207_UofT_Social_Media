package use_case.landing;

import use_case.make_post.PostViewData;

import java.util.ArrayList;

public class LandingOutputData {
    private final ArrayList<PostViewData> posts;
    public LandingOutputData(ArrayList<PostViewData> posts) {
        this.posts = posts;
    }

    public ArrayList<PostViewData> getPosts() {
        return posts;
    }
}
