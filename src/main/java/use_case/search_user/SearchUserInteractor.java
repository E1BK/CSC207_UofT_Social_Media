// hasan
package use_case.search_user;

import entity.User;
import interface_adapter.search_user.SearchUserPresenter;

public class SearchUserInteractor implements SearchUserInputBoundary{

    private final SearchUserDataAccessInterface searchUserDataAccessObject;
    private final SearchUserOutputBoundary searchUserPresenter;

    public SearchUserInteractor(SearchUserDataAccessInterface searchUserDataAccessObject,
                                SearchUserOutputBoundary searchUserPresenter) {

        this.searchUserDataAccessObject = searchUserDataAccessObject;
        this.searchUserPresenter = searchUserPresenter;

    }

    public void execute(SearchUserInputData searchUserInputData) {
        User foundUser = searchUserDataAccessObject.findUserByUsername(searchUserInputData.getUsername());
        if (foundUser == null) {
            searchUserPresenter.prepareFailView();
        }
        else {
            searchUserPresenter.prepareSuccessView(foundUser);
        }
    }

    public void switchToLandingView() {
        SearchUserPresenter temp = (SearchUserPresenter) searchUserPresenter;
        temp.switchToLandingView();
    }

    public void switchToMeView() {
        SearchUserPresenter temp = (SearchUserPresenter) searchUserPresenter;
        temp.switchToMeView();
    }

}
