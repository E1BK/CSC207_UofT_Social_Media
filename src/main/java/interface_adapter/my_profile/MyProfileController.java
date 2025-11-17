package interface_adapter.my_profile;

import entity.User;
import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;
import use_case.my_profile.MyProfileInteractor;

public class MyProfileController {

    private final MyProfileInputBoundary myMyProfileInteractor;

    public MyProfileController(MyProfileInputBoundary myMyProfileInteractor) {
        this.myMyProfileInteractor = myMyProfileInteractor;
    }

    public void execute(User user) {
        final MyProfileInputData myMyProfileInputData = new MyProfileInputData(user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getPosts());

        myMyProfileInteractor.execute(myMyProfileInputData);
    }

    public void switchToLandingView() { myMyProfileInteractor.switchToLandingView(); }
    public void switchToSearchView() { myMyProfileInteractor.switchToSearchView(); }
    public void switchToPostView() { myMyProfileInteractor.switchToPostView(); }
    public void switchToMyProfileView() { myMyProfileInteractor.switchToMyProfileView(); }
}
