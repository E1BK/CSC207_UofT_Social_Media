package interface_adapter.see_profile;

import use_case.search_user.SearchUserInputBoundary;
import use_case.see_profile.SeeProfileInputBoundary;
import use_case.see_profile.SeeProfileInputData;
import use_case.see_profile.SeeProfileInteractor;

public class SeeProfileController {

    private SeeProfileInputBoundary seeProfileInteractor;

    public SeeProfileController(SeeProfileInputBoundary seeProfileInteractor) {
        this.seeProfileInteractor = seeProfileInteractor;
    }

    public void execute(SeeProfileInputData seeProfileInputData) {
        seeProfileInteractor.execute(seeProfileInputData);
    }

    public void switchToHomeView() {
        SeeProfileInteractor temp = (SeeProfileInteractor) seeProfileInteractor;
        temp.switchToHomeView();
    }

    public void switchToPeopleView() {
        SeeProfileInteractor temp = (SeeProfileInteractor) seeProfileInteractor;
        temp.switchToPeopleView();
    }
}
