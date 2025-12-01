package interface_adapter.my_profile.my_profile_change_password;

import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputBoundary;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class MyProfileChangePasswordController {
    private final MyProfileChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public MyProfileChangePasswordController(MyProfileChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     *
     * @param password        the new password
     * @param username        the user whose password to change
     */
    public void execute(String username, String password, String bio, String passOrBio) {
        final MyProfileChangePasswordInputData changePasswordInputData =
                new MyProfileChangePasswordInputData(username, password, bio, passOrBio);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}