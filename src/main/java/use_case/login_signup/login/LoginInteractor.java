package use_case.login_signup.login;

import entity.Post;
import entity.User;
import use_case.make_post.PostViewData;

import java.util.ArrayList;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.getUserInfo(loginInputData.getUsername());

                userDataAccessObject.setCurrentUsername(username);
                ArrayList<PostViewData> postData = new ArrayList<> ();

                for  (Post post : user.getPosts()) {
                    postData.add(new PostViewData(post.getUsername(),
                            post.getPost_id(),
                            post.getTitle(),
                            post.getBody(),
                            post.getPost_date(),
                            post.getComments()));
                }

                final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(),
                                                                            user.getPassword(),
                                                                            user.getBio(),
                                                                            user.getEmail(),
                                                                            postData);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
