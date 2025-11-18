package interface_adapter.signup;

import use_case.login_signup.signup.SignupInputBoundary;
import use_case.login_signup.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     * @param email the email
     * @param name the name to use
     */
    public void execute(String username, String password1, String password2, String email, String name) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2, email, name);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}