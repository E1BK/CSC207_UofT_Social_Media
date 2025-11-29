package use_case.profile;

import entity.Post;
import entity.User;
import use_case.my_profile.PostData;

import java.util.ArrayList;

public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileUserDataAccessInterface profileUserDataAccess;
    private final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(
            ProfileUserDataAccessInterface profileUserDataAccessInterface,
            ProfileOutputBoundary profilePresenter) {
        this.profileUserDataAccess = profileUserDataAccessInterface;
        this.profilePresenter = profilePresenter;
    }

    @Override
    public void execute(ProfileInputData profileInputData) {
        profilePresenter.setState(profileInputData.getUsername(),
                                  profileInputData.getEmail(),
                                  profileInputData.getBio(),
                                  profileInputData.getPosts());
    }

    // Switches between views
    public void switchToMyProfileView() { profilePresenter.switchToMyProfileView(); }
    public void switchToLandingView() {
        profilePresenter.switchToLandingView();
    }
    public void switchToSearchView() {
        profilePresenter.switchToSearchView();
    }
    public void switchToPostView() {
        profilePresenter.switchToPostView();
    }
    public void switchToProfileView() { profilePresenter.switchToProfileView(); }

    public void refreshPosts(String username) {
        User user = profileUserDataAccess.getUserInfo(username);
        ArrayList<Post> posts = user.getPosts();
        PostData postData = new PostData();
        postData.setPostList(posts);
        profilePresenter.refreshPosts(postData.getPosts());
    }
}
