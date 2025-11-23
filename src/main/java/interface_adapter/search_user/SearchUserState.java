package interface_adapter.search_user;

// hasan, russell
import entity.User;

import java.util.ArrayList;

public class SearchUserState {
    private String username = "";
    private String password = "";
    private ArrayList<User> people = new ArrayList<User>();

    // Russell: new field for displaying result message in the UI
    // eg. "Found user: gaohe" or "User Not Found"
    private String message = "";
    // the default no-arg constructor:
    public SearchUserState() {
    }

    // the second constructor:
    public SearchUserState(SearchUserState copy) {
        username = copy.username;
        password = copy.password;
        people = copy.people;
        message = copy.message;  // Russell: copy message as well
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

    public ArrayList<User> getPeople() {return people;}

    public void setPeople(ArrayList<User> newPeople) {
        people.clear();
        people.addAll(newPeople);
    }

    // Russell: getter/setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
