// hasan
package interface_adapter.landing;

import interface_adapter.ViewManagerModel;
import use_case.make_post.MakePostInputBoundary;
import use_case.make_post.MakePostOutputBoundary;
import use_case.make_post.MakePostOutputData;

/**
 * Controller for the Change Password Use Case.
 */

public class MakePostPresenter implements MakePostOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;

    public MakePostPresenter(ViewManagerModel viewManagerModel,
                             LandingViewModel landingViewModel) {

        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareSuccessView(MakePostOutputData makePostOutputData) {
        // ????
        // landingViewModel.getState().addNewPost(p);
        landingViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        landingViewModel.firePropertyChange();
    }
}
