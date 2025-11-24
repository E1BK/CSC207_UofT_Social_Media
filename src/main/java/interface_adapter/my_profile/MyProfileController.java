package interface_adapter.my_profile;

import entity.Post;
import entity.User;
import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;

public class MyProfileController {

    private final MyProfileInputBoundary myProfileInteractor;

    public MyProfileController(MyProfileInputBoundary myProfileInteractor) {
        this.myProfileInteractor = myProfileInteractor;
    }

    public void execute(User user) {
        final MyProfileInputData myMyProfileInputData = new MyProfileInputData(user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getPosts());

        myProfileInteractor.execute(myMyProfileInputData);
    }

    public void switchToLandingView() { myProfileInteractor.switchToLandingView(); }
    public void switchToSearchView() { myProfileInteractor.switchToSearchView(); }
    public void switchToPostView() { myProfileInteractor.switchToPostView(); }
    public void switchToMyProfileView() { myProfileInteractor.switchToMyProfileView(); }
    public void switchToCurrentPost(Post post) {
    }
}
