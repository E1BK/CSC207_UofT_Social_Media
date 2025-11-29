package use_case.profile;

public interface ProfileInputBoundary {
    void execute(ProfileInputData profileInputData);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
    void refreshPosts(String username);
    void switchToProfileView();
}
