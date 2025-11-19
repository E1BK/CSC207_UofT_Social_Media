package interface_adapter.my_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.my_profile.MyProfileOutputBoundary;
import use_case.my_profile.MyProfileOutputData;

public class MyProfilePresenter implements MyProfileOutputBoundary {

    private final MyProfileViewModel myProfileViewModel;
    private final SearchUserViewModel searchUserViewModel;
    // To implement
//    private final PostViewModel postViewModel;
//    private final MyProfileModel myMyProfileModel;
    private final ViewManagerModel viewManagerModel;
    private final LandingViewModel landingViewModel;

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
//        viewManagerModel.setState(myProfileModel.getViewName());
//        viewManagerModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
