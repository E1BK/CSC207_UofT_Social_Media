package data_access;

import entity.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit tests for DBUserDataAccessObject that avoid real HTTP calls by
 * overriding getUserInfo/save to use an in-memory store.
 */
public class DataAccessObjectTests {

    private TestableDAO dao;

    // simple test subclass that keeps users in a map instead of calling the API
    private static class TestableDAO extends DBUserDataAccessObject {
        private final Map<String, User> store = new HashMap<>();
        User lastSaved;

        public TestableDAO(UserFactory uf, PostFactory pf,
                           CommentFactory cf, ClubFactory clubFactory) {
            super(uf, pf, cf, clubFactory);
        }

        @Override
        public User getUserInfo(String username) {
            return store.get(username);
        }

        @Override
        public void save(User user) {
            lastSaved = user;
            store.put(user.getUsername(), user);
        }

        void putUser(User user) {
            store.put(user.getUsername(), user);
        }
    }

    @Before
    public void setUp() {
        UserFactory userFactory = Mockito.mock(UserFactory.class);
        PostFactory postFactory = Mockito.mock(PostFactory.class);
        CommentFactory commentFactory = Mockito.mock(CommentFactory.class);
        ClubFactory clubFactory = Mockito.mock(ClubFactory.class);

        dao = new TestableDAO(userFactory, postFactory, commentFactory, clubFactory);
    }

    // ---------- setCurrentUsername / getCurrentUsername ----------

    @Test
    public void currentUsername_roundTrip() {
        assertNull(dao.getCurrentUsername());

        dao.setCurrentUsername("alice");
        assertEquals("alice", dao.getCurrentUsername());
    }

    // -------------------------- getPost --------------------------

    @Test
    public void getPost_returnsMatchingPost_whenUserAndPostExist() {
        User user = new User("alice", "pw", "alice@email", "Alice");
        Post p1 = new Post(1, "alice", "t1", "b1", "d1");
        Post p2 = new Post(2, "alice", "t2", "b2", "d2");
        user.getPosts().add(p1);
        user.getPosts().add(p2);

        dao.putUser(user);

        Post result = dao.getPost("alice", 2);

        assertNotNull(result);
        assertEquals(2, result.getPost_id());
        assertEquals("t2", result.getTitle());
    }

    @Test
    public void getPost_returnsNull_whenPostNotFound() {
        User user = new User("alice", "pw", "alice@email", "Alice");
        user.getPosts().add(new Post(1, "alice", "t1", "b1", "d1"));
        dao.putUser(user);

        Post result = dao.getPost("alice", 999);

        assertNull(result);
    }

    @Test
    public void getPost_throws_whenUserNotFound() {
        try {
            dao.getPost("does_not_exist", 1);
            fail("Expected RuntimeException");
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("User not found"));
        }
    }

    // ----------------------- addCommentToPost -----------------------

    @Test
    public void addCommentToPost_addsCommentAndSaves() {
        User user = new User("alice", "pw", "alice@email", "Alice");
        Post post = new Post(10, "alice", "t", "b", "d");
        user.getPosts().add(post);
        dao.putUser(user);

        Comment comment = new Comment(1, "nice post", "today", 0);

        dao.addCommentToPost("alice", 10, comment);

        // comment attached to post
        assertEquals(1, post.getComments().size());
        Comment savedComment = post.getComments().get(0);
        assertEquals("nice post", savedComment.getComment_body());

        // save(user) was called with the updated user
        assertSame(user, dao.lastSaved);
    }

    @Test
    public void addCommentToPost_throws_whenUserNotFound() {
        Comment c = new Comment(1, "body", "date", 0);

        try {
            dao.addCommentToPost("ghost", 1, c);
            fail("Expected RuntimeException");
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("User not found"));
        }
    }

    @Test
    public void addCommentToPost_throws_whenPostNotFound() {
        User user = new User("alice", "pw", "alice@email", "Alice");
        // no posts or wrong id
        dao.putUser(user);

        Comment c = new Comment(1, "body", "date", 0);

        try {
            dao.addCommentToPost("alice", 999, c);
            fail("Expected RuntimeException");
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().contains("Post not found"));
        }
    }
}
