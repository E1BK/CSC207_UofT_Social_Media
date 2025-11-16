package use_case.view_post;

import entity.Post;
import entity.Comment;

import java.util.List;

public class ViewPostOutputData {

    private final Post post;
    private final List<Comment> comments;

    public ViewPostOutputData(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }

    public Post getPost() {
        return post;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
