package interface_adapter.my_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.make_post.PostViewData;
import use_case.my_profile.MyProfileOutputBoundary;
import use_case.my_profile.MyProfileOutputData;

import java.util.ArrayList;

public class MyProfilePresenter implements MyProfileOutputBoundary {

    private final MyProfileViewModel myProfileViewModel;
    private final SearchUserViewModel searchUserViewModel;
    // TODO implement
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
    public void prepareSuccessView(MyProfileOutputData makePostOutputData) {
        ArrayList<PostViewData> posts = makePostOutputData.getPosts();
        MyProfileState state = myProfileViewModel.getState();
        state.setPosts(posts);
        myProfileViewModel.setState(state);
        myProfileViewModel.firePropertyChange();
    }

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
}
