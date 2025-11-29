package interface_adapter.my_profile;

import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;
import use_case.my_profile.MyProfileUserDataAccessInterface;

public class MyProfileController {

    private final MyProfileInputBoundary myProfileInteractor;
    private final MyProfileUserDataAccessInterface dataAccess;

    public MyProfileController(MyProfileInputBoundary myProfileInteractor, MyProfileUserDataAccessInterface dataAccess) {
        this.myProfileInteractor = myProfileInteractor;
        this.dataAccess = dataAccess;
    }

    public void execute(String username) {
        final MyProfileInputData myMyProfileInputData = new MyProfileInputData(dataAccess, username);
        myProfileInteractor.execute(myMyProfileInputData);
    }

    public void switchToLandingView() { myProfileInteractor.switchToLandingView(); }
    public void switchToSearchView() { myProfileInteractor.switchToSearchView(); }
    public void switchToPostView() { myProfileInteractor.switchToPostView(); }
    public void switchToMyProfileView() { myProfileInteractor.switchToMyProfileView(); }
    public void switchToLoginSignupView() {myProfileInteractor.switchToLoginSignupView();}
    public void switchToCurrentPost(int postID) {
    }

    public void refreshPosts(String username) {
        myProfileInteractor.refreshPosts(username);
    }
}
