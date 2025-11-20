package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import use_case.login_signup.change_passwrod.ChangePasswordOutputBoundary;
import use_case.login_signup.change_passwrod.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(ViewManagerModel viewManagerModel,
                                   LandingViewModel landingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
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