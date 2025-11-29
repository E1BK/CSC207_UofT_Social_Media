package use_case.profile;

import java.util.ArrayList;
import java.util.Map;

public interface ProfileOutputBoundary {
    void prepareSuccessView(ProfileOutputData myProfileOutputData);
    void prepareFailView(String errorMessage);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
    void refreshPosts(ArrayList<Map> posts);
    void switchToProfileView();
    void setState(String username, String email, String bio, ArrayList<Map> posts);
}
