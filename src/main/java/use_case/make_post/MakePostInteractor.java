package use_case.make_post;

import entity.PostFactory;
import entity.UserFactory;

public class MakePostInteractor implements MakePostInputBoundary{
    private final MakePostUserDataAccessInterface makePostUserDataAccess;
    private final MakePostOutputBoundary makePostPresenter;
    private final UserFactory userFactory;
    private final PostFactory postFactory;

    public MakePostInteractor(MakePostUserDataAccessInterface makePostUserDataAccessInterface,
                              MakePostOutputBoundary makePostPresenter, UserFactory userFactory, PostFactory postFactory) {
        this.makePostUserDataAccess = makePostUserDataAccessInterface;
        this.makePostPresenter = makePostPresenter;
        this.userFactory = userFactory;
        this.postFactory = postFactory;
    }

    @Override
    public void execute(MakePostInputData makePostInputData) {

    }
}