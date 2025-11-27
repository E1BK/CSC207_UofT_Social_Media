package interface_adapter.clubs;

import entity.Club;

import java.util.ArrayList;

public class ClubsState {

    private ArrayList<Club> clubs;

    // default no-arg constructor
    public ClubsState() {}

    public ClubsState(ArrayList<Club> clubs) {
        this.clubs = clubs;
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }


}
