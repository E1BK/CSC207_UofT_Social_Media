package use_case.my_profile.profile_change_password;

import entity.User;
import entity.UserFactory;

/**
 * The Change Password Interactor.
 */
public class MyProfileChangePasswordInteractor implements MyProfileChangePasswordInputBoundary {
    private final MyProfileChangePasswordUserDataAccessInterface userDataAccessObject;
    private final MyProfileChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public MyProfileChangePasswordInteractor(MyProfileChangePasswordUserDataAccessInterface myProfileChangePasswordDataAccess,
                                    MyProfileChangePasswordOutputBoundary myProfileChangePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = myProfileChangePasswordDataAccess;
        this.userPresenter = myProfileChangePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * This method changes the users password or bio from the <bio> and <password> from
     * <MyProfileChangePasswordInputData>
     * @param inputData the input data for this use case
     */
    @Override
    public void execute(MyProfileChangePasswordInputData inputData) {
        if ("".equals(inputData.getPassword())) {
            userPresenter.prepareFailView("New password cannot be empty");
        }
        else {
            User user = userDataAccessObject.getUserInfo(inputData.getUsername());

            if (inputData.getPassOrBio().equals("password")) {
                final User newUser = userFactory.create(inputData.getUsername(),
                        inputData.getPassword(),
                        user.getBio(),
                        user.getEmail(),
                        user.getName(),
                        user.getPosts());
                userDataAccessObject.changePassword(newUser);
            } else if (inputData.getPassOrBio().equals("bio")) {
                final User newUser = userFactory.create(inputData.getUsername(),
                        user.getPassword(),
                        inputData.getBio(),
                        user.getEmail(),
                        user.getName(),
                        user.getPosts());
                userDataAccessObject.changeBio(newUser);
            }

            final MyProfileChangePasswordOutputData myProfileChangePasswordOutputData =
                    new MyProfileChangePasswordOutputData(user.getName());
            userPresenter.prepareSuccessView(myProfileChangePasswordOutputData);
        }
    }
}
