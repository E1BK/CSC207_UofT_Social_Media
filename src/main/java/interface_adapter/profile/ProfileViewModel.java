package interface_adapter.profile;

import interface_adapter.ViewModel;

public class ProfileViewModel extends ViewModel<ProfileState> {

    public static final String BACK_BUTTON_LABEL = "Home";
    public static final String POST_BUTTON_LABEL = "Post";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String PROFILE_BUTTON_LABEL = "Profile";

    public ProfileViewModel() {
        super("profile");
        setState(new ProfileState());
    }
}
