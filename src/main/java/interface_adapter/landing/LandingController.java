package interface_adapter.landing;

import use_case.landing.LandingInputBoundary;
import use_case.make_post.MakePostInteractor;

public class LandingController {
    private final LandingInputBoundary landingInteractor;
    public LandingController(LandingInputBoundary landingInteractor) {
        this.landingInteractor = landingInteractor;
    }

    public void execute(){
        landingInteractor.execute();
    }

    public void switchToPeopleView() {
        landingInteractor.switchToPeopleView();
    }

    public void switchToMeView() {
        landingInteractor.switchToMeView();
    }

    public void switchToClubsView() {
        landingInteractor.switchToClubsView();
    }
}
