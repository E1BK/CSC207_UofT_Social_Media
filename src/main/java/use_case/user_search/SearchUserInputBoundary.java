package use_case.user_search;

// Use case input boundary
public interface SearchUserInputBoundary {

    // 执行搜索. run the search
    void execute(SearchUserInputData inputData);
}