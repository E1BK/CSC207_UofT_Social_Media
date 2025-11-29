package use_case.clubs;

import java.util.ArrayList;

public interface ClubsDataAccessInterface {

    /**
     * Search the database for a club with the same name as searchQuery.
     * @return a String that contains the club's name and description.
     * If not found, return null.
     */
    public String search(String searchQuery);
}
