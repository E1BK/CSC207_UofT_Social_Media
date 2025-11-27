package interface_adapter.clubs;

import use_case.clubs.ClubsInputBoundary;
import use_case.clubs.ClubsInteractor;

public class ClubsController {

    private final ClubsInputBoundary clubsInteractor;

    public ClubsController(ClubsInputBoundary clubsInteractor) {
        this.clubsInteractor = clubsInteractor;
    }

    public void findClub(String searchQuery) {
        clubsInteractor.findClub(searchQuery);
    }

    public void switchToLandingView() {
        ClubsInteractor temp = (ClubsInteractor) clubsInteractor;
        temp.switchToLandingView();
    }

}
