package use_case.clubs;

public class ClubsOutputData {

    private final String foundClubName;
    private final String foundClubDescription;

    public ClubsOutputData(String foundClubName, String foundClubDescription) {
        this.foundClubName = foundClubName;
        this.foundClubDescription = foundClubDescription;
    }

    public String getFoundClubName() {
        return foundClubName;
    }

    public String getFoundClubDescription() {
        return foundClubDescription;
    }
}
