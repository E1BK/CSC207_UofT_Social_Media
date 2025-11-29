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

        ClubFactory tempClubFactory = new ClubFactory(); // REMOVE THIS LINE LATER


        // this line is what calls the database

//        clubsDataAccessObject.addClub(tempClubFactory.create("CSSU", "The Computer Science Student Union (CSSU) is the official student government for all undergraduate Computer Science students at the University of Toronto's St. George campus, run by an elected student council dedicated to enhancing the academic, professional, and social lives of its members. It serves as a crucial liaison between the student body, the Department of Computer Science (DCS) faculty, and the tech industry, advocating for student interests and organizing career-focused initiatives like tech seminars and industry workshops. Socially, the Union fosters a strong community through various events, including a First-Year BBQ, pub nights, and game events, helping students network and build support systems. Furthermore, the CSSU manages the student lounge (BA2250) in the Bahen Centre, providing a central, accessible hub for studying, socializing, and peer assistance, which underscores its commitment to the daily well-being of the CS undergraduate community."));
//        clubsDataAccessObject.addClub(tempClubFactory.create("UTMIST", "The University of Toronto Machine Intelligence Student Team (UTMIST) is the premier undergraduate machine learning club at the University of Toronto, aiming to “clear the mist” around artificial intelligence. As one of North America's largest student-led AI groups, UTMIST provides a comprehensive ecosystem for students of all skill levels to engage with AI. They offer diverse activities including beginner-friendly workshops through their ML Fundamentals program, in-house engineering and research design teams, an AI startup incubator, and major events like the annual EigenAI conference and a hackathon. The club connects students with academic resources like the Vector Institute and industry opportunities at top tech companies. Founded in 2017, UTMIST empowers its thousands of members and over 170 executives with practical experience and career acceleration in a rapidly evolving field."));
//        clubsDataAccessObject.addClub(tempClubFactory.create("UTFR", "The University of Toronto Formula Racing Team (UTFR) is a student-run design team that annually designs, builds, and races a formula-style electric vehicle in the Formula SAE (FSAE) competitions around the world. Founded in 1996, the team provides students with hands-on experience in engineering design, project management, and business operations, preparing them for their careers. The 120-plus member team, which operates on a 12-month cycle from design to competition, has successfully embraced innovation, notably transitioning to a fully electric powertrain and developing cutting-edge autonomous driving capabilities, a unique achievement among North American teams. This continuous pursuit of engineering excellence has earned them numerous awards and a strong reputation within the international FSAE series."));

        ArrayList<Club> allDBClubs = clubsDataAccessObject.getClubs();
        for (Club c: allDBClubs) {
            System.out.println(c.getName());
            System.out.println(c.getStatementOfPurpose());
        }


//        // temporary solution until database is implemented:
//        ArrayList<Club> allClubs = new ArrayList<>();
//        allClubs.add(tempClubFactory.create("CSSU", "" +
//                "The Computer Science Student Union (CSSU) is the official student government for all undergraduate Computer Science students at the University of Toronto's St. George campus, run by an elected student council dedicated to enhancing the academic, professional, and social lives of its members. It serves as a crucial liaison between the student body, the Department of Computer Science (DCS) faculty, and the tech industry, advocating for student interests and organizing career-focused initiatives like tech seminars and industry workshops. Socially, the Union fosters a strong community through various events, including a First-Year BBQ, pub nights, and game events, helping students network and build support systems. Furthermore, the CSSU manages the student lounge (BA2250) in the Bahen Centre, providing a central, accessible hub for studying, socializing, and peer assistance, which underscores its commitment to the daily well-being of the CS undergraduate community."));
//        allClubs.add(tempClubFactory.create("",
//                "The University of Toronto Machine Intelligence Student Team (UTMIST) is the premier undergraduate machine learning club at the University of Toronto, aiming to “clear the mist” around artificial intelligence. As one of North America's largest student-led AI groups, UTMIST provides a comprehensive ecosystem for students of all skill levels to engage with AI. They offer diverse activities including beginner-friendly workshops through their ML Fundamentals program, in-house engineering and research design teams, an AI startup incubator, and major events like the annual EigenAI conference and a hackathon. The club connects students with academic resources like the Vector Institute and industry opportunities at top tech companies. Founded in 2017, UTMIST empowers its thousands of members and over 170 executives with practical experience and career acceleration in a rapidly evolving field."));
//        allClubs.add(tempClubFactory.create("", "" +
//                "The University of Toronto Formula Racing Team (UTFR) is a student-run design team that annually designs, builds, and races a formula-style electric vehicle in the Formula SAE (FSAE) competitions around the world. Founded in 1996, the team provides students with hands-on experience in engineering design, project management, and business operations, preparing them for their careers. The 120-plus member team, which operates on a 12-month cycle from design to competition, has successfully embraced innovation, notably transitioning to a fully electric powertrain and developing cutting-edge autonomous driving capabilities, a unique achievement among North American teams. This continuous pursuit of engineering excellence has earned them numerous awards and a strong reputation within the international FSAE series."));
        Club foundClub = null;

        for (Club c: allDBClubs) {
            if (c.getName().equals(clubsInputData.getSearchQuery())) {
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
