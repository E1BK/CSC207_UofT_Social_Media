package entity;

public class ClubFactory {

    public Club create(String name, String statementOfPurpose) {
        return new Club(name, statementOfPurpose);
    }
}
