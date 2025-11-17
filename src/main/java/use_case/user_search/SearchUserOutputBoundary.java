package use_case.user_search;

public interface SearchUserOutputBoundary {

    // On success,send results to presenter
    void prepareSuccessView(SearchUserOutputData outputData);

    // On failure (e.g.no user), show message
    void prepareFailView(String message);
}