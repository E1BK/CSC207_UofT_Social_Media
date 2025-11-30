package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.landing.LandingViewModel;
import interface_adapter.my_profile.MyProfileViewModel;
import interface_adapter.search_user.SearchUserViewModel;
import use_case.make_post.PostViewData;
import use_case.profile.ProfileOutputData;
import use_case.profile.ProfileOutputBoundary;

import java.util.ArrayList;

public class ProfilePresenter implements ProfileOutputBoundary {

    private final MyProfileViewModel myProfileViewModel;
    private final ProfileViewModel profileViewModel;
    private final SearchUserViewModel searchUserViewModel;
    // TODO implement
//    private final PostViewModel postViewModel;
//    private final MyProfileModel myMyProfileModel;
    private final ViewManagerModel viewManagerModel;
    private final LandingViewModel landingViewModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel,
                              LandingViewModel landingViewModel,
                              SearchUserViewModel searchUserViewModel,
                              MyProfileViewModel myProfileViewModel,
                              ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myProfileViewModel = myProfileViewModel;
        this.profileViewModel = profileViewModel;
        this.searchUserViewModel = searchUserViewModel;
        this.landingViewModel = landingViewModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData makePostOutputData) { profileViewModel.firePropertyChange(); }

    @Override
    public void prepareFailView(String errorMessage) { profileViewModel.firePropertyChange(); }

    public void switchToProfileView() {
        profileViewModel.firePropertyChange();
        viewManagerModel.setState(profileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToSearchView() {
        viewManagerModel.setState(searchUserViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void switchToPostView() {
//        viewManagerModel.setState(postViewModel.getViewName());
//        viewManagerModel.firePropertyChange();
    }

    @Override
    public void switchToMyProfileView() {
        viewManagerModel.setState(myProfileViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void switchToLandingView() {
        viewManagerModel.setState(landingViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    public void refreshPosts(ArrayList<PostViewData> posts) {
        ProfileState state = profileViewModel.getState();
        state.setPosts(posts);
    }

    public void setState(ProfileOutputData outputData) {
        ProfileState state = profileViewModel.getState();
        state.setUsername(outputData.getUsername());
        state.setEmail(outputData.getEmail());
        state.setBio(outputData.getBio());
        state.setPosts(outputData.getPosts());
    }
}
