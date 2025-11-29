package use_case.landing;

public interface LandingInputBoundary {
    void execute();
    void switchToPeopleView();
    void switchToMeView();
    void switchToClubsView();
}
