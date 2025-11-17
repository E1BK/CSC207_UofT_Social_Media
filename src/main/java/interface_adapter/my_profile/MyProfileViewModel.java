package interface_adapter.my_profile;

import interface_adapter.ViewModel;

public class MyProfileViewModel extends ViewModel<MyProfileState> {
    public MyProfileViewModel() {
        super("my profile");
        setState(new MyProfileState());
    }
}
