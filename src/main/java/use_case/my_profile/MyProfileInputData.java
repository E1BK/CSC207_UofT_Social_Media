package use_case.my_profile;

public class MyProfileInputData{
    private final String username;
    private final String password;
    private final String email;
    private final String bio;


    public MyProfileInputData(String username, String password, String email, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getEmail(){ return email; }
    public String getBio(){ return bio; }
}
