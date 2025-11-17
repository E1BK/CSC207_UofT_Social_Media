// hasan
package use_case.search_user;

import entity.User;

public interface SearchUserOutputBoundary {

    void prepareSuccessView(User user);

    void prepareFailView();

}
