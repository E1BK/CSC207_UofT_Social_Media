package use_case.clubs;

import entity.Club;

public interface ClubsOutputBoundary {

    void prepareSuccessView(Club foundClub);

    void prepareFailView(String searchQuery);
}
