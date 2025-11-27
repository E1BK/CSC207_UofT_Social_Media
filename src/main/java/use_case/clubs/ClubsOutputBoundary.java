package use_case.clubs;

public interface ClubsOutputBoundary {

    void prepareSuccessView(ClubsOutputData clubsOutputData);

    void prepareFailView(ClubsInputData clubsInputData);
}
