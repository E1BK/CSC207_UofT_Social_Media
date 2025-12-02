// Ioane
package use_case.view_post;

public interface ViewPostOutputBoundary {

    void prepareSuccessView(ViewPostOutputData data);

    void prepareFailView(String errorMessage);
}