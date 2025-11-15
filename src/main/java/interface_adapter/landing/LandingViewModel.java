// hasan
package interface_adapter.landing;

import View.LandingView;
import interface_adapter.ViewModel;

public class LandingViewModel extends ViewModel<LandingState> {

    public LandingViewModel() {
        super("landing");
        setState(new LandingState());
    }

}
