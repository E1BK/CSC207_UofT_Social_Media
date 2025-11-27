package use_case.clubs;

import entity.Club;
import entity.ClubFactory;
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

        // this line is what calls the database
        // Club foundClub = clubsDataAccessObject.search(searchQuery);

        // temporary solution until database is implemented:
        ArrayList<Club> allClubs = new ArrayList<>();
        ClubFactory tempClubFactory = new ClubFactory();
        allClubs.add(tempClubFactory.create("CSSU", "The Computer Science Student Union (CSSU) is the official student government for all undergraduate Computer Science students at the University of Toronto's St. George campus, run by an elected student council dedicated to enhancing the academic, professional, and social lives of its members. It serves as a crucial liaison between the student body, the Department of Computer Science (DCS) faculty, and the tech industry, advocating for student interests and organizing career-focused initiatives like tech seminars and industry workshops. Socially, the Union fosters a strong community through various events, including a First-Year BBQ, pub nights, and game events, helping students network and build support systems. Furthermore, the CSSU manages the student lounge (BA2250) in the Bahen Centre, providing a central, accessible hub for studying, socializing, and peer assistance, which underscores its commitment to the daily well-being of the CS undergraduate community."));
        Club foundClub = null;

        for (Club c: allClubs) {
            if (c.getName().equals(clubsInputData.getSearchQuery())) {
                foundClub = c;
            }
        }

        if (foundClub == null) {
            clubsPresenter.prepareFailView(clubsInputData.getSearchQuery());
        } else {
            ClubsOutputData outputData = new ClubsOutputData(foundClub.getName());
            clubsPresenter.prepareSuccessView(outputData);
        }
    }

    public void switchToLandingView() {
        ClubsPresenter temp = (ClubsPresenter) clubsPresenter;
        temp.switchToLandingView();
    }
}
