package use_case.user_search;

import java.util.List;

// Data access interface. by DBUserDataAccessObject
public interface SearchUserDataAccessInterface {

    // Get all usernames from repository
    List<String> getAllUsernames();
}