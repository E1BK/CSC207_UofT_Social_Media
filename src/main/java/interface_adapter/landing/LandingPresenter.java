package interface_adapter.landing;

import use_case.landing.LandingOutputBoundary;
import use_case.landing.LandingOutputData;
import interface_adapter.landing.LandingState;
import use_case.make_post.MakePostOutputData;

public class LandingPresenter implements LandingOutputBoundary {
    private final LandingViewModel landingViewModel;

    public LandingPresenter(LandingViewModel landingViewModel) {
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(LandingOutputData landingOutputData) {
        LandingState landingState = landingViewModel.getState();
        landingState.setPosts(landingOutputData.getPosts());
        landingViewModel.setState(landingState);
        landingViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

}
