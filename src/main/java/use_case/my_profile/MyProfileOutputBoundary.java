package use_case.my_profile;

import java.util.ArrayList;
import java.util.Map;

public interface MyProfileOutputBoundary {
    void prepareSuccessView(MyProfileOutputData myProfileOutputData);
    void prepareFailView(String errorMessage);

    void switchToLandingView();
    void switchToSearchView();
    void switchToPostView();
    void switchToMyProfileView();
    void refreshPosts(ArrayList<Map> posts);
}