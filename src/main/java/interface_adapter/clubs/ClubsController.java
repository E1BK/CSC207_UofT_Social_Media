package interface_adapter.clubs;

import use_case.clubs.ClubsInputBoundary;
import use_case.clubs.ClubsInputData;
import use_case.clubs.ClubsInteractor;

public class ClubsController {

    private final ClubsInputBoundary clubsInteractor;

    public ClubsController(ClubsInputBoundary clubsInteractor) {
        this.clubsInteractor = clubsInteractor;
    }

    public void execute(String searchQuery) {
        ClubsInputData inputData = new ClubsInputData(searchQuery);
        clubsInteractor.execute(inputData);
    }

    public void switchToLandingView() {
        ClubsInteractor temp = (ClubsInteractor) clubsInteractor;
        temp.switchToLandingView();
    }

}
