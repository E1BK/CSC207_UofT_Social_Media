package interface_adapter.clubs;


public class ClubsState {

    private String foundClubName;
    private String foundClubDescription;

    // default no-arg constructor
    public ClubsState() {}

    public ClubsState(String foundClubName, String foundClubDescription) {
        this.setFoundClubName(foundClubName);
        this.setFoundClubDescription(foundClubDescription);
    }


    public String getFoundClubName() {
        return foundClubName;
    }

    public void setFoundClubName(String foundClubName) {
        this.foundClubName = foundClubName;
    }

    public String getFoundClubDescription() {
        return foundClubDescription;
    }

    public void setFoundClubDescription(String foundClubDescription) {
        this.foundClubDescription = foundClubDescription;
    }
}
