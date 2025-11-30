package use_case.profile;

import use_case.make_post.PostViewData;

import java.util.ArrayList;

public interface ProfileOutputBoundary {
    void prepareSuccessView(ProfileOutputData myProfileOutputData);
    void prepareFailView(String errorMessage);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
    void refreshPosts(ArrayList<PostViewData> posts);
    void switchToProfileView();
    void setState(ProfileOutputData outputData);
}
