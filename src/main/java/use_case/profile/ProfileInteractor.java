package use_case.profile;

import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.profile.ProfilePresenter;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileUserDataAccessInterface;

public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileUserDataAccessInterface profileUserDataAccess;
    private final ProfileOutputBoundary profilePresenter;
    private final UserFactory userFactory;
    private final PostFactory postFactory;

    public ProfileInteractor(
            ProfileUserDataAccessInterface profileUserDataAccessInterface,
            ProfileOutputBoundary profilePresenter,
            UserFactory userFactory,
            PostFactory postFactory) {
        this.profileUserDataAccess = profileUserDataAccessInterface;
        this.profilePresenter = profilePresenter;
        this.userFactory = userFactory;
        this.postFactory = postFactory;
    }

    @Override
    public void execute(ProfileInputData profileInputData) {

    }

    // Switches between views
    public void switchToProfileView() {
        ProfilePresenter temp = (ProfilePresenter) profilePresenter;
        System.out.println("bye");
        temp.switchToProfileView();
    }

    public void switchToLandingView() {
        profilePresenter.switchToLandingView();
    }

    public void switchToSearchView() {
        profilePresenter.switchToSearchView();
    }

    public void switchToPostView() {
        profilePresenter.switchToPostView();
    }

    public void switchToMyProfileView() {
        profilePresenter.switchToMyProfileView();
    }
}
