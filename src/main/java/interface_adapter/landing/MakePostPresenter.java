// hasan
package interface_adapter.landing;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
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
    private final ProfileViewModel profileViewModel;

    public MakePostPresenter(ViewManagerModel viewManagerModel,
                             LandingViewModel landingViewModel,
                             SearchUserViewModel searchUserViewModel,
                             ProfileViewModel profileViewModel) {

        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(MakePostOutputData makePostOutputData) {
        // ????
        // landingViewModel.getState().addNewPost(p); ???
        landingViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        landingViewModel.firePropertyChange();
    }

    public void switchToProfileView() {
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToPeopleView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
