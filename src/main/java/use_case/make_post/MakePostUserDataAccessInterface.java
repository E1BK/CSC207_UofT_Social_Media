package use_case.make_post;

import entity.Post;
import entity.User;

public interface MakePostUserDataAccessInterface {

    public void save(User user);
    public User getUserInfo(String username);
    public Post getPost(String username, int postId);
}