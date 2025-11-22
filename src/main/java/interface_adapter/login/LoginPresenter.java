package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingState;
import interface_adapter.landing.LandingViewModel;
import use_case.login_signup.login.LoginOutputBoundary;
import use_case.login_signup.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LandingViewModel landingViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.landingViewModel = landingViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        final LandingState landingState = landingViewModel.getState();
        landingState.setUsername(response.getUsername());
        this.landingViewModel.firePropertyChange();
        loginViewModel.setState(new LoginState());
        this.viewManagerModel.setState(landingViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChange();
    }
}