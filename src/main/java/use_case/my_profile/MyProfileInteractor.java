package use_case.my_profile;

import interface_adapter.my_profile.MyProfilePresenter;

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
}
