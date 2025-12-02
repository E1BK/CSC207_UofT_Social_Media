package use_case.landing;

import entity.Post;
import entity.User;
import use_case.make_post.PostViewData;

import java.util.*;

public class LandingInteractor implements LandingInputBoundary{
    private final LandingDataAccessInterface landingDataAccess;
    private final LandingOutputBoundary landingPresenter;

    public LandingInteractor(LandingDataAccessInterface landingDataAccess, LandingOutputBoundary landingPresenter) {
        this.landingDataAccess = landingDataAccess;
        this.landingPresenter = landingPresenter;
    }


    @Override
    public void execute() {
        try {
            ArrayList<String> usernames = landingDataAccess.getExistingUsernames();
            Random random = new Random();
            ArrayList<PostViewData> posts = new ArrayList<>();
            ArrayList<String> addedUsers = new ArrayList<>();
            int attemps = 0;
            while (posts.size() < 3 && attemps < 30) {
                attemps++;
                String username = usernames.get(random.nextInt(usernames.size()));
                if (addedUsers.contains(username)) {
                    continue;
                }
                User currUser = landingDataAccess.getUserInfo(username);
                if (currUser.getPosts().isEmpty()) {
                    continue;
                }
                Post newestPost = currUser.getPosts().getLast();
                PostViewData newestPostData = new PostViewData(newestPost.getUsername(),
                        newestPost.getPost_id(),
                        newestPost.getTitle(),
                        newestPost.getBody(),
                        newestPost.getPost_date(),
                        newestPost.getComments());
                posts.add(newestPostData);
                addedUsers.add(username);
            }
            LandingOutputData output = new LandingOutputData(posts);
            landingPresenter.prepareSuccessView(output);
        }
        catch (Exception e) {
            landingPresenter.prepareFailView(e.getMessage());
        }
    }

    public void switchToPeopleView() {
        landingPresenter.switchToPeopleView();
    }

    public void switchToProfileView() {landingPresenter.switchToProfileView();}

    public void switchToClubsView() {
        landingPresenter.switchToClubsView();
    }
}
