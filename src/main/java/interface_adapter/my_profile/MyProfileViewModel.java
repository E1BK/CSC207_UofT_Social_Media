package interface_adapter.my_profile;

import interface_adapter.ViewModel;

public class MyProfileViewModel extends ViewModel<MyProfileState> {

    public static final String HOME_BUTTON_LABEL = "Home";
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String PROFILE_BUTTON_LABEL = "Profile";

    public static final String USERNAME = "User";
    public static final String TITLE = "Title";
    public static final String BODY = "Body";
    public static final String DATE = "Date";
    public static final String ID = "Id";
    public static final String NUM_OF_COMMENTS = "Comments";

    public MyProfileViewModel() {
        super("my profile");
        setState(new MyProfileState());
    }
}
