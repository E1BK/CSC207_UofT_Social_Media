// Ioane
package interface_adapter.view_post;

import interface_adapter.ViewModel;

/**
 * ViewModel for the ViewPost use case.
 * Holds a ViewPostState and notifies listeners (PostView) on changes.
 */
public class ViewPostViewModel extends ViewModel<ViewPostState> {

    public ViewPostViewModel() {
        // "post" is the card name used by ViewManager / ViewManagerModel
        super("post");
        setState(new ViewPostState());
    }
}
