package interface_adapter.clubs;


public class ClubsState {

    private String foundClubName;
//    private String foundClubDescription;

    // default no-arg constructor
    public ClubsState() {}

    public ClubsState(String foundClubName) {
        this.setFoundClubName(foundClubName);
    }


    public String getFoundClubName() {
        return foundClubName;
    }

    public void setFoundClubName(String foundClubName) {
        this.foundClubName = foundClubName;
    }
}
