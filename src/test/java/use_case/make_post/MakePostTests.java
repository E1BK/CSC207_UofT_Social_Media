package use_case.make_post;

import entity.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;


public class MakePostTests {

    private MakePostUserDataAccessInterface userGateway;
    private MakePostOutputBoundary presenter;
    private PostFactory postFactory;
    private MakePostInteractor interactor;
    private UserFactory userFactory;

    @Before
    public void setUp() {
        userGateway = Mockito.mock(MakePostUserDataAccessInterface.class);
        presenter = Mockito.mock(MakePostOutputBoundary.class);
        postFactory = Mockito.mock(PostFactory.class);
        userFactory = Mockito.mock(UserFactory.class);
        interactor = new MakePostInteractor(userGateway, presenter, userFactory, postFactory);
    }

    @Test
    public void blankTitle_callsFailView_andStops() {
        MakePostInputData in = new MakePostInputData("alice", "", "body");

        interactor.execute(in);

        Mockito.verify(presenter)
                .prepareFailView("Title or Body cannot be blank");
        Mockito.verifyNoInteractions(userGateway, postFactory);
    }

    @Test
    public void blankBody_callsFailView_andStops() {
        MakePostInputData in = new MakePostInputData("alice", "title", "");

        interactor.execute(in);

        Mockito.verify(presenter)
                .prepareFailView("Title or Body cannot be blank");
        Mockito.verifyNoInteractions(userGateway, postFactory);
    }

    @Test
    public void userNotFound_callsFailView() {
        MakePostInputData in = new MakePostInputData("alice", "t", "b");
        Mockito.when(userGateway.getUserInfo("alice")).thenReturn(null);

        interactor.execute(in);

        Mockito.verify(presenter).prepareFailView("User not found.");
        Mockito.verify(userGateway, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void exceptionLoadingUser_callsFailView() {
        MakePostInputData in = new MakePostInputData("alice", "t", "b");
        Mockito.when(userGateway.getUserInfo("alice"))
                .thenThrow(new RuntimeException("db down"));

        interactor.execute(in);

        Mockito.verify(presenter)
                .prepareFailView(Mockito.startsWith("Failed to load user: "));
    }

    @Test
    public void happyPath_createsPostWithMaxIdPlusOne_andCallsSuccess() {
        // input
        MakePostInputData in =
                new MakePostInputData("alice", "new title", "new body");

        User user = new User("alice", "pw", "alice@email.com", "Alice");

        // first post: id 3  -> maxId becomes 3 (if condition TRUE)
        Post post1 = new Post(3, "alice", "old1", "old body1", "t1");
        // second post: id 1 -> 1 > 3 is FALSE, so branch FALSE is hit
        Post post2 = new Post(1, "alice", "old2", "old body2", "t2");

        user.getPosts().add(post1);
        user.getPosts().add(post2);

        Mockito.when(userGateway.getUserInfo("alice")).thenReturn(user);

        // post that the factory will create (id should be 4 = maxId+1)
        Post created = new Post(
                4,                   // post_id
                "alice",
                "new title",
                "new body",
                "t2"
        );
        Mockito.when(postFactory.create(
                Mockito.eq("alice"),
                Mockito.eq(4),
                Mockito.eq("new title"),
                Mockito.eq("new body"),
                Mockito.anyString()   // time from Instant.now().toString()
        )).thenReturn(created);

        // run
        interactor.execute(in);

        // user should be saved
        Mockito.verify(userGateway).save(user);

        // presenter should receive correct view data
        ArgumentCaptor<MakePostOutputData> cap =
                ArgumentCaptor.forClass(MakePostOutputData.class);
        Mockito.verify(presenter).prepareSuccessView(cap.capture());

        PostViewData view = cap.getValue().getNewPost();
        assertEquals("username mismatch", "alice", view.getUsername());
        assertEquals("post id mismatch", 4, view.getPost_id());
        assertEquals("title mismatch", "new title", view.getTitle());

    }

    @Test
    public void exceptionSavingUser_callsFailView() {
        MakePostInputData in = new MakePostInputData("alice", "t", "b");

        User user = new User("alice", "pw", "alice@email.com", "Alice");
        Mockito.when(userGateway.getUserInfo("alice")).thenReturn(user);

        Mockito.when(postFactory.create(
                Mockito.anyString(),
                Mockito.anyInt(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()
        )).thenReturn(new Post(1, "alice", "t", "b", "t"));

        Mockito.doThrow(new RuntimeException("disk full"))
                .when(userGateway).save(user);

        interactor.execute(in);

        Mockito.verify(presenter)
                .prepareFailView(Mockito.startsWith("Failed to save user: "));
    }

    @Test
    public void postViewData_gettersWork() {
        ArrayList<Comment> comments = new ArrayList<>();
        PostViewData dto = new PostViewData("alice", 4, "t", "b", "date", comments);

        assertEquals("alice", dto.getUsername());
        assertEquals(4, dto.getPost_id());
        assertEquals("t", dto.getTitle());
        assertEquals("b", dto.getBody());
        assertEquals("date", dto.getPost_date());
        assertSame(comments, dto.getComments());
    }
}
