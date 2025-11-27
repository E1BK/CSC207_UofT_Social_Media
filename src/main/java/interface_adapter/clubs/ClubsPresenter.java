package interface_adapter.clubs;

import entity.Club;
import entity.ClubFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import use_case.clubs.ClubsOutputBoundary;
import use_case.clubs.ClubsOutputData;

import java.util.ArrayList;

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
        this.clubsViewModel.firePropertyChange();



//
//        ClubsState clubsState = clubsViewModel.getState();
//        clubsState.setClubToDisplay(foundClub);
//
////        ClubsState newState = new ClubsState(foundClub);
//
////        clubsViewModel.setState(newState);
//        clubsViewModel.firePropertyChange();
//
//        viewManagerModel.setState(clubsViewModel.getViewName());
//        viewManagerModel.firePropertyChange();

        System.out.println(clubsViewModel.getState().getFoundClubName());

    }

    public void prepareFailView(String searchQuery) {
        clubsViewModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
