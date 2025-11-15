package app;

import View.ViewManager;
//import data_access.FileUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.ViewManagerModel;
//import interface_adapter.logged_in.ChangePasswordController;
//import interface_adapter.logged_in.ChangePasswordPresenter;
//import interface_adapter.logged_in.LoggedInViewModel;
//import interface_adapter.login.LoginController;
//import interface_adapter.login.LoginPresenter;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.logout.LogoutController;
//import interface_adapter.logout.LogoutPresenter;
//import interface_adapter.signup.SignupController;
//import interface_adapter.signup.SignupPresenter;
//import interface_adapter.signup.SignupViewModel;
//import use_case.change_password.ChangePasswordInputBoundary;
//import use_case.change_password.ChangePasswordInteractor;
//import use_case.change_password.ChangePasswordOutputBoundary;
import View.LandingView;
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
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    public ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);



    private LandingView landingView;
    private LandingViewModel landingViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addLandingView() {
        landingViewModel = new LandingViewModel();
        landingView = new LandingView(landingViewModel);
        cardPanel.add(landingView, landingView.getViewName());
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