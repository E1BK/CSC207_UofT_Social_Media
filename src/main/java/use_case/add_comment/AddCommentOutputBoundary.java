package use_case.add_comment;

public interface AddCommentOutputBoundary {
    void prepareSuccessView(AddCommentOutputData outputData);
    void prepareFailView(String errorMessage);
}
