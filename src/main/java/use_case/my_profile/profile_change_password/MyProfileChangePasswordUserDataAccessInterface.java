package use_case.my_profile.profile_change_password;

import entity.User;

/**
 * The DAO interface for the Change Password Use Case.
 */
public interface MyProfileChangePasswordUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void changePassword(User user);
    void changeBio(User user);
    User getUserInfo(String username);
}
