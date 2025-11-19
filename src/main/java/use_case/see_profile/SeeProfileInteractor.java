package use_case.see_profile;

import interface_adapter.search_user.SearchUserPresenter;
import interface_adapter.see_profile.SeeProfilePresenter;

public class SeeProfileInteractor implements SeeProfileInputBoundary {

    private final SeeProfileUserDataAccessInterface seeProfileUserDataAccessInterface;
    private final SeeProfileOutputBoundary seeProfilePresenter;

    public SeeProfileInteractor(SeeProfileUserDataAccessInterface seeProfileUserDataAccessInterface,
                                SeeProfileOutputBoundary seeProfilePresenter) {
        this.seeProfileUserDataAccessInterface = seeProfileUserDataAccessInterface;
        this.seeProfilePresenter = seeProfilePresenter;
    }


    /**
     * This method takes the <username> of the user from
     * <profileInputData>, searches for the corresponding user in the
     * database, and displays all their posts.
     * @param profileInputData
     */
    @Override
    public void execute(SeeProfileInputData profileInputData) {

    }

    public void switchToHomeView() {
        SeeProfilePresenter temp = (SeeProfilePresenter) seeProfilePresenter;
        temp.switchToHomeView();
    }

    public void switchToPeopleView() {
        SeeProfilePresenter temp = (SeeProfilePresenter) seeProfilePresenter;
        temp.switchToPeopleView();
    }
}