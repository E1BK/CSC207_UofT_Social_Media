package use_case.user_search;

import java.util.ArrayList;
import java.util.List;

// 搜索 search logic
public class SearchUserInteractor implements SearchUserInputBoundary {

    private final SearchUserDataAccessInterface userDataAccess; // 访问用户名列表, data access for usernames
    private final SearchUserOutputBoundary presenter;           // 输出给 presenter, sends output to presenter

    public SearchUserInteractor(SearchUserDataAccessInterface userDataAccess,
                                SearchUserOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(SearchUserInputData inputData) {
        String query = inputData.getQuery();

        String trimmedQuery = query.trim().toLowerCase();

        // get all usernames from data access
        List<String> allUsernames = userDataAccess.getAllUsernames();

        List<String> matched = new ArrayList<>();

        // 简单的部分匹配:contains 和 忽略大小写; Simple partial match: contains & ignore case
        for (String username : allUsernames) {
            if (username != null &&
                    username.toLowerCase().contains(trimmedQuery)) {
                matched.add(username);
            }
        }

        // 没有匹配到任何用户名 ,no match found
        if (matched.isEmpty()) {
            presenter.prepareFailView("User Not Found");
        } else {
            // 有结果给presenter, found matches then send to presenter
            SearchUserOutputData outputData = new SearchUserOutputData(matched);
            presenter.prepareSuccessView(outputData);
        }
    }
}