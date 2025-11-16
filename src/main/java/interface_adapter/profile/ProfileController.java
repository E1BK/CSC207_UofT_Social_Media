package interface_adapter.profile;

// import use_case. <<< data boundries to controll data between two things >>>

import entity.User;
import use_case.make_post.MakePostInteractor;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;
import use_case.profile.ProfileInteractor;
import use_case.search_user.SearchUserInteractor;

public class ProfileController {

    private final ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    public void execute(User user) {
        final ProfileInputData profileInputData = new ProfileInputData(user.getUsername(),
                                                                           user.getEmail(),
                                                                           user.getBio(),
                                                                           user.getPosts());

        profileInteractor.execute(profileInputData);
    }

    public void switchToProfileView() {
        ProfileInteractor temp = (ProfileInteractor) profileInteractor;
        temp.switchToProfileView();
    }
}
