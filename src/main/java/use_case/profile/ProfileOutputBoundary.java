package use_case.profile;

public interface ProfileOutputBoundary {
    void prepareSuccessView(ProfileOutputData ProfileOutputData);
    void prepareFailView(String errorMessage);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
}