package use_case.like_comment;

public interface LikeCommentOutputBoundary {
    void prepareSuccessView(LikeCommentOutputData outputData);
    void prepareFailView(String errorMessage);
}
