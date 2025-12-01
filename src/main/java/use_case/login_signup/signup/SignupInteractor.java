package use_case.login_signup.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists");
        }
        else if (!signupInputData.getEmail().contains("@mail.utoronto.ca")) {
            userPresenter.prepareFailView("Must use UofT email (@mail.utoronto.ca)");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if ("".equals(signupInputData.getPassword())) {
            userPresenter.prepareFailView("New password cannot be empty");
        }
        else if ("".equals(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username cannot be empty");
        }
        else if ("".equals(signupInputData.getEmail())) {
            userPresenter.prepareFailView("Email cannot be empty");
        }
        else if ("".equals(signupInputData.getName())) {
            userPresenter.prepareFailView("Name cannot be empty");
        }
        else if (!(signupInputData.getUsername().length() > 7)){
            userPresenter.prepareFailView("Username must be at least 8 characters or digits");
        }
        else if (!(signupInputData.getPassword().length() > 5)){
            userPresenter.prepareFailView("Password must be at least 6 characters or digits");
        }
        else if (signupInputData.getUsername().contains(" ")) {
            userPresenter.prepareFailView("Username cannot contain spaces");
        }
        else {
            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getEmail(), signupInputData.getName());
            userDataAccessObject.createUser(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), user.getPassword());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
