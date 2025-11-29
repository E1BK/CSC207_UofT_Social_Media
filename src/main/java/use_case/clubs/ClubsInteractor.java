package use_case.clubs;

import entity.Club;
import interface_adapter.clubs.ClubsPresenter;

import java.util.ArrayList;

public class ClubsInteractor implements ClubsInputBoundary{

    private ClubsOutputBoundary clubsPresenter;
    private ClubsDataAccessInterface clubsDataAccessObject;

    public ClubsInteractor(ClubsOutputBoundary clubsPresenter, ClubsDataAccessInterface clubsDataAccessObject) {
        this.clubsPresenter = clubsPresenter;
        this.clubsDataAccessObject = clubsDataAccessObject;
    }

    @Override
    public void execute(ClubsInputData clubsInputData) {

        ArrayList<Club> allDBClubs = clubsDataAccessObject.getClubs(); // this line is what calls the database
         Club foundClub = null;

        for (Club c: allDBClubs) {
            if (c.getName().equalsIgnoreCase(clubsInputData.getSearchQuery())) {
                foundClub = c;
            }
        }

        if (foundClub == null) {
            clubsPresenter.prepareFailView(clubsInputData);
        } else {
            ClubsOutputData outputData = new ClubsOutputData(foundClub.getName(), foundClub.getStatementOfPurpose());
            clubsPresenter.prepareSuccessView(outputData);
        }
    }

    public void switchToLandingView() {
        ClubsPresenter temp = (ClubsPresenter) clubsPresenter;
        temp.switchToLandingView();
    }
}
