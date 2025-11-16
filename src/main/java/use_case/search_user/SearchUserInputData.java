// hasan
package use_case.search_user;

public class SearchUserInputData {
    private final String username;


    public SearchUserInputData(String username){
        this.username = username;

    }

    public String getUsername(){ return username; }
}
