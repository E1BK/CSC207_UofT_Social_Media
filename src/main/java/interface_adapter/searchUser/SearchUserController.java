// hasan
package interface_adapter.searchUser;

import entity.User;
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
     * @param u the user
     */
    public void execute(User u) {
        final SearchUserInputData searchUserInputData = new SearchUserInputData(u.getUsername());

        searchUserInteractor.execute(searchUserInputData);
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
