// hasan, russell
//// (russell) Note: The controller works with a String username instead of user's entity
//// The view only has text input, not a User object. The use case + DAO are
//// responsible for finding the actual User entity.
package interface_adapter.search_user;

// import entity.User; (deleted)
import use_case.search_user.SearchUserInputBoundary;
import use_case.search_user.SearchUserInputData;
import use_case.search_user.SearchUserInteractor;

public class SearchUserController {

    private final SearchUserInputBoundary searchUserInteractor;

    public SearchUserController(SearchUserInputBoundary searchUserInteractor) {

        this.searchUserInteractor = searchUserInteractor;
    }

    /**
     * Executes the Search User Use Case.
     * new. Execute the Search User use case with a username.
     */
    //russell: new execute
    // Only the username string exists in the GUI, not the complete user object.
    public void execute(String username) {
        SearchUserInputData inputData = new SearchUserInputData(username);
        searchUserInteractor.execute(inputData);
    }

    public void switchToLandingView() {
        SearchUserInteractor temp = (SearchUserInteractor) searchUserInteractor;
        temp.switchToLandingView();
    }

    public void switchToMeView() {
        SearchUserInteractor temp = (SearchUserInteractor) searchUserInteractor;
        temp.switchToMeView();
    }
}
