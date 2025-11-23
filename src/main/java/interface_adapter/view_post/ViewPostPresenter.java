// Ioane
package interface_adapter.view_post;

import interface_adapter.ViewManagerModel;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;

public class ViewPostPresenter implements ViewPostOutputBoundary {

    private final ViewPostViewModel viewPostViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewPostPresenter(ViewManagerModel viewManagerModel,
                             ViewPostViewModel viewPostViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewPostViewModel = viewPostViewModel;
    }

    @Override
    public void prepareSuccessView(ViewPostOutputData data) {
        ViewPostState state = viewPostViewModel.getState();

        state.setPostTitle(data.getPostTitle());
        state.setPostBody(data.getPostBody());
        state.setCommentIds(data.getCommentIds());
        state.setCommentBodies(data.getCommentBodies());
        state.setCommentLikes(data.getCommentLikes());
        state.setErrorMessage("");

        viewPostViewModel.setState(state);
        viewPostViewModel.firePropertyChange();

        viewManagerModel.setState(viewPostViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        ViewPostState state = viewPostViewModel.getState();
        state.setErrorMessage(errorMessage);

        viewPostViewModel.setState(state);
        viewPostViewModel.firePropertyChange();
    }
}