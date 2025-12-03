package use_case.profile;


import data_access.DBUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;

public class ProfileTests {
    @NotNull
    private ProfileInteractor setUpInteractor() {
        UserFactory userFactory = new UserFactory();
        PostFactory postFactory = new PostFactory();
        CommentFactory commentFactory = new CommentFactory();
        ClubFactory clubFactory = new ClubFactory();
        DBUserDataAccessObject dataAccess = new DBUserDataAccessObject(userFactory,
                postFactory, commentFactory, clubFactory);

        User user = userFactory.create("tstUser0", "tsttst",
                "tst@mail.utoronto.ca", "tst");
        Post post = new Post(010101, "tstUser0", "tstTitle",
                "This is a test", "12/02/25", new ArrayList<Comment>());
        user.addPost(post);
        dataAccess.save(user);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LandingViewModel landingViewModel = new LandingViewModel();
        SearchUserViewModel searchUserViewModel = new SearchUserViewModel();
        MyProfileViewModel myProfileViewModel = new MyProfileViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();

        ProfilePresenter presenter = new ProfilePresenter(viewManagerModel,
                landingViewModel, searchUserViewModel, myProfileViewModel, profileViewModel);
        return new ProfileInteractor(dataAccess, presenter);
    }

    @Test
    public void testUpdateProfile() {
        ProfileInputData inputData = new ProfileInputData(
                "tstUser0", "tst@mail.utoronto.ca", "", "tsttst");
        ProfileInteractor interactor = setUpInteractor();
        interactor.execute(inputData);
    }

    @Test
    public void testSwitchToProfileView() {
        ProfileInteractor interactor = setUpInteractor();
        interactor.switchToProfileView();
    }

    @Test
    public void tstSwitchToLandingView() {
        ProfileInteractor interactor = setUpInteractor();
        interactor.switchToLandingView();
    }

    @Test
    public void testSwitchToSearchUserView() {
        ProfileInteractor interactor = setUpInteractor();
        interactor.switchToSearchView();
    }

    @Test
    public void testSwitchToMyProfileView() {
        ProfileInteractor interactor = setUpInteractor();
        interactor.switchToMyProfileView();
    }

    @Test
    public void testSwitchToPostView() {
        ProfileInteractor interactor = setUpInteractor();
        interactor.switchToPostView();
    }
}
