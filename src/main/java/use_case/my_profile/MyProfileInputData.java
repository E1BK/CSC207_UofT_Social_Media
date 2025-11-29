package use_case.my_profile;

import entity.User;

public class MyProfileInputData{
    private MyProfileUserDataAccessInterface myProfileUserDataAccess;

    private final String username;
    private final String name;
    private final String password;
    private final String email;
    private final String bio;


    public MyProfileInputData(MyProfileUserDataAccessInterface myProfileUserDataAccessInterface, String username) {
        this.myProfileUserDataAccess = myProfileUserDataAccessInterface;

        this.username = username;
        User user;

        user = myProfileUserDataAccess.getUserInfo(username);

        name = user.getName();
        password = user.getPassword();
        email = user.getEmail();
        bio = user.getBio();

    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getBio(){ return bio; }
}
