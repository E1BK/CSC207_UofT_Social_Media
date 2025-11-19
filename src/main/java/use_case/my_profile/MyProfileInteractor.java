package use_case.my_profile;

import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.my_profile.MyProfilePresenter;
import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInputData;
import use_case.my_profile.MyProfileOutputBoundary;
import use_case.my_profile.MyProfileUserDataAccessInterface;

public class MyProfileInteractor implements MyProfileInputBoundary {
    private final MyProfileUserDataAccessInterface myProfileUserDataAccess;
    private final MyProfileOutputBoundary myProfilePresenter;
    private final UserFactory userFactory;
    private final PostFactory postFactory;

    public MyProfileInteractor(
            MyProfileUserDataAccessInterface myProfileUserDataAccessInterface,
            MyProfileOutputBoundary myProfilePresenter,
            UserFactory userFactory,
            PostFactory postFactory) {
        this.myProfileUserDataAccess = myProfileUserDataAccessInterface;
        this.myProfilePresenter = myProfilePresenter;
        this.userFactory = userFactory;
        this.postFactory = postFactory;
    }

    @Override
    public void execute(MyProfileInputData myProfileInputData) {

    }

    // Switches between views
    public void switchToMyProfileView() {
        MyProfilePresenter temp = (MyProfilePresenter) myProfilePresenter;
        System.out.println("bye");
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
}
