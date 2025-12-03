package use_case.profile;

import entity.Post;
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

    /**
     * Creates the searched <user>'s profile from <ProfileInputData> using <username>.
     * It also creates a list of posts creates from getting the <user>'s <Post>s
     * @param inputData
     */
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
                postData,
                inputData.getUser());

        profilePresenter.prepareSuccessView(outputData);
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
}
