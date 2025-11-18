package use_case.make_post;

import entity.PostFactory;
import entity.UserFactory;
import interface_adapter.landing.MakePostPresenter;

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

    }

    // hasan: this method must be here, because "making a post" and
    // "switching to People view" occur on the same screen (i.e. on the Landing Page!)
    public void switchToPeopleView() {
        MakePostPresenter temp = (MakePostPresenter) makePostPresenter;
        System.out.println("bye");
        temp.switchToPeopleView();
    }
}