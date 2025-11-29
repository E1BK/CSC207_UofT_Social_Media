// hasan
package interface_adapter.landing;

import interface_adapter.ViewManagerModel;
import interface_adapter.clubs.ClubsViewModel;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.make_post.MakePostOutputBoundary;
import use_case.make_post.MakePostOutputData;

/**
 * Controller for the Change Password Use Case.
 */

public class MakePostPresenter implements MakePostOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyProfileViewModel myProfileViewModel;
    private final ClubsViewModel clubsViewModel;

    public MakePostPresenter(ViewManagerModel viewManagerModel,
                             LandingViewModel landingViewModel,
                             SearchUserViewModel searchUserViewModel,
                             MyProfileViewModel myProfileViewModel,
                             ClubsViewModel clubsViewModel) {

        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.myProfileViewModel = myProfileViewModel;
        this.clubsViewModel = clubsViewModel;
    }

    @Override
    public void prepareSuccessView(MakePostOutputData makePostOutputData) {
        LandingState state = landingViewModel.getState();

        state.setNewpost_title("");
        state.setNewpost_body("");

        // TODO: show the new post on landing

        landingViewModel.setState(state);
        landingViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        LandingState state = landingViewModel.getState();

        state.setpostError(errorMessage);
        landingViewModel.setState(state);

        landingViewModel.firePropertyChange();

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
