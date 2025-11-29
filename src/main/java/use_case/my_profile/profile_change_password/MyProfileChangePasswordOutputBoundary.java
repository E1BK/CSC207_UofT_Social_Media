package use_case.my_profile.profile_change_password;

/**
 * The output boundary for the Change Password Use Case.
 */
public interface MyProfileChangePasswordOutputBoundary {
    /**
     * Prepares the success view for the Change Password Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(MyProfileChangePasswordOutputData outputData);

    /**
     * Prepares the failure view for the Change Password Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
