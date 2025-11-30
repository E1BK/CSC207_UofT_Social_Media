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
        ArrayList<String> usernames = landingDataAccess.getExistingUsernames();

        ArrayList<PostViewData> posts = new ArrayList<>();
        while(posts.size() < 3){
            Random random = new Random();
            String username = usernames.get(random.nextInt(usernames.size()));
//            String username = "zhaohayd";
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
        }
        LandingOutputData output = new LandingOutputData(posts);
        landingPresenter.prepareSuccessView(output);
    }

    public void switchToPeopleView() {
        landingPresenter.switchToPeopleView();
    }

    public void switchToProfileView() {landingPresenter.switchToProfileView();}

    public void switchToClubsView() {
        landingPresenter.switchToClubsView();
    }
}
