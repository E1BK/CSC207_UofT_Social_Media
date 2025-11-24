package interface_adapter.my_profile.my_profile_change_password;

import entity.Post;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputBoundary;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputData;

import java.util.ArrayList;

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
     * @param newPassword        the new password
     * @param username        the user whose password to change
     */
    public void execute(String username, String newPassword, String bio,
                        String email, String name, ArrayList<Post> posts) {
        final MyProfileChangePasswordInputData changePasswordInputData =
                new MyProfileChangePasswordInputData(username, newPassword, bio, email, name, posts);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}