package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.my_profile.MyProfileState;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private MyProfileViewModel myProfileViewModel;
    private LoginViewModel loginSignupViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           MyProfileViewModel myProfileViewModel,
                           LoginViewModel loginSignupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myProfileViewModel = myProfileViewModel;
        this.loginSignupViewModel = loginSignupViewModel;
    }


    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {
        MyProfileState myProfileState  = this.myProfileViewModel.getState();
        String oldUsername = myProfileState.getUsername();
        myProfileState.logout();
        myProfileState.setInactive();
        myProfileViewModel.setState(myProfileState);

        LoginState loginState = this.loginSignupViewModel.getState();
        loginState.setUsername(oldUsername);
        loginState.setPassword("");
        this.loginSignupViewModel.setState(loginState);
        this.loginSignupViewModel.firePropertyChange();

        this.viewManagerModel.setState(loginSignupViewModel.getViewName());
        this.viewManagerModel.firePropertyChange();
    }
}