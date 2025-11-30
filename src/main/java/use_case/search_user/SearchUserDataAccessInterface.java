package use_case.search_user;

import entity.User;

public interface SearchUserDataAccessInterface {

    /**(NEW)
     * Look up a user whose username matches the given value.
     * @param username the username to search for
     * @return the User object if the user exists
     * @throws RuntimeException if the user does not exist or if the data source returns an error
     */
    User getUserInfo(String username);

}
