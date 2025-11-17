package interface_adapter.profile;

public class ProfileState {
    private String username = "";

    public ProfileState(ProfileState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ProfileState() {

    }
}
