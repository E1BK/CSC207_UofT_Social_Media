package use_case.view_post;

public interface ViewPostOutputBoundary {
    void prepareSuccessView(ViewPostOutputData viewPostOutputData);
    void prepareFailView(String errorMessage);
}
