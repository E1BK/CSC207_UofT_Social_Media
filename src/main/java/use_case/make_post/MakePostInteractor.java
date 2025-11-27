package use_case.make_post;

import entity.Post;
import entity.PostFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.landing.MakePostPresenter;

import java.time.Instant;
import java.util.ArrayList;

public class MakePostInteractor implements MakePostInputBoundary{
    private final MakePostUserDataAccessInterface makePostUserDataAccess;
    private final MakePostOutputBoundary makePostPresenter;
    private final UserFactory userFactory;
    private final PostFactory postFactory;

    public MakePostInteractor(
            MakePostUserDataAccessInterface makePostUserDataAccessInterface,
            MakePostOutputBoundary makePostPresenter,
            UserFactory userFactory,
            PostFactory postFactory) {
        this.makePostUserDataAccess = makePostUserDataAccessInterface;
        this.makePostPresenter = makePostPresenter;
        this.userFactory = userFactory;
        this.postFactory = postFactory;
    }

    /**
     * This method makes a post using the <title>, <body>, etc
     * from <makePostInputData>.
     * @param makePostInputData
     */
    @Override
    public void execute(MakePostInputData makePostInputData) {
        String username = makePostInputData.getUsername();
        String title = makePostInputData.getTitle();
        String body = makePostInputData.getBody();
        String time = Instant.now().toString();

        User user;

        try {
            user = makePostUserDataAccess.getUserInfo(username);
            if (user == null) {
                makePostPresenter.prepareFailView("User not found.");
                return;
            }
        } catch (Exception e) {
            makePostPresenter.prepareFailView("Failed to load user: " + e.getMessage());
            return;
        }

        int maxId = 0;
        ArrayList<Post> posts = user.getPosts();
        for (Post post : posts) {
            if (post.getPost_id() > maxId) {
                maxId = post.getPost_id();
            }
        }

        Post newPost = postFactory.create(username, maxId+1, title, body, time);
        user.addPost(newPost);

        try {
            makePostUserDataAccess.save(user);   // or modifyUser
        } catch (Exception e) {
            makePostPresenter.prepareFailView("Failed to save user: " + e.getMessage());
            return;
        }

        MakePostOutputData output = new MakePostOutputData(newPost.getPost_id(),
                                                            newPost.getUsername(),
                                                            newPost.getTitle(),
                                                            newPost.getBody(),
                                                            newPost.getPost_date());
        makePostPresenter.prepareSuccessView(output);
    }

    // hasan: this method must be here, because "making a post" and
    // "switching to People view" occur on the same screen (i.e. on the Landing Page!)
    public void switchToPeopleView() {
        MakePostPresenter temp = (MakePostPresenter) makePostPresenter;
        temp.switchToPeopleView();
    }

    public void switchToMeView() {
        MakePostPresenter temp = (MakePostPresenter) makePostPresenter;
        temp.switchToMeView();
    }

    public void switchToClubsView() {
        MakePostPresenter temp = (MakePostPresenter) makePostPresenter;
        temp.switchToClubsView();
    }
}