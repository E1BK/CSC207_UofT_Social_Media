package interface_adapter.searchUser;

import entity.User;
import use_case.search_user.SearchUserOutputBoundary;

public class SearchUserPresenter implements SearchUserOutputBoundary {

    @Override
    public void prepareSuccessView(User user) {
    }

    @Override
    public void prepareFailView() {
    }
}
