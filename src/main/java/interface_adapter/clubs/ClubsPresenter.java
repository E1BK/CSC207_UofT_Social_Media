package interface_adapter.clubs;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import use_case.clubs.ClubsOutputBoundary;

public class ClubsPresenter implements ClubsOutputBoundary {

    private final ClubsViewModel clubsViewModel;
    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubsPresenter(ClubsViewModel clubsViewModel, LandingViewModel landingViewModel, ViewManagerModel viewManagerModel) {
        this.clubsViewModel = clubsViewModel;
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView() {
        clubsViewModel.firePropertyChange();
    }

    public void prepareFailView() {
        clubsViewModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
