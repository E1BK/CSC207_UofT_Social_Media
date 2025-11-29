package app;

import entity.ClubFactory;
import entity.CommentFactory;
import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.clubs.ClubsController;
import interface_adapter.clubs.ClubsPresenter;
import interface_adapter.clubs.ClubsViewModel;
import interface_adapter.landing.LandingController;
import interface_adapter.landing.LandingPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.make_post.MakePostViewModel;
import interface_adapter.my_profile.my_profile_change_password.MyProfileChangePasswordController;
import interface_adapter.my_profile.my_profile_change_password.MyProfileChangePasswordPresenter;
import use_case.clubs.ClubsInputBoundary;
import use_case.clubs.ClubsInteractor;
import use_case.clubs.ClubsOutputBoundary;
import use_case.landing.LandingInputBoundary;
import use_case.landing.LandingInteractor;
import use_case.landing.LandingOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInputBoundary;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordInteractor;
import use_case.my_profile.profile_change_password.MyProfileChangePasswordOutputBoundary;
import view.SearchUserView;
import interface_adapter.my_profile.*;
import use_case.my_profile.*;
import view.*;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import data_access.DBUserDataAccessObject;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.make_post.MakePostController;
import interface_adapter.make_post.MakePostPresenter;
import interface_adapter.search_user.SearchUserController;
import interface_adapter.search_user.SearchUserPresenter;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.make_post.MakePostInputBoundary;
import use_case.make_post.MakePostInteractor;
import use_case.make_post.MakePostOutputBoundary;
import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInteractor;
import use_case.search_user.SearchUserOutputBoundary;
import use_case.login_signup.login.LoginInputBoundary;
import use_case.login_signup.login.LoginInteractor;
import use_case.login_signup.login.LoginOutputBoundary;
import use_case.login_signup.signup.SignupInputBoundary;
import use_case.login_signup.signup.SignupInteractor;
import use_case.login_signup.signup.SignupOutputBoundary;
import view.LoginSignupView;
import interface_adapter.view_post.*;
import use_case.view_post.*;
import use_case.add_comment.AddCommentInputBoundary;
import use_case.add_comment.AddCommentInteractor;


import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final PostFactory postFactory = new PostFactory();
    final CommentFactory commentFactory = new CommentFactory();
    final ClubFactory clubFactory = new ClubFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    public ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory, postFactory, commentFactory, clubFactory);

    // Add View Models
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoginSignupView loginSignupView;
    private LandingView landingView;
    private LandingViewModel landingViewModel;
    private MakePostViewModel makePostViewModel;

    private ProfileView profileView;
    private ProfileViewModel profileViewModel;

    private MyProfileView myProfileView;
    private MyProfileViewModel myProfileViewModel;

    private SearchUserView searchUserView;
    private SearchUserViewModel searchUserViewModel;

    private PostView postView;
    private ViewPostViewModel viewPostViewModel;

    private ClubsView clubsView;
    private ClubsViewModel clubsViewModel;



    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLoginSignupView() {
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        loginSignupView = new LoginSignupView(loginViewModel, signupViewModel);
        cardPanel.add(loginSignupView, loginSignupView.getViewName());
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        SignupController controller = new SignupController(userSignupInteractor);
        loginSignupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                                                                           landingViewModel,
                                                                           loginViewModel,
                                                                           myProfileViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        LoginController loginController = new LoginController(loginInteractor);
        loginSignupView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addLandingView() {
        landingViewModel = new LandingViewModel();
        makePostViewModel = new MakePostViewModel();
        landingView = new LandingView(landingViewModel, makePostViewModel);
        cardPanel.add(landingView, landingView.getViewName());
        return this;
    }

    public AppBuilder addSearchUserView() {
        searchUserViewModel = new SearchUserViewModel();
        searchUserView = new SearchUserView(searchUserViewModel);
        cardPanel.add(searchUserView, searchUserView.getViewName());
        return this;
    }

    public AppBuilder addProfileView() {
        profileViewModel = new ProfileViewModel();
        profileView = new ProfileView(profileViewModel);
        cardPanel.add(profileView, profileView.getViewName());
        return this;
    }

    public AppBuilder addClubsView() {
        clubsViewModel = new ClubsViewModel();
        clubsView = new ClubsView(clubsViewModel);
        cardPanel.add(clubsView, clubsView.getViewName());
        return this;
    }

    public AppBuilder addProfileUseCase() {
        final ProfileOutputBoundary profileOutputBoundary = new ProfilePresenter(viewManagerModel,
                                                                                 landingViewModel,
                                                                                 searchUserViewModel,
                                                                                 profileViewModel);
        final ProfileInputBoundary profileInteractor = new ProfileInteractor(userDataAccessObject,
                                                                                   profileOutputBoundary,
                                                                                   userFactory,
                                                                                   postFactory);
        ProfileController controller = new ProfileController(profileInteractor);
        profileView.setProfileController(controller);
        // Change to my profile

        // Russell newly added:
        searchUserView.setProfileController(controller);
        return this;

    }

    public AppBuilder addMyProfileView() {
        myProfileViewModel = new MyProfileViewModel();
        myProfileView = new MyProfileView(myProfileViewModel);
        cardPanel.add(myProfileView, myProfileView.getViewName());
        return this;
    }

    public AppBuilder addMyProfileUseCase() {
        final MyProfileOutputBoundary myProfileOutputBoundary = new MyProfilePresenter(
                viewManagerModel,
                landingViewModel,
                searchUserViewModel,
                myProfileViewModel);
        final MyProfileInputBoundary myProfileInteractor = new MyProfileInteractor(
                userDataAccessObject,
                myProfileOutputBoundary);
        MyProfileController controller = new MyProfileController(myProfileInteractor, userDataAccessObject);
        myProfileView.setMyProfileController(controller);
        landingView.setMyProfileController(controller);
        return this;
    }

    public AppBuilder addLandingUseCase() {
        final LandingOutputBoundary landingOutputBoundary = new LandingPresenter(viewManagerModel, landingViewModel, searchUserViewModel, myProfileViewModel, clubsViewModel);
        final LandingInputBoundary landingInteractor = new LandingInteractor(userDataAccessObject, landingOutputBoundary);
        LandingController controller = new LandingController(landingInteractor);
        landingView.setLandingController(controller);
        return this;
    }

    public AppBuilder addMyProfileChangePasswordUseCase() {
        final MyProfileChangePasswordOutputBoundary myProfileChangePasswordOutputBoundary =
                new MyProfileChangePasswordPresenter(viewManagerModel,
                landingViewModel);
        final MyProfileChangePasswordInputBoundary myProfileChangePasswordInteractor =
                new MyProfileChangePasswordInteractor(userDataAccessObject, myProfileChangePasswordOutputBoundary, userFactory);

        MyProfileChangePasswordController myProfileChangePasswordController = new MyProfileChangePasswordController(myProfileChangePasswordInteractor);
        myProfileView.setChangePasswordController(myProfileChangePasswordController);
        return this;
    }

    public AppBuilder addPostView() {
        viewPostViewModel = new ViewPostViewModel();
        postView = new PostView(viewPostViewModel);
        cardPanel.add(postView, postView.getViewName());
        return this;
    }

    public AppBuilder addViewPostUseCase() {
        final ViewPostOutputBoundary viewPostOutputBoundary =
                new ViewPostPresenter(viewManagerModel, viewPostViewModel);

        final ViewPostInputBoundary viewPostInteractor =
                new ViewPostInteractor(userDataAccessObject, viewPostOutputBoundary);

        final AddCommentInputBoundary addCommentInteractor =
                new AddCommentInteractor(userDataAccessObject, commentFactory, viewPostOutputBoundary);

        ViewPostController controller =
                new ViewPostController(
                        viewPostInteractor,
                        addCommentInteractor,
                        viewManagerModel,
                        landingViewModel
                );

        postView.setViewPostController(controller);
        landingView.setViewPostController(controller);   // assuming you already have this setter

        return this;
    }

    public AppBuilder addMakePostUseCase() {
        final MakePostOutputBoundary makePostOutputBoundary = new MakePostPresenter(landingViewModel, makePostViewModel);
        final MakePostInputBoundary makePostInteractor = new MakePostInteractor(
                userDataAccessObject, makePostOutputBoundary, userFactory, postFactory);

        MakePostController makePostController = new MakePostController(makePostInteractor);
        landingView.setMakePostController(makePostController);
        return this;
    }

    public AppBuilder addSearchUserUseCase() {
        final SearchUserOutputBoundary searchUserOutputBoundary =  new SearchUserPresenter(viewManagerModel,
                landingViewModel, searchUserViewModel, myProfileViewModel);
        final SearchUserInputBoundary searchUserInteractor = new SearchUserInteractor(
                userDataAccessObject, searchUserOutputBoundary);

        SearchUserController searchUserController = new SearchUserController(searchUserInteractor);
        searchUserView.setSearchUserController(searchUserController);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                                                                              myProfileViewModel,
                                                                              loginViewModel);
        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);
        final LogoutController logoutController = new LogoutController(logoutInteractor);
        myProfileView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addClubsUseCase() {
        final ClubsOutputBoundary clubsOutputBoundary = new ClubsPresenter(clubsViewModel,
                landingViewModel, viewManagerModel);
        final ClubsInputBoundary clubsInteractor = new ClubsInteractor(
                clubsOutputBoundary, userDataAccessObject);

        ClubsController clubsController = new ClubsController(clubsInteractor);
        clubsView.setClubsController(clubsController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("UofT Social Media App");

        Image img = new ImageIcon(
                getClass().getClassLoader().getResource("img/chatuoft_lg.png")
        ).getImage();

        application.setIconImage(img);

        if (Taskbar.isTaskbarSupported()) {
            try {
                Taskbar.getTaskbar().setIconImage(img);
            } catch (UnsupportedOperationException ignored) {
            }
        }

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(loginSignupView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}
