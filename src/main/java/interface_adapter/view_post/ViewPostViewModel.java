// Ioane
package interface_adapter.view_post;

import interface_adapter.ViewModel;

public class ViewPostViewModel extends ViewModel<ViewPostState> {

    public ViewPostViewModel() {
        super("post");
        setState(new ViewPostState());
    }
}
