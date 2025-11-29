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

    // Russel: new execute method
    @Override
    public void execute(SearchUserInputData searchUserInputData) {
        try {
            User foundUser =
                    searchUserDataAccessObject.getUserInfo(searchUserInputData.getUsername());
            searchUserPresenter.prepareSuccessView(foundUser);
        } catch (RuntimeException ex) {
            System.out.println("SearchUserInteractor error: " + ex.getMessage());
            searchUserPresenter.prepareFailView();
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
