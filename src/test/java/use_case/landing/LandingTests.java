package use_case.landing;

import entity.Post;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.make_post.PostViewData;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LandingTests {

    private LandingDataAccessInterface landingDataAccess;
    private LandingOutputBoundary landingPresenter;
    private LandingInteractor interactor;

    @Before
    public void setUp() {
        landingDataAccess = Mockito.mock(LandingDataAccessInterface.class);
        landingPresenter  = Mockito.mock(LandingOutputBoundary.class);
        interactor        = new LandingInteractor(landingDataAccess, landingPresenter);
    }

    // 1) Happy path: one user with posts
    @Test
    public void oneUserWithPosts_returnsNewestPostOnce() {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("alice");

        // user with TWO posts, so "newest" is the SECOND (last) one
        User alice = new User("alice", "pw", "alice@email", "Alice");
        alice.getPosts().add(new Post(1, "alice", "old", "body1", "d1"));
        Post newest = new Post(2, "alice", "new", "body2", "d2");
        alice.getPosts().add(newest);

        Mockito.when(landingDataAccess.getExistingUsernames()).thenReturn(usernames);
        Mockito.when(landingDataAccess.getUserInfo("alice")).thenReturn(alice);

        interactor.execute();

        ArgumentCaptor<LandingOutputData> cap =
                ArgumentCaptor.forClass(LandingOutputData.class);
        Mockito.verify(landingPresenter).prepareSuccessView(cap.capture());

        ArrayList<PostViewData> resultPosts = cap.getValue().getPosts();
        // with a single username, we’ll end up with exactly one post
        assertEquals(1, resultPosts.size());

        PostViewData p = resultPosts.get(0);
        assertEquals("alice", p.getUsername());
        assertEquals(2,       p.getPost_id());
        assertEquals("new",   p.getTitle());
        assertEquals("body2", p.getBody());
        assertEquals("d2",    p.getPost_date());
    }

    // 2) User has NO posts → nothing added, but still success
    @Test
    public void userWithNoPosts_resultsInEmptyList() {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("bob");

        User bob = new User("bob", "pw", "bob@email", "Bob");
        // bob.getPosts() is empty

        Mockito.when(landingDataAccess.getExistingUsernames()).thenReturn(usernames);
        Mockito.when(landingDataAccess.getUserInfo("bob")).thenReturn(bob);

        interactor.execute();

        ArgumentCaptor<LandingOutputData> cap =
                ArgumentCaptor.forClass(LandingOutputData.class);
        Mockito.verify(landingPresenter).prepareSuccessView(cap.capture());

        ArrayList<PostViewData> resultPosts = cap.getValue().getPosts();
        assertTrue(resultPosts.isEmpty());
    }

    // 3) Data access throws → fail view called
    @Test
    public void exceptionInDataAccess_callsFailView() {
        Mockito.when(landingDataAccess.getExistingUsernames())
                .thenThrow(new RuntimeException("boom"));

        interactor.execute();

        Mockito.verify(landingPresenter)
                .prepareFailView("boom");
        // and success view should NOT be called
        Mockito.verify(landingPresenter, Mockito.never())
                .prepareSuccessView(Mockito.any());
    }

    // 4) Optional: multiple users, invariants only (no duplicates, <=3 posts)
    @Test
    public void multipleUsers_neverDuplicatesUsernames_andUpToThreePosts() {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("alice");
        usernames.add("bob");
        usernames.add("carol");

        User alice = new User("alice", "pw", "a@email", "Alice");
        alice.getPosts().add(new Post(1, "alice", "a1", "b1", "d1"));

        User bob = new User("bob", "pw", "b@email", "Bob");
        bob.getPosts().add(new Post(2, "bob", "b1", "b2", "d2"));

        User carol = new User("carol", "pw", "c@email", "Carol");
        carol.getPosts().add(new Post(3, "carol", "c1", "c2", "d3"));

        Mockito.when(landingDataAccess.getExistingUsernames()).thenReturn(usernames);
        Mockito.when(landingDataAccess.getUserInfo("alice")).thenReturn(alice);
        Mockito.when(landingDataAccess.getUserInfo("bob")).thenReturn(bob);
        Mockito.when(landingDataAccess.getUserInfo("carol")).thenReturn(carol);

        interactor.execute();

        ArgumentCaptor<LandingOutputData> cap =
                ArgumentCaptor.forClass(LandingOutputData.class);
        Mockito.verify(landingPresenter).prepareSuccessView(cap.capture());

        ArrayList<PostViewData> resultPosts = cap.getValue().getPosts();

        // size is 1..3 because of randomness & while-condition
        assertTrue(resultPosts.size() >= 1 && resultPosts.size() <= 3);

        // usernames are unique
        java.util.Set<String> names = new java.util.HashSet<>();
        for (PostViewData p : resultPosts) {
            assertTrue("duplicate username in posts", names.add(p.getUsername()));
        }
    }
}
