package use_case.landing;

public interface LandingOutputBoundary {
    public void prepareSuccessView(LandingOutputData landingOutputData);
    public void prepareFailView(String errorMessage);
}
