package use_case.my_profile;


import data_access.DBUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfilePresenter;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;

public class MyProfileTests {
    @NotNull
    private MyProfileInteractor setUpInteractor() {
        MyProfileInputData inputData = new MyProfileInputData("tst");

        UserFactory userFactory = new UserFactory();
        PostFactory postFactory = new PostFactory();
        CommentFactory commentFactory = new CommentFactory();
        ClubFactory clubFactory = new ClubFactory();
        DBUserDataAccessObject dataAccess = new DBUserDataAccessObject(userFactory,
                postFactory, commentFactory, clubFactory);

        User user = userFactory.create("tstUser0", "tsttst", "tst@mail.utoronto.ca", "tst");
        Post post = new Post(010101, "tstUser0", "tstTitle",
                "This is a test", "12/02/25", new ArrayList<Comment>());
        user.addPost(post);
        dataAccess.save(user);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LandingViewModel landingViewModel = new LandingViewModel();
        SearchUserViewModel searchUserViewModel = new SearchUserViewModel();
        MyProfileViewModel myProfileViewModel = new MyProfileViewModel();

        MyProfilePresenter presenter = new MyProfilePresenter(viewManagerModel,
                landingViewModel, searchUserViewModel, myProfileViewModel);
        return new MyProfileInteractor(dataAccess, presenter);
    }

    @Test
    public void testUpdateProfile() {
        MyProfileInputData inputData = new MyProfileInputData("tstUser0");
        MyProfileInteractor interactor = setUpInteractor();
        interactor.execute(inputData);
    }

    @Test
    public void testBadUser() {
        MyProfileInputData inputData = new MyProfileInputData("badUser");
        MyProfileInteractor interactor = setUpInteractor();
        interactor.execute(inputData);
    }

    @Test
    public void testSwitchToLoginSignupView() {
        MyProfileInteractor interactor = setUpInteractor();
        interactor.switchToLoginSignupView();
    }

    @Test
    public void tstSwitchToLandingView() {
        MyProfileInteractor interactor = setUpInteractor();
        interactor.switchToLandingView();
    }

    @Test
    public void testSwitchToSearchUserView() {
        MyProfileInteractor interactor = setUpInteractor();
        interactor.switchToSearchView();
    }

    @Test
    public void testSwitchToPostView() {
        MyProfileInteractor interactor = setUpInteractor();
        interactor.switchToPostView();
    }
}
