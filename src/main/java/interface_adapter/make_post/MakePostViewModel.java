package interface_adapter.make_post;

import interface_adapter.ViewModel;

public class MakePostViewModel extends ViewModel<MakePostState> {

    public MakePostViewModel() {
        super("make_post");
        setState(new MakePostState());
    }
}
