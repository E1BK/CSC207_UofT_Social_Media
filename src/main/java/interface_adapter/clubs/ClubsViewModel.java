package interface_adapter.clubs;

import entity.Club;
import interface_adapter.ViewModel;

public class ClubsViewModel extends ViewModel<ClubsState> {

    public ClubsViewModel() {
        super("clubs");
        setState(new ClubsState());
    }

}
