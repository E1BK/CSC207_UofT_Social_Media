package use_case.user_search;

public class SearchUserInputData {

    private final String query; //search keyword

    public SearchUserInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}