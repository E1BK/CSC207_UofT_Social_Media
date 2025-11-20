// hasan
package interface_adapter.search_user;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileViewModel;
import use_case.search_user.SearchUserOutputBoundary;

public class SearchUserPresenter implements SearchUserOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyProfileViewModel myProfileViewModel;

    public SearchUserPresenter(
            ViewManagerModel viewManagerModel,
            LandingViewModel landingViewModel,
            SearchUserViewModel searchUserViewModel,
            MyProfileViewModel myProfileViewModel
    ) {
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.myProfileViewModel = myProfileViewModel;
    }

    @Override
    public void prepareSuccessView(User user) {
        searchUserViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView() {
        searchUserViewModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToMeView() {
        viewManagerModel.setState(myProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
