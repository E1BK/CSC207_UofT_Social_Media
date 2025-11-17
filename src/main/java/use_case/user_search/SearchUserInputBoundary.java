package use_case.user_search;

public interface SearchUserInputBoundary {

    // run the search
    void execute(SearchUserInputData inputData);
}