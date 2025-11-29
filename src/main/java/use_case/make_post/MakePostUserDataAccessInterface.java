package use_case.make_post;

import entity.User;

public interface MakePostUserDataAccessInterface {

    void save(User user);
    User getUserInfo(String username);
}