package interface_adapter.my_profile;

import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;

public class MyProfileController {

    private final MyProfileInputBoundary myProfileInteractor;

    public MyProfileController(MyProfileInputBoundary myProfileInteractor) {
        this.myProfileInteractor = myProfileInteractor;
    }

    public void execute(String username) {
        final MyProfileInputData myMyProfileInputData = new MyProfileInputData(username);
        myProfileInteractor.execute(myMyProfileInputData);
    }

    public void switchToLandingView() { myProfileInteractor.switchToLandingView(); }
    public void switchToSearchView() { myProfileInteractor.switchToSearchView(); }
    public void switchToPostView() { myProfileInteractor.switchToPostView(); }
    public void switchToLoginSignupView() {myProfileInteractor.switchToLoginSignupView();}
    public void switchToCurrentPost(int postID) {
    }
}
