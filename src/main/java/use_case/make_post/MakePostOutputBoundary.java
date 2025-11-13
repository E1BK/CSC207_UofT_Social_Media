package use_case.make_post;

public interface MakePostOutputBoundary {
    public void prepareSuccessView(MakePostOutputData makePostOutputData);
    public void prepareFailView(String errorMessage);
}