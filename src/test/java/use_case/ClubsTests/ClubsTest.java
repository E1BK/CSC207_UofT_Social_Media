package use_case.ClubsTests;

import data_access.DBUserDataAccessObject;
import entity.ClubFactory;
import entity.CommentFactory;
import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.clubs.ClubsPresenter;
import interface_adapter.clubs.ClubsViewModel;
import interface_adapter.landing.LandingViewModel;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.clubs.ClubsInputData;
import use_case.clubs.ClubsInteractor;

public class ClubsTest {

    @NotNull
    private ClubsInteractor setupClubsInteractor() {
        ClubsViewModel clubsViewModel = new ClubsViewModel();
        LandingViewModel landingViewModel = new LandingViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ClubsPresenter clubsPresenter = new ClubsPresenter(clubsViewModel, landingViewModel, viewManagerModel);

        UserFactory userFactory = new UserFactory();
        PostFactory postFactory = new PostFactory();
        CommentFactory commentFactory = new CommentFactory();
        ClubFactory clubFactory = new ClubFactory();
        final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory, postFactory, commentFactory, clubFactory);

        ClubsInteractor clubsInteractor = new ClubsInteractor(clubsPresenter, userDataAccessObject);
        return clubsInteractor;
    }

    @Test
    public void successTest() {
        ClubsInputData clubsInputData = new ClubsInputData("csSu");

        ClubsInteractor clubsInteractor = setupClubsInteractor();
        clubsInteractor.execute(clubsInputData);
    }

    @Test
    public void testSwitchToLandingView() {
        ClubsInputData clubsInputData = new ClubsInputData("nonexistent club");

        ClubsInteractor clubsInteractor = setupClubsInteractor();
        clubsInteractor.switchToLandingView();
    }

    @Test
    public void failureTest() {
        ClubsInputData clubsInputData = new ClubsInputData("nonexistent club that doesn't exist");

        ClubsInteractor clubsInteractor = setupClubsInteractor();
        clubsInteractor.execute(clubsInputData);
    }
}
