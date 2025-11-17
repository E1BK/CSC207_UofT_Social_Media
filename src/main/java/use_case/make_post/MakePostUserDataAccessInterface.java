package use_case.make_post;

import entity.User;

public interface MakePostUserDataAccessInterface {

    public void save(User user);
    public User getUserInfo(String username);
}