package use_case.landing;

import entity.User;

import java.util.ArrayList;

public interface LandingDataAccessInterface {
    User getUserInfo(String username);
    ArrayList<String> getExistingUsernames();
}
