package interface_adapter.my_profile;

import entity.Post;
import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;

import java.util.ArrayList;

public class MyProfileController {

    private final MyProfileInputBoundary myProfileInteractor;

    public MyProfileController(MyProfileInputBoundary myProfileInteractor) {
        this.myProfileInteractor = myProfileInteractor;
    }

    public void execute(String username, String email, String bio, ArrayList<Post> posts) {
        final MyProfileInputData myMyProfileInputData = new MyProfileInputData(username,
                email,
                bio,
                posts);

        myProfileInteractor.execute(myMyProfileInputData);
    }

    public void switchToLandingView() { myProfileInteractor.switchToLandingView(); }
    public void switchToSearchView() { myProfileInteractor.switchToSearchView(); }
    public void switchToPostView() { myProfileInteractor.switchToPostView(); }
    public void switchToMyProfileView() { myProfileInteractor.switchToMyProfileView(); }
    public void switchToCurrentPost(Post post) {
    }
}
