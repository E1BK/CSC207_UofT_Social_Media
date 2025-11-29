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

    public MyProfileChangePasswordInteractor(MyProfileChangePasswordUserDataAccessInterface myProfileChangePasswordDataAccessInterface,
                                    MyProfileChangePasswordOutputBoundary myProfileChangePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = myProfileChangePasswordDataAccessInterface;
        this.userPresenter = myProfileChangePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(MyProfileChangePasswordInputData myProfileChangePasswordInputData) {
        if ("".equals(myProfileChangePasswordInputData.getPassword())) {
            userPresenter.prepareFailView("New password cannot be empty");
        }
        else {
            final User user = userFactory.create(myProfileChangePasswordInputData.getUsername(),
                    myProfileChangePasswordInputData.getPassword(),
                    myProfileChangePasswordInputData.getBio(),
                    myProfileChangePasswordInputData.getEmail(),
                    myProfileChangePasswordInputData.getName(),
                    myProfileChangePasswordInputData.getPosts());

            userDataAccessObject.changePassword(user);

            final MyProfileChangePasswordOutputData myProfileChangePasswordOutputData =
                    new MyProfileChangePasswordOutputData(user.getName());
            userPresenter.prepareSuccessView(myProfileChangePasswordOutputData);
        }
    }
}
