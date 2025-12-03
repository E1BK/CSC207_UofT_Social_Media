package use_case.SearchUserTest;
import entity.Post;
import entity.User;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.search_user.SearchUserPresenter;
import interface_adapter.search_user.SearchUserViewModel;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

import use_case.search_user.SearchUserDataAccessInterface;
import use_case.search_user.SearchUserInputData;
import use_case.search_user.SearchUserInteractor;


public class SearchUserTest {


    static class FakeSearchUserDAO implements SearchUserDataAccessInterface {

        User userToReturn = null;
        RuntimeException exceptionToThrow = null;

        @Override
        public User getUserInfo(String username) {
            if (exceptionToThrow != null) {
                throw exceptionToThrow;
            }
            return userToReturn;
        }
    }


    private SearchUserInteractor makeInteractor(FakeSearchUserDAO fakeDAO,
                                                SearchUserViewModel searchVM) {

        ViewManagerModel viewManager = new ViewManagerModel();
        LandingViewModel landingVM = new LandingViewModel();
        MyProfileViewModel myProfileVM = new MyProfileViewModel();

        SearchUserPresenter presenter =
                new SearchUserPresenter(viewManager, landingVM, searchVM, myProfileVM);

        return new SearchUserInteractor(fakeDAO, presenter);
    }

    @Test
    public void testExecute_successUserFound() {
        FakeSearchUserDAO fakeDAO = new FakeSearchUserDAO();
        SearchUserViewModel searchVM = new SearchUserViewModel();

        fakeDAO.userToReturn = new User(
                "rgaohe",
                "pw",
                "bio here",
                "r@example.com",
                "Russell",
                new ArrayList<Post>()
        );

        SearchUserInteractor interactor = makeInteractor(fakeDAO, searchVM);

        SearchUserInputData input = new SearchUserInputData("rgaohe");
        interactor.execute(input);

        var state = searchVM.getState();
        assertEquals("Found user: rgaohe", state.getMessage());
        assertEquals("rgaohe", state.getSelectedUsername());
        assertEquals("r@example.com", state.getSelectedEmail());
        assertEquals("bio here", state.getSelectedBio());
    }

    @Test
    public void testExecute_userNotFound() {
        FakeSearchUserDAO fakeDAO = new FakeSearchUserDAO();
        SearchUserViewModel searchVM = new SearchUserViewModel();

        SearchUserInteractor interactor = makeInteractor(fakeDAO, searchVM);

        SearchUserInputData input = new SearchUserInputData("nosuchuser");
        interactor.execute(input);

        var state = searchVM.getState();
        assertEquals("User Not Found", state.getMessage());
        assertEquals("", state.getSelectedUsername());
        assertEquals("", state.getSelectedEmail());
        assertEquals("", state.getSelectedBio());
    }

    @Test
    public void testExecute_daoThrowsException() {
        FakeSearchUserDAO fakeDAO = new FakeSearchUserDAO();
        SearchUserViewModel searchVM = new SearchUserViewModel();

        fakeDAO.exceptionToThrow = new RuntimeException("API error");
        SearchUserInteractor interactor = makeInteractor(fakeDAO, searchVM);

        SearchUserInputData input = new SearchUserInputData("anything");
        interactor.execute(input);

        var state = searchVM.getState();
        assertEquals("User Not Found", state.getMessage());
    }

    @Test
    public void testSwitchToLandingAndMeView() {
        FakeSearchUserDAO fakeDAO = new FakeSearchUserDAO();
        SearchUserViewModel searchVM = new SearchUserViewModel();

        ViewManagerModel viewManager = new ViewManagerModel();
        LandingViewModel landingVM = new LandingViewModel();
        MyProfileViewModel myProfileVM = new MyProfileViewModel();
        SearchUserPresenter presenter =
                new SearchUserPresenter(viewManager, landingVM, searchVM, myProfileVM);

        SearchUserInteractor interactor = new SearchUserInteractor(fakeDAO, presenter);

        searchVM.getState().setUsername("rgaohe");

        interactor.switchToLandingView();
        assertEquals(landingVM.getViewName(), viewManager.getState());

        interactor.switchToMeView();
        assertEquals(myProfileVM.getViewName(), viewManager.getState());
        assertEquals("rgaohe", myProfileVM.getState().getUsername());
    }
}