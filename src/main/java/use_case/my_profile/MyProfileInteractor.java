package use_case.my_profile;

import entity.Post;
import entity.User;
import use_case.make_post.PostViewData;

import java.util.ArrayList;

public class MyProfileInteractor implements MyProfileInputBoundary {
    private final MyProfileUserDataAccessInterface myProfileUserDataAccess;
    private final MyProfileOutputBoundary myProfilePresenter;

    public MyProfileInteractor(
            MyProfileUserDataAccessInterface myProfileUserDataAccessInterface,
            MyProfileOutputBoundary myProfilePresenter) {
        this.myProfileUserDataAccess = myProfileUserDataAccessInterface;
        this.myProfilePresenter = myProfilePresenter;
    }

    /**
     * Creates/Updates the current <user>'s profile from <MyProfileInputData> using <username>.
     * It also creates a list of posts creates from getting the <user>'s <Post>s
     * @param inputData
     */
    @Override
    public void execute(MyProfileInputData inputData) {
        try {
            User user = myProfileUserDataAccess.getUserInfo(inputData.getUsername());
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

            MyProfileOutputData outputData = new MyProfileOutputData(user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getBio(),
                    postData);

            myProfilePresenter.prepareSuccessView(outputData);
        }
        catch (RuntimeException ex) {
            System.out.println("SearchUserInteractor error: " + ex.getMessage());
            myProfilePresenter.prepareFailView(ex.getMessage());
        }
    }

    public void switchToLandingView() {
        myProfilePresenter.switchToLandingView();
    }
    public void switchToSearchView() {
        myProfilePresenter.switchToSearchView();
    }
    public void switchToPostView() {
        myProfilePresenter.switchToPostView();
    }
    public void switchToLoginSignupView() { myProfilePresenter.switchToLoginSignupView(); }
}
