package use_case.clubs;

public interface ClubsInputBoundary {

    void execute(ClubsInputData clubsInputData);

    void findClub(String searchQuery);
}
