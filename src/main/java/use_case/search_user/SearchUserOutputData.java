package use_case.search_user;

public class SearchUserOutputData {

    private final String username;
    private final String email;
    private final String bio;


    public SearchUserOutputData(String username, String email, String bio) {
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }
}
