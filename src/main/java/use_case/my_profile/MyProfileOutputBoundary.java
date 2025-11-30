package use_case.my_profile;

import use_case.make_post.PostViewData;

import java.util.ArrayList;

public interface MyProfileOutputBoundary {
    void prepareSuccessView(MyProfileOutputData myProfileOutputData);
    void prepareFailView(String errorMessage);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
    void switchToLoginSignupView();
}
