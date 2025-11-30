package use_case.profile;

import entity.Post;
import entity.User;
import use_case.make_post.PostViewData;

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
    public void execute(ProfileInputData inputData) {
        ArrayList<Post> posts = profileUserDataAccess.getUserInfo(inputData.getUsername()).getPosts();
        ArrayList<PostViewData> postData = new  ArrayList<>();
        for (Post post : posts) {
            postData.add(new PostViewData(post.getUsername(),
                    post.getPost_id(),
                    post.getTitle(),
                    post.getBody(),
                    post.getPost_date(),
                    post.getComments()));
        }

        ProfileOutputData outputData = new ProfileOutputData(inputData.getUsername(),
                inputData.getEmail(),
                inputData.getBio(),
                postData);

        profilePresenter.setState(outputData);
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
        ArrayList<PostViewData> postData = new ArrayList<>();

        for (Post post : posts) {
            postData.add(new PostViewData(post.getUsername(),
                    post.getPost_id(),
                    post.getTitle(),
                    post.getBody(),
                    post.getPost_date(),
                    post.getComments()));
        }

        profilePresenter.refreshPosts(postData);
    }
}
