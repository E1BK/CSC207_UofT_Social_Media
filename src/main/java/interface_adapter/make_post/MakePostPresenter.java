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
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyProfileViewModel myProfileViewModel;
    private final ClubsViewModel clubsViewModel;

    public MakePostPresenter(ViewManagerModel viewManagerModel,
                             LandingViewModel landingViewModel,
                             MakePostViewModel makePostViewModel,
                             SearchUserViewModel searchUserViewModel,
                             MyProfileViewModel myProfileViewModel,
                             ClubsViewModel clubsViewModel) {

        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.makePostViewModel = makePostViewModel;
        this.searchUserViewModel = searchUserViewModel;
        this.myProfileViewModel = myProfileViewModel;
        this.clubsViewModel = clubsViewModel;
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

    public void switchToPeopleView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToMeView() {
        viewManagerModel.setState(myProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToClubsView() {
        viewManagerModel.setState(clubsViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
