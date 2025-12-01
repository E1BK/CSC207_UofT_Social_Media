package use_case.my_profile;

import data_access.DBUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.my_profile_change_password.MyProfileChangePasswordPresenter;
import org.junit.Test;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputData;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInteractor;

public class ChangePasswordTests {
    private MyProfileChangePasswordInteractor setUpInteractor() {
        UserFactory userFactory = new UserFactory();
        PostFactory postFactory = new PostFactory();
        CommentFactory commentFactory = new CommentFactory();
        ClubFactory clubFactory = new ClubFactory();
        DBUserDataAccessObject dataAccess = new DBUserDataAccessObject(userFactory,
                postFactory, commentFactory, clubFactory);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LandingViewModel landingViewModel = new LandingViewModel();

        MyProfileChangePasswordPresenter presenter = new MyProfileChangePasswordPresenter(viewManagerModel,
                landingViewModel);

        User user = userFactory.create("tstUser0", "tsttst", "tst@mail.utoronto.ca", "tst");
        dataAccess.save(user);

        return new MyProfileChangePasswordInteractor(dataAccess, presenter, userFactory);
    }

    @Test
    public void testChangePassword() {
        MyProfileChangePasswordInteractor interactor = setUpInteractor();
        MyProfileChangePasswordInputData inputData = new MyProfileChangePasswordInputData("tstUser0",
                "tsttst", "","password");
        interactor.execute(inputData);
    }

    @Test
    public void testChangeBio() {
        MyProfileChangePasswordInteractor interactor = setUpInteractor();
        MyProfileChangePasswordInputData inputData = new MyProfileChangePasswordInputData(
                "tstUser0", "tsttst", "New Bio", "bio");
        interactor.execute(inputData);
    }
}
