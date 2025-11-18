package interface_adapter.see_profile;

import use_case.search_user.SearchUserInputBoundary;
import use_case.see_profile.SeeProfileInputBoundary;
import use_case.see_profile.SeeProfileInputData;

public class SeeProfileController {

    private SeeProfileInputBoundary seeProfileInteractor;

    public SeeProfileController(SeeProfileInputBoundary seeProfileInteractor) {
        this.seeProfileInteractor = seeProfileInteractor;
    }

    public void execute(SeeProfileInputData seeProfileInputData) {
        seeProfileInteractor.execute(seeProfileInputData);
    }
}
