package use_case.my_profile;

public class MyProfileInputData {
    private final String username;


    public MyProfileInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
