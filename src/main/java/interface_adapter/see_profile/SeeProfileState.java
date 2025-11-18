package interface_adapter.see_profile;

import entity.Post;

import java.util.ArrayList;

public class SeeProfileState {

    private String username = "";
    private String password = "";
    private ArrayList<Post> thisUsersPosts = new ArrayList<Post>();

    // the default no-arg constructor:
    public SeeProfileState() {
    }

    // the second constructor:
    public SeeProfileState(SeeProfileState copy) {
        username = copy.username;
        password = copy.password;
        thisUsersPosts = copy.thisUsersPosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Post> getThisUsersPosts() {return thisUsersPosts;}

    public void setThisUsersPosts(ArrayList<Post> newThisUsersPosts) {
        thisUsersPosts.clear();
        thisUsersPosts.addAll(newThisUsersPosts);
    }



}
