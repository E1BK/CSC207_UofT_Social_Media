package use_case.landing;

public interface LandingOutputBoundary {
    void prepareSuccessView(LandingOutputData landingOutputData);
    void prepareFailView(String errorMessage);
    void switchToPeopleView();
    void switchToMeView();
    void switchToClubsView();
}
