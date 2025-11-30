package interface_adapter.search_user;

public class SearchUserState {

    private String username = "";
    private String password = "";

    private String message = "";

    private String selectedUsername = "";

    public SearchUserState() {}

    public SearchUserState(SearchUserState copy) {
        username = copy.username;
        password = copy.password;
        message = copy.message;
        selectedUsername = copy.selectedUsername;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSelectedUsername() {
        return selectedUsername;
    }

    public void setSelectedUsername(String selectedUsername) {
        this.selectedUsername = selectedUsername;
    }
}