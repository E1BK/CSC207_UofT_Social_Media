package interface_adapter.my_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.my_profile.MyProfileOutputBoundary;
import use_case.my_profile.MyProfileOutputData;
import view.LoginSignupView;

import java.util.ArrayList;
import java.util.Map;

public class MyProfilePresenter implements MyProfileOutputBoundary {

    private final MyProfileViewModel myProfileViewModel;
    private final SearchUserViewModel searchUserViewModel;
    // To implement
//    private final PostViewModel postViewModel;
//    private final MyProfileModel myMyProfileModel;
    private final ViewManagerModel viewManagerModel;
    private final LandingViewModel landingViewModel;
    private final String loginSignupViewName = "login signup";

    public MyProfilePresenter(ViewManagerModel viewManagerModel,
                            LandingViewModel landingViewModel,
                            SearchUserViewModel searchUserViewModel,
                            MyProfileViewModel myProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myProfileViewModel = myProfileViewModel;
        this.searchUserViewModel = searchUserViewModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(MyProfileOutputData makePostOutputData) { myProfileViewModel.firePropertyChange(); }

    @Override
    public void prepareFailView(String errorMessage) { myProfileViewModel.firePropertyChange(); }

    public void switchToSearchView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void switchToPostView() {
//        viewManagerModel.setState(postViewModel.getViewName());
//        viewManagerModel.firePropertyChange();
    }

    @Override
    public void switchToMyProfileView() {
        viewManagerModel.setState(myProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToLoginSignupView() {
        viewManagerModel.setState(loginSignupViewName);
        viewManagerModel.firePropertyChange();
    }

    public void refreshPosts(ArrayList<Map> posts) {
        MyProfileState state = myProfileViewModel.getState();
        state.setPosts(posts);
    }
}
