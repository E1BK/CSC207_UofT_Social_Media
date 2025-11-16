// hasan
package use_case.search_user;

import entity.User;

public interface SearchUserDataAccessInterface {

    /**
     * Search the database for a user whose username matches.
     * If found, return the User object. Otherwise return null.
     * @param username
     */
    User findUserByUsername(String username);

}
