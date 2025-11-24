package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingState;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileState;
import interface_adapter.my_profile.MyProfileViewModel;
import use_case.login_signup.login.LoginOutputBoundary;
import use_case.login_signup.login.LoginOutputData;
import use_case.my_profile.PostData;

import java.util.ArrayList;
import java.util.Map;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyProfileViewModel myProfileViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LandingViewModel landingViewModel,
                          LoginViewModel loginViewModel,
                          MyProfileViewModel myProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.landingViewModel = landingViewModel;
        this.loginViewModel = loginViewModel;
        this.myProfileViewModel = myProfileViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        final LandingState landingState = landingViewModel.getState();
        landingState.setUsername(response.getUsername());
        this.landingViewModel.firePropertyChange();

        final MyProfileState myProfileState = myProfileViewModel.getState();
        myProfileState.setUsername(response.getUsername());
        myProfileState.setPassword(response.getPassword());
        myProfileState.setBio(response.getBio());
        myProfileState.setEmail(response.getEmail());
        PostData postData = new PostData();
        postData.setPostList(response.getPosts());
        ArrayList<Map> postList = postData.getPosts();
        myProfileState.setPosts(postList);
        myProfileViewModel.firePropertyChange();

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
