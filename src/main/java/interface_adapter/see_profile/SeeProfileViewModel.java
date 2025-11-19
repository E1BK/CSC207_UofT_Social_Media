package interface_adapter.see_profile;

import interface_adapter.ViewModel;

public class SeeProfileViewModel extends ViewModel<SeeProfileState> {


    public SeeProfileViewModel() {
        super("profile");
        setState(new SeeProfileState());
    }

}
