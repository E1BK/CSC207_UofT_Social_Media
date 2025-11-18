package use_case.see_profile;

public class SeeProfileInputData {
    private final String username;
    private final String bio;

    public SeeProfileInputData(String username, String bio) {
        this.username = username;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

}
