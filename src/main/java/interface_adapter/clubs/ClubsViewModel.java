package interface_adapter.clubs;

import interface_adapter.ViewModel;
import interface_adapter.search_user.SearchUserState;

public class ClubsViewModel extends ViewModel<ClubsState> {

    public ClubsViewModel() {
        super("clubs");
        setState(new ClubsState());
    }
}
