package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.login_signup.signup.SignupOutputBoundary;
import use_case.login_signup.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // 1. Optionally pre-fill the login username
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChange();

        // 2. Trigger the signup view to handle "success"
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(null);
        signupState.setEmailError(null);
        signupState.setNameError(null);
        signupState.setPasswordError(null);
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(null);
        signupState.setEmailError(null);
        signupState.setNameError(null);
        signupState.setPasswordError(null);

        if (error.contains("email")) {
            signupState.setEmailError(error);
        } else if (error.contains("username")) {
            signupState.setUsernameError(error);
        } else if (error.contains("name")) {
            signupState.setNameError(error);
        } else if (error.contains("password")) {
            signupState.setPasswordError(error);
        } else {
            signupState.setUsernameError(error);
        }
        signupViewModel.firePropertyChange();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
