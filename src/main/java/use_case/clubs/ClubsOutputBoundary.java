package use_case.clubs;

import entity.Club;

public interface ClubsOutputBoundary {

    void prepareSuccessView(ClubsOutputData clubsOutputData);

    void prepareFailView(String searchQuery);
}
