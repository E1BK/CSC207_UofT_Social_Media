package interface_adapter.my_profile;

public class MyProfileState {
    private String username = "";

    public MyProfileState(MyProfileState copy) {
        username = copy.username;
    }

    public MyProfileState() {

    }
}
