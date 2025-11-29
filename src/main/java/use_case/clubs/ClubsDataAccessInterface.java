package use_case.clubs;

import entity.Club;

import java.util.ArrayList;

public interface ClubsDataAccessInterface {

    /**
     * Search the database for a club with the same name as searchQuery.
     * @return a String that contains the club's name and description.
     * If not found, return null.
     */
    public ArrayList<Club> getClubs();
    public void addClub(Club club);
}
