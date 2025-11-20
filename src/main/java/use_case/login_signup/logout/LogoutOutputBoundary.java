package use_case.login_signup.logout;

/**
 * The output boundary for the Logout Use Case.
 */
public interface LogoutOutputBoundary {
    /**
     * Prepares the success view for the Logout Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(LogoutOutputData outputData);

}
