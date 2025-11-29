// hasan
package interface_adapter.make_post;

import interface_adapter.ViewManagerModel;
import interface_adapter.clubs.ClubsViewModel;
import interface_adapter.landing.LandingState;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.make_post.MakePostOutputBoundary;
import use_case.make_post.MakePostOutputData;

/**
 * Controller for the Change Password Use Case.
 */

public class MakePostPresenter implements MakePostOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final MakePostViewModel makePostViewModel;

    public MakePostPresenter(LandingViewModel landingViewModel,
                             MakePostViewModel makePostViewModel) {

        this.landingViewModel = landingViewModel;
        this.makePostViewModel = makePostViewModel;
    }

    @Override
    public void prepareSuccessView(MakePostOutputData makePostOutputData) {
        LandingState landingState = landingViewModel.getState();
        landingState.addPost(makePostOutputData.getNewPost());
        landingViewModel.setState(landingState);
        landingViewModel.firePropertyChange();

        MakePostState makePostState = makePostViewModel.getState();
        makePostState.setNewpost_title("");
        makePostState.setNewpost_body("");
        makePostViewModel.setState(makePostState);
        makePostViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        MakePostState makePostState = makePostViewModel.getState();

        makePostState.setpostError(errorMessage);
        makePostViewModel.setState(makePostState);

        makePostViewModel.firePropertyChange();

    }

}
