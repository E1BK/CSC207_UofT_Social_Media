// hasan
package interface_adapter.search_user;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.see_profile.SeeProfileViewModel;
import use_case.search_user.SearchUserOutputBoundary;

public class SearchUserPresenter implements SearchUserOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SeeProfileViewModel seeProfileViewModel;

    public SearchUserPresenter(
            ViewManagerModel viewManagerModel,
            LandingViewModel landingViewModel,
            SearchUserViewModel searchUserViewModel,
            SeeProfileViewModel seeProfileViewModel
    ) {
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.seeProfileViewModel = seeProfileViewModel;
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
        viewManagerModel.setState(seeProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
