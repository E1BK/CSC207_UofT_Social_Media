// hasan
package interface_adapter.landing;

import view.LandingView;
import interface_adapter.ViewModel;

public class LandingViewModel extends ViewModel<LandingState> {

    public static final String TITLE_LABEL = "Welcome, user!";

    public static final String ME_BUTTON_LABEL = "Profile";
    public static final String MAKE_POST_BUTTON_LABEL = "Make a Post";
    public static final String PEOPLE_BUTTON_LABEL = "People";
    public static final String POSTS_BUTTON_LABEL = "Home"; // i.e. the landing page!


    public LandingViewModel() {
        super("landing");
        setState(new LandingState());
    }

}
