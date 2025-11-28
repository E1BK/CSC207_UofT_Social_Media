package entity;

public class Club {

    private final String name;
    private final String statementOfPurpose;

    public Club(String name, String statementOfPurpose) {
        this.name = name;
        this.statementOfPurpose = statementOfPurpose;
    }

    public String getName() {
        return name;
    }

    public String getStatementOfPurpose() {
        return statementOfPurpose;
    }
}
