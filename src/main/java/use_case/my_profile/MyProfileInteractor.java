package use_case.my_profile;

import entity.Post;
import interface_adapter.my_profile.MyProfilePresenter;
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

    @Override
    public void execute(MyProfileInputData myProfileInputData) {

    }

    // Switches between views
    public void switchToMyProfileView() {
        MyProfilePresenter temp = (MyProfilePresenter) myProfilePresenter;
        temp.switchToMyProfileView();
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

    public void refreshPosts(String username) {
        User user = myProfileUserDataAccess.getUserInfo(username);
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

        myProfilePresenter.refreshPosts(postData);
    }
}
