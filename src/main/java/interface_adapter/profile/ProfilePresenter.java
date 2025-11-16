package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.searchUser.SearchUserViewModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundary {

    private final ProfileViewModel profileViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LandingViewModel landingViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                            LandingViewModel landingViewModel,
                            SearchUserViewModel searchUserViewModel,
                            ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
        this.searchUserViewModel = searchUserViewModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData makePostOutputData) { profileViewModel.firePropertyChange(); }

    @Override
    public void prepareFailView(String errorMessage) { profileViewModel.firePropertyChange(); }

    public void switchToProfileView() {
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToSearchUserView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
