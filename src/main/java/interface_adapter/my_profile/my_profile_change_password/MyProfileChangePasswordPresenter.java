package interface_adapter.my_profile.my_profile_change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordOutputBoundary;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class MyProfileChangePasswordPresenter implements MyProfileChangePasswordOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;

    public MyProfileChangePasswordPresenter(ViewManagerModel viewManagerModel,
                                   LandingViewModel landingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(MyProfileChangePasswordOutputData outputData) {
        landingViewModel.getState().setPassword("");
        landingViewModel.getState().setPasswordError(null);
        landingViewModel.firePropertyChange("password");
    }

    @Override
    public void prepareFailView(String error) {
        landingViewModel.getState().setPasswordError(error);
        landingViewModel.firePropertyChange("password");
    }
}