// hasan, Russell
package interface_adapter.search_user;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileState;
import interface_adapter.my_profile.MyProfileViewModel;
import use_case.search_user.SearchUserOutputBoundary;

public class SearchUserPresenter implements SearchUserOutputBoundary {

    private final LandingViewModel landingViewModel;
    private final SearchUserViewModel searchUserViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MyProfileViewModel myProfileViewModel;

    public SearchUserPresenter(
            ViewManagerModel viewManagerModel,
            LandingViewModel landingViewModel,
            SearchUserViewModel searchUserViewModel,
            MyProfileViewModel myProfileViewModel
    ) {
        this.landingViewModel = landingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchUserViewModel = searchUserViewModel;
        this.myProfileViewModel = myProfileViewModel;
    }

    @Override
    public void prepareSuccessView(User user) {
        // Russell: update state with a success message and the found user,
        // then notify the view.
        SearchUserState state = searchUserViewModel.getState();
        state.setMessage("Found user: " + user.getUsername());
        state.setSelectedUser(user);  // store the found user
        searchUserViewModel.setState(state);
        searchUserViewModel.firePropertyChange();
    }

    @Override
    public void prepareFailView() {
        // Russell: update state with a failure message,
        // empty the selectedUser / and clear selectedUser.
        SearchUserState state = searchUserViewModel.getState();
        state.setMessage("User Not Found");
        state.setSelectedUser(null);  // 没找到，不保留上一次的用户
        searchUserViewModel.setState(state);
        searchUserViewModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToMeView() {
        final SearchUserState searchState = searchUserViewModel.getState();
        final MyProfileState myProfileState = myProfileViewModel.getState();
        myProfileState.setUsername(searchState.getUsername());
        viewManagerModel.setState(myProfileViewModel.getViewName());
        myProfileViewModel.firePropertyChange();
        viewManagerModel.firePropertyChange();
    }
}
