package use_case.clubs;

import interface_adapter.clubs.ClubsPresenter;

public class ClubsInteractor implements ClubsInputBoundary{

    private ClubsOutputBoundary clubsPresenter;
    private ClubsDataAccessInterface clubsDataAccessObject;

    public ClubsInteractor(ClubsOutputBoundary clubsPresenter, ClubsDataAccessInterface clubsDataAccessObject) {
        this.clubsPresenter = clubsPresenter;
        this.clubsDataAccessObject = clubsDataAccessObject;
    }

    @Override
    public void execute(ClubsInputData clubsInputData) {

    }

    public void switchToLandingView() {
        ClubsPresenter temp = (ClubsPresenter) clubsPresenter;
        temp.switchToLandingView();
    }
}
