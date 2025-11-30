package use_case.profile;

import entity.User;

public interface ProfileUserDataAccessInterface {

    public void save(User user);
    public User getUserInfo(String username);
}
