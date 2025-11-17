package use_case.my_profile;

public interface MyProfileInputBoundary {
    void execute(MyProfileInputData myProfileInputData);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
}
