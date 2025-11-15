package interface_adapter.landing;

import entity.Post;
import use_case.make_post.MakePostInputBoundary;
import use_case.make_post.MakePostInputData;

public class MakePostController {

    private final MakePostInputBoundary makePostInteractor;

    public MakePostController(MakePostInputBoundary makePostInteractor) {

        this.makePostInteractor = makePostInteractor;
    }

    /**
     * Executes the Make Post Use Case.
     * @param p the post
     */
    public void execute(Post p) {
        final MakePostInputData makePostInputData = new MakePostInputData(p.getUsername(), p.getTitle(), p.getBody());

        makePostInteractor.execute(makePostInputData);
    }
}
