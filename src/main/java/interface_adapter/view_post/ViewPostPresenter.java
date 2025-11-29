package interface_adapter.view_post;

import interface_adapter.ViewManagerModel;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;

public class ViewPostPresenter implements ViewPostOutputBoundary {

    private final ViewPostViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewPostPresenter(ViewManagerModel viewManagerModel,
                             ViewPostViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(ViewPostOutputData data) {
        ViewPostState state = viewModel.getState();

        state.setUsername(data.getUsername());
        state.setPostId(data.getPostId());
        state.setPostTitle(data.getPostTitle());
        state.setPostBody(data.getPostBody());
        state.setCommentIds(data.getCommentIds());
        state.setCommentBodies(data.getCommentBodies());
        state.setCommentLikes(data.getCommentLikes());
        state.setErrorMessage("");

        viewModel.setState(state);
        viewModel.firePropertyChange();

        // Show PostView
        viewManagerModel.setState(viewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        ViewPostState state = viewModel.getState();
        state.setErrorMessage(errorMessage);
        viewModel.setState(state);
        viewModel.firePropertyChange();
    }
}