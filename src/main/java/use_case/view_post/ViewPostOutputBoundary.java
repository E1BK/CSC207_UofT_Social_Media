package use_case.view_post;

public interface ViewPostOutputBoundary {
    void prepareSuccessView(ViewPostOutputData outputData);
    void prepareFailView(String errorMessage);
}
