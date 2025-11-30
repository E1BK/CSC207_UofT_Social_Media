package use_case.my_profile;

import entity.User;

public interface MyProfileUserDataAccessInterface {

    public void save(User user);
    public User getUserInfo(String username);
}
