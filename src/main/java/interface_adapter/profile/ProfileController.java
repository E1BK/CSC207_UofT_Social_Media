package interface_adapter.profile;

import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

public class ProfileController {

    private final ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    public void execute(String username, String email, String bio, String user) {
        final ProfileInputData profileInputData = new ProfileInputData(username, email, bio, user);
        profileInteractor.execute(profileInputData);
    }

    public void switchToLandingView() { profileInteractor.switchToLandingView(); }
    public void switchToSearchView() { profileInteractor.switchToSearchView(); }
    public void switchToPostView() { profileInteractor.switchToPostView(); }
    public void switchToMyProfileView() { profileInteractor.switchToMyProfileView(); }
    public void switchToProfileView() { profileInteractor.switchToProfileView(); }
    public void switchToCurrentPost(int postID) {
    }
}
