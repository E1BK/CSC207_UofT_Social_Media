package app;

import data_access.*;
import entity.*;
import interface_adapter.*;
import interface_adapter.landing.*;
import interface_adapter.my_profile.*;
import interface_adapter.profile.*;
import interface_adapter.search_user.*;
//import use_case.landing.*;
import use_case.make_post.*;
import use_case.my_profile.*;
import use_case.profile.*;
import use_case.search_user.*;
import view.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final PostFactory postFactory = new PostFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    final CommentFactory commentFactory = new CommentFactory();
    public ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);

    // Add View Models
    private LandingView landingView;
    private LandingViewModel landingViewModel;
    private ProfileView profileView;
    private ProfileViewModel profileViewModel;
    private MyProfileView myProfileView;
    private MyProfileViewModel myProfileViewModel;
    private view.SearchUserView searchUserView;
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
        final ProfileInputBoundary profileInteractor = new ProfileInteractor(userDataAccessObject,
                                                                                   profileOutputBoundary,
                                                                                   userFactory,
                                                                                   postFactory);
        ProfileController controller = new ProfileController(profileInteractor);
        profileView.setProfileController(controller);
        return this;

    }

    public AppBuilder addMyProfileView() {
        myProfileViewModel = new MyProfileViewModel();
        myProfileView = new MyProfileView(myProfileViewModel);
        cardPanel.add(myProfileView, myProfileView.getViewName());
        return this;
    }

    public AppBuilder addMyProfileUseCase() {
        final MyProfileOutputBoundary myProfileOutputBoundary = new MyProfilePresenter(viewManagerModel,
                                                                                       landingViewModel,
                                                                                       searchUserViewModel,
                                                                                       myProfileViewModel);
        final MyProfileInputBoundary myProfileInteractor = new MyProfileInteractor(userDataAccessObject,
                                                                                   myProfileOutputBoundary,
                                                                                   userFactory,
                                                                                   postFactory);
        MyProfileController controller = new MyProfileController(myProfileInteractor);
        myProfileView.setProfileController(controller);
        return this;

    }

    public AppBuilder addMakePostUseCase() {
        final MakePostOutputBoundary makePostOutputBoundary = new MakePostPresenter(viewManagerModel,
                                                                                    landingViewModel,
                                                                                    searchUserViewModel,
                                                                                    profileViewModel);
        final MakePostInputBoundary makePostInteractor = new MakePostInteractor(
                userDataAccessObject, makePostOutputBoundary, userFactory, postFactory);

        interface_adapter.make_post.MakePostController makePostController =
                new interface_adapter.make_post.MakePostController(makePostInteractor);
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

        viewManagerModel.setState(myProfileView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}
