package interface_adapter.clubs;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import use_case.clubs.ClubsInputData;
import use_case.clubs.ClubsOutputBoundary;
import use_case.clubs.ClubsOutputData;


public class ClubsPresenter implements ClubsOutputBoundary {

    private final ClubsViewModel clubsViewModel;
    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;



    public ClubsPresenter(ClubsViewModel clubsViewModel, LandingViewModel landingViewModel, ViewManagerModel viewManagerModel) {
        this.clubsViewModel = clubsViewModel;
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(ClubsOutputData response) {

        // On success, update the clubsViewModel's state
        final ClubsState clubsState = clubsViewModel.getState();
        clubsState.setFoundClubName(response.getFoundClubName());
        clubsState.setFoundClubDescription(response.getFoundClubDescription());
        this.clubsViewModel.firePropertyChange();

        System.out.println("success view");

    }

    public void prepareFailView(ClubsInputData inputData) {
        final ClubsState clubsState = clubsViewModel.getState();
        clubsState.setFoundClubName("This club does not exist.");
        clubsState.setFoundClubDescription("");
        clubsViewModel.firePropertyChange();

        System.out.println("fail view");
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
