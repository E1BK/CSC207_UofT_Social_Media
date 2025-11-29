package use_case.clubs;

public class ClubsInputData {

    private final String searchQuery;

    public ClubsInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
