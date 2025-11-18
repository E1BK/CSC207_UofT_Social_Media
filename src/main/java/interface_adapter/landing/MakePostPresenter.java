// hasan
package interface_adapter.landing;

import interface_adapter.ViewManagerModel;
import interface_adapter.searchUser.SearchUserViewModel;
import interface_adapter.see_profile.SeeProfileViewModel;
import use_case.make_post.MakePostInputBoundary;
import use_case.make_post.MakePostOutputBoundary;
import use_case.make_post.MakePostOutputData;

/**
 * Controller for the Change Password Use Case.
 */

public class MakePostPresenter implements MakePostOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SeeProfileViewModel seeProfileViewModel;

    public MakePostPresenter(ViewManagerModel viewManagerModel,
                             LandingViewModel landingViewModel,
                             SearchUserViewModel searchUserViewModel, SeeProfileViewModel seeProfileViewModel) {

        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.seeProfileViewModel = seeProfileViewModel;
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

    public void switchToPeopleView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToMeView() {
        viewManagerModel.setState(seeProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
