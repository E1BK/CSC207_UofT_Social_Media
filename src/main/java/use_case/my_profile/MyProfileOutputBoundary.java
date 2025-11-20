package use_case.my_profile;

public interface MyProfileOutputBoundary {
    void prepareSuccessView(MyProfileOutputData myProfileOutputData);
    void prepareFailView(String errorMessage);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
}