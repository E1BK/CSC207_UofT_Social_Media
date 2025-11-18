package use_case.see_profile;

public interface SeeProfileOutputBoundary {
    public void prepareSuccessView(SeeProfileOutputData profileOutputData);
    public void prepareFailView(String errorMessage);
}
