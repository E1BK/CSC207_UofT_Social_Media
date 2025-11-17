package use_case.user_search;

import java.util.List;

// holds the list of matched usernames
public class SearchUserOutputData {

    private final List<String> matchedUsernames; // 匹配到的用户名. matched usernames

    public SearchUserOutputData(List<String> matchedUsernames) {
        this.matchedUsernames = matchedUsernames;
    }

    public List<String> getMatchedUsernames() {
        return matchedUsernames;
    }
}