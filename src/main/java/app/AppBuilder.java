package app;

import View.SearchUserView;
import View.ViewManager;
//import data_access.FileUserDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.CommentFactory;
import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.ViewManagerModel;
import View.LandingView;
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
//import use_case.login.LoginInputBoundary;
//import use_case.login.LoginInteractor;
//import use_case.login.LoginOutputBoundary;
//import use_case.logout.LogoutInputBoundary;
//import use_case.logout.LogoutInteractor;
//import use_case.logout.LogoutOutputBoundary;
//import use_case.signup.SignupInputBoundary;
//import use_case.signup.SignupInteractor;
//import use_case.signup.SignupOutputBoundary;
//import view.LoggedInView;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;

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

    private LandingView landingView;
    private LandingViewModel landingViewModel;

    private SearchUserView searchUserView;
    private SearchUserViewModel searchUserViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
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

    public AppBuilder addMakePostUseCase() {
        final MakePostOutputBoundary makePostOutputBoundary = new MakePostPresenter(viewManagerModel,
                landingViewModel, searchUserViewModel);
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

        viewManagerModel.setState(landingView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }

}