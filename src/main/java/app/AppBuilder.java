package app;

//import data_access.FileUserDataAccessObject;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.logout.LogoutPresenter;
import use_case.login_signup.change_passwrod.ChangePasswordInputBoundary;
import use_case.login_signup.change_passwrod.ChangePasswordInteractor;
import use_case.login_signup.change_passwrod.ChangePasswordOutputBoundary;
import use_case.login_signup.change_passwrod.ChangePasswordOutputData;
import use_case.profile.ProfileUserDataAccessInterface;
import view.*;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import view.ProfileView;
import view.ViewManager;
//import data_access.FileUserDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.CommentFactory;
import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.ViewManagerModel;
import view.LandingView;
import interface_adapter.landing.MakePostController;
import interface_adapter.landing.MakePostPresenter;
import interface_adapter.searchUser.SearchUserController;
import interface_adapter.searchUser.SearchUserPresenter;
import interface_adapter.searchUser.SearchUserViewModel;
import use_case.make_post.MakePostInputBoundary;
import use_case.make_post.MakePostInteractor;
import use_case.make_post.MakePostOutputBoundary;
import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInteractor;
import use_case.search_user.SearchUserOutputBoundary;
import use_case.login_signup.login.LoginInputBoundary;
import use_case.login_signup.login.LoginInteractor;
import use_case.login_signup.login.LoginOutputBoundary;
import use_case.login_signup.logout.LogoutInputBoundary;
import use_case.login_signup.logout.LogoutInteractor;
import use_case.login_signup.logout.LogoutOutputBoundary;
import use_case.login_signup.signup.SignupInputBoundary;
import use_case.login_signup.signup.SignupInteractor;
import use_case.login_signup.signup.SignupOutputBoundary;
import view.LoginSignupView;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final PostFactory postFactory = new PostFactory();
    final CommentFactory commentFactory = new CommentFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    public ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory, postFactory, commentFactory);

    // Add View Models
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoginSignupView loginSignupView;
    private LandingView landingView;
    private LandingViewModel landingViewModel;
    private ProfileView profileView;
    private ProfileViewModel profileViewModel;

    private SearchUserView searchUserView;
    private SearchUserViewModel searchUserViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLoginView() {
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
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        LoginController loginController = new LoginController(loginInteractor);
        loginSignupView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {

            }
        };

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        ChangePasswordController changePasswordController = new ChangePasswordController(changePasswordInteractor);
        loginSignupView.setChangePasswordController(changePasswordController);
        return this;
    }


    public AppBuilder addLandingView() {
        landingViewModel = new LandingViewModel();
        landingView = new LandingView(landingViewModel);
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

    public AppBuilder addProfileUseCase() {
        final ProfileOutputBoundary profileOutputBoundary = new ProfilePresenter(viewManagerModel,
                                                                                 landingViewModel,
                                                                                 searchUserViewModel,
                                                                                 profileViewModel);
        final ProfileInputBoundary profileInteractor = new ProfileInteractor((ProfileUserDataAccessInterface) userDataAccessObject,
                                                                                   profileOutputBoundary,
                                                                                   userFactory,
                                                                                   postFactory);
        ProfileController controller = new ProfileController(profileInteractor);
        profileView.setProfileController(controller);
        return this;

    }

    public AppBuilder addMakePostUseCase() {
        final MakePostOutputBoundary makePostOutputBoundary = new MakePostPresenter(viewManagerModel,
                                                                                    landingViewModel,
                                                                                    searchUserViewModel,
                                                                                    profileViewModel);
        final MakePostInputBoundary makePostInteractor = new MakePostInteractor(
                userDataAccessObject, makePostOutputBoundary, userFactory, postFactory);

        MakePostController makePostController = new MakePostController(makePostInteractor);
        landingView.setMakePostController(makePostController);
        return this;
    }

    public AppBuilder addSearchUserUseCase() {
        final SearchUserOutputBoundary searchUserOutputBoundary =  new SearchUserPresenter(viewManagerModel,
                landingViewModel, searchUserViewModel);
        final SearchUserInputBoundary searchUserInteractor = new SearchUserInteractor(
                userDataAccessObject, searchUserOutputBoundary);

        SearchUserController searchUserController = new SearchUserController(searchUserInteractor);
        searchUserView.setSearchUserController(searchUserController);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("UofT Social Media App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(profileView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}
