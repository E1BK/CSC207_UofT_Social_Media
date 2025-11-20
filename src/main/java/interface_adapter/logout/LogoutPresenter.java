package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.login_signup.logout.LogoutOutputBoundary;
import use_case.login_signup.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private ChangePasswordViewModel changePasswordViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           ChangePasswordViewModel changePasswordViewModel,
                           LoginViewModel loginViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        // 1. get the LoggedInState out of the appropriate View Model,
        // 2. set the username in the state to the empty string
        // 3. firePropertyChanged so that the View that is listening is updated.
        ChangePasswordState changePasswordState = this.changePasswordViewModel.getState();
        String oldUsername = changePasswordState.getUsername();
        changePasswordState.setUsername("");
        this.changePasswordViewModel.firePropertyChange();

        // 1. get the LoginState out of the appropriate View Model,
        // 2. set the username in the state to be the username of the user that just logged out,
        // 3. firePropertyChanged so that the View that is listening is updated.
        LoginState loginState = this.loginViewModel.getState();
        loginState.setUsername(oldUsername);
        this.loginViewModel.firePropertyChange();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}