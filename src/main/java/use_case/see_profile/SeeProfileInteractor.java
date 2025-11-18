package use_case.see_profile;

public class SeeProfileInteractor implements SeeProfileInputBoundary {

    private final SeeProfileUserDataAccessInterface profileUserDataAccessInterface;
    private final SeeProfileOutputBoundary profileOutputBoundary;

    public SeeProfileInteractor(SeeProfileUserDataAccessInterface profileUserDataAccessInterface,
                                SeeProfileOutputBoundary profileOutputBoundary) {
        this.profileUserDataAccessInterface = profileUserDataAccessInterface;
        this.profileOutputBoundary = profileOutputBoundary;
    }


    /**
     * This method takes the <username> of the user from
     * <profileInputData>, searches for the corresponding user in the
     * database, and displays all their posts.
     * @param profileInputData
     */
    @Override
    public void execute(SeeProfileInputData profileInputData) {

    }
}