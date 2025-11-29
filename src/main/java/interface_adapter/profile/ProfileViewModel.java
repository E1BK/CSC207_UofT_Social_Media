package interface_adapter.profile;

import interface_adapter.ViewModel;

public class ProfileViewModel extends ViewModel<ProfileState> {

    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String PROFILE_BUTTON_LABEL = "Profile";

    public static final String USERNAME = "User";
    public static final String TITLE = "Title";
    public static final String BODY = "Body";
    public static final String DATE = "Date";
    public static final String ID = "Id";
    public static final String NUM_OF_COMMENTS = "Comments";

    public ProfileViewModel() {
        super("profile");
        setState(new ProfileState());
    }
}
