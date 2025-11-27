package use_case.clubs;

import entity.Club;

import java.util.ArrayList;

public interface ClubsDataAccessInterface {

    /**
     * Search the database for a club with the same name as searchQuery.
     * @return the Club that matches the searchQuery.
     * If not found, return null.
     */
    public Club search(String searchQuery);
}
