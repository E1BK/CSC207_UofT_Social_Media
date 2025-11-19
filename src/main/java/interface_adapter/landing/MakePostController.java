// hasan
package interface_adapter.landing;

import entity.Post;
import use_case.make_post.MakePostInputBoundary;
import use_case.make_post.MakePostInputData;
import use_case.make_post.MakePostInteractor;

public class MakePostController {

    private final MakePostInputBoundary makePostInteractor;

    public MakePostController(MakePostInputBoundary makePostInteractor) {

        this.makePostInteractor = makePostInteractor;
    }

    /**
     * Executes the Make Post Use Case.
     * @param
     */
    public void execute(String username, String post_title, String post_body) {
        final MakePostInputData makePostInputData = new MakePostInputData(username, post_title, post_body);

        makePostInteractor.execute(makePostInputData);
    }

    public void switchToPeopleView() {
        MakePostInteractor temp = (MakePostInteractor) makePostInteractor;
        temp.switchToPeopleView();
    }
}
