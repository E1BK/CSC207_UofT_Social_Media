package use_case.search_user;

public interface SearchUserOutputBoundary {

    void prepareSuccessView(SearchUserOutputData outputData);

    void prepareFailView();

}
