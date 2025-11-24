package use_case.my_profile.profile_change_password;

import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputData;

public interface MyProfileChangePasswordInputBoundary {

    /**
     * Execute the Change Password Use Case.
     * @param changePasswordInputData the input data for this use case
     */
    void execute(MyProfileChangePasswordInputData changePasswordInputData);

}