package interface_adapter.change_password;

import use_case.login_signup.change_passwrod.ChangePasswordInputBoundary;
import use_case.login_signup.change_passwrod.ChangePasswordInputData;
import use_case.login_signup.change_passwrod.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     *
     * @param oldPassword
     * @param newPassword        the new password
     * @param username        the user whose password to change
     * @param confirmPassword
     */
    public void execute(String username, String oldPassword, String newPassword, String confirmPassword) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, oldPassword, newPassword, confirmPassword);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}