package use_case.make_post;

public class MakePostOutputData {
    private final PostViewData newPost;

    public MakePostOutputData(PostViewData newPost) {
        this.newPost = newPost;
    }

    public PostViewData getNewPost() {
        return newPost;
    }
}