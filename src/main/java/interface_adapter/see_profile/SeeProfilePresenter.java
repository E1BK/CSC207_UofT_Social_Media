package interface_adapter.see_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.see_profile.SeeProfileOutputBoundary;
import use_case.see_profile.SeeProfileOutputData;

public class SeeProfilePresenter implements SeeProfileOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SeeProfileViewModel seeProfileViewModel;

    public SeeProfilePresenter(LandingViewModel landingViewModel, SearchUserViewModel searchUserViewModel, ViewManagerModel viewManagerModel, SeeProfileViewModel seeProfileViewModel) {
        this.landingViewModel = landingViewModel;
        this.searchUserViewModel = searchUserViewModel;
        this.viewManagerModel = viewManagerModel;
        this.seeProfileViewModel = seeProfileViewModel;
    }

    @Override
    public void prepareSuccessView(SeeProfileOutputData profileOutputData) {
        seeProfileViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        seeProfileViewModel.firePropertyChange();
    }

    public void switchToHomeView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToPeopleView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
