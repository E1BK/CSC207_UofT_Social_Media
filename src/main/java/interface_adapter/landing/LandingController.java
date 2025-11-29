package interface_adapter.landing;

import use_case.landing.LandingInputBoundary;

public class LandingController {
    private final LandingInputBoundary landingInteractor;
    public LandingController(LandingInputBoundary landingInteractor) {
        this.landingInteractor = landingInteractor;
    }

    public void execute(){
        landingInteractor.execute();
    }
}
