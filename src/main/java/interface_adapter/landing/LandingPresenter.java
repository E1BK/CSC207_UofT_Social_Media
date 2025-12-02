package interface_adapter.landing;

import interface_adapter.ViewManagerModel;
import interface_adapter.clubs.ClubsViewModel;
import interface_adapter.my_profile.MyProfileState;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.search_user.SearchUserState;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.landing.LandingOutputBoundary;
import use_case.landing.LandingOutputData;

public class LandingPresenter implements LandingOutputBoundary {
    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyProfileViewModel myProfileViewModel;
    private final ClubsViewModel clubsViewModel;

    public LandingPresenter(ViewManagerModel viewManagerModel,
                            LandingViewModel landingViewModel,
                            SearchUserViewModel searchUserViewModel,
                            MyProfileViewModel myProfileViewModel,
                            ClubsViewModel clubsViewModel) {
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.myProfileViewModel = myProfileViewModel;
        this.clubsViewModel = clubsViewModel;
    }

    @Override
    public void prepareSuccessView(LandingOutputData landingOutputData) {
        LandingState landingState = landingViewModel.getState();
        landingState.setPosts(landingOutputData.getPosts());
        landingViewModel.setState(landingState);
        landingViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        LandingState landingState = landingViewModel.getState();
        landingState.setGetPostError(errorMessage);
        landingViewModel.setState(landingState);
        landingViewModel.firePropertyChange();
    }

    public void switchToPeopleView() {
        final LandingState landingState = landingViewModel.getState();
        final SearchUserState searchUserState = searchUserViewModel.getState();
        searchUserState.setUsername(landingState.getUsername());
        searchUserViewModel.firePropertyChange();
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToProfileView() {
        final LandingState landingState = landingViewModel.getState();
        final MyProfileState myProfileState = myProfileViewModel.getState();
        myProfileState.setUsername(landingState.getUsername());
        myProfileViewModel.firePropertyChange();
        viewManagerModel.setState(myProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToClubsView() {
        viewManagerModel.setState(clubsViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

}
