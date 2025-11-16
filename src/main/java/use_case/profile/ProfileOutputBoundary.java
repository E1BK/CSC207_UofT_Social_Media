package use_case.profile;

public interface ProfileOutputBoundary {
    public void prepareSuccessView(ProfileOutputData makePostOutputData);
    public void prepareFailView(String errorMessage);
}