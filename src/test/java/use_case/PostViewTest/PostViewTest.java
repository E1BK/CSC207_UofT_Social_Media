package use_case.PostViewTest;

import entity.Comment;
import entity.CommentFactory;
import entity.Post;
import entity.PostFactory;
import org.junit.Test;
import use_case.add_comment.AddCommentDataAccessInterface;
import use_case.add_comment.AddCommentInputData;
import use_case.add_comment.AddCommentInteractor;
import use_case.view_post.ViewPostDataAccessInterface;
import use_case.view_post.ViewPostInputData;
import use_case.view_post.ViewPostInteractor;
import use_case.view_post.ViewPostOutputBoundary;
import use_case.view_post.ViewPostOutputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests focused on the Post-related interactors:
 *  - ViewPostInteractor
 *  - AddCommentInteractor
 *
 * We use in-memory fake data access + presenter, so no API calls happen.
 */
public class PostViewTest {

    /* ------------------------------------------------------------------
     * Shared in-memory data structures
     * ------------------------------------------------------------------ */

    private static class InMemoryStore {
        Post post;  // single post we operate on
    }

    /* ------------------------------------------------------------------
     * Fake ViewPost data access + presenter
     * ------------------------------------------------------------------ */

    private static class FakeViewPostDAO implements ViewPostDataAccessInterface {
        private final InMemoryStore store;

        FakeViewPostDAO(InMemoryStore store) {
            this.store = store;
        }

        @Override
        public Post getPost(String username, int postId) {
            // For tests we just return the single post, or null
            if (store.post == null) {
                return null;
            }
            return store.post;
        }
    }

    private static class FakeViewPostPresenter implements ViewPostOutputBoundary {
        ViewPostOutputData lastSuccess;
        String lastError;

        @Override
        public void prepareSuccessView(ViewPostOutputData data) {
            lastSuccess = data;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            lastError = errorMessage;
        }
    }

    /* ------------------------------------------------------------------
     * Fake AddComment data access (reuses same InMemoryStore)
     * ------------------------------------------------------------------ */

    private static class FakeAddCommentDAO implements AddCommentDataAccessInterface {
        private final InMemoryStore store;

        FakeAddCommentDAO(InMemoryStore store) {
            this.store = store;
        }

        @Override
        public Post getPost(String username, int postId) {
            return store.post;
        }

        @Override
        public void addCommentToPost(String username, int postId, Comment comment) {
            store.post.getComments().add(comment);
        }
    }

    /* ------------------------------------------------------------------
     * Helpers to build sample posts/comments
     * ------------------------------------------------------------------ */

    private InMemoryStore buildSampleStore() {
        CommentFactory commentFactory = new CommentFactory();
        PostFactory postFactory = new PostFactory();

        List<Comment> comments = new ArrayList<>();
        comments.add(commentFactory.create(1, "Old comment 1", "2025-11-18", 5));
        comments.add(commentFactory.create(2, "Old comment 2", "2025-11-19", 3));

        Post post = postFactory.create(
                "E1",
                42,
                "Sample Title",
                "Sample body",
                LocalDate.now().toString(),
                new ArrayList<>(comments)
        );

        InMemoryStore store = new InMemoryStore();
        store.post = post;
        return store;
    }

    /* ==================================================================
       ViewPostInteractor tests
       ================================================================== */

    @Test
    public void testViewPostInteractorSuccessBuildsOutput() {
        InMemoryStore store = buildSampleStore();
        FakeViewPostDAO dao = new FakeViewPostDAO(store);
        FakeViewPostPresenter presenter = new FakeViewPostPresenter();

        ViewPostInteractor interactor = new ViewPostInteractor(dao, presenter);

        ViewPostInputData input = new ViewPostInputData("E1", 42);
        interactor.execute(input);

        assertNull("No error expected", presenter.lastError);
        assertNotNull("Success output expected", presenter.lastSuccess);

        ViewPostOutputData out = presenter.lastSuccess;
        assertEquals("E1", out.getUsername());
        assertEquals(42, out.getPostId());
        assertEquals("Sample Title", out.getPostTitle());
        assertEquals("Sample body", out.getPostBody());

        // We had 2 comments in the store
        assertEquals(2, out.getCommentBodies().length);
        assertEquals("Old comment 1", out.getCommentBodies()[0]);
        assertEquals("Old comment 2", out.getCommentBodies()[1]);
    }

    @Test
    public void testViewPostInteractorPostNotFound() {
        InMemoryStore store = new InMemoryStore(); // post == null
        FakeViewPostDAO dao = new FakeViewPostDAO(store);
        FakeViewPostPresenter presenter = new FakeViewPostPresenter();

        ViewPostInteractor interactor = new ViewPostInteractor(dao, presenter);

        ViewPostInputData input = new ViewPostInputData("E1", 999);
        interactor.execute(input);

        assertNull("No success output expected", presenter.lastSuccess);
        assertNotNull("Error message expected", presenter.lastError);
        assertFalse(presenter.lastError.isBlank());
    }

    /* ==================================================================
       AddCommentInteractor tests
       ================================================================== */

    @Test
    public void testAddCommentInteractorValidComment() {
        InMemoryStore store = buildSampleStore();
        CommentFactory commentFactory = new CommentFactory();
        FakeAddCommentDAO dao = new FakeAddCommentDAO(store);
        FakeViewPostPresenter presenter = new FakeViewPostPresenter();

        AddCommentInteractor interactor =
                new AddCommentInteractor(dao, commentFactory, presenter);

        AddCommentInputData input =
                new AddCommentInputData("E1", 42, "This is a new comment!");

        interactor.execute(input);

        // Data access side: one more comment
        assertEquals(3, store.post.getComments().size());

        // Presenter side: success called, no error
        assertNull(presenter.lastError);
        assertNotNull(presenter.lastSuccess);

        ViewPostOutputData out = presenter.lastSuccess;
        // Should still describe the same post
        assertEquals("Sample Title", out.getPostTitle());
        assertEquals("Sample body", out.getPostBody());
        // And contain up to 3 comments in arrays
        assertTrue(out.getCommentBodies().length >= 1);
    }

    @Test
    public void testAddCommentInteractorEmptyBodyFails() {
        InMemoryStore store = buildSampleStore();
        CommentFactory commentFactory = new CommentFactory();
        FakeAddCommentDAO dao = new FakeAddCommentDAO(store);
        FakeViewPostPresenter presenter = new FakeViewPostPresenter();

        AddCommentInteractor interactor =
                new AddCommentInteractor(dao, commentFactory, presenter);

        AddCommentInputData input =
                new AddCommentInputData("E1", 42, "   "); // empty after trim

        interactor.execute(input);

        // No new comment added
        assertEquals(2, store.post.getComments().size());

        // Error should be set, no success output
        assertNull(presenter.lastSuccess);
        assertNotNull(presenter.lastError);
    }

    @Test
    public void testAddCommentInteractorTooLongBodyFails() {
        InMemoryStore store = buildSampleStore();
        CommentFactory commentFactory = new CommentFactory();
        FakeAddCommentDAO dao = new FakeAddCommentDAO(store);
        FakeViewPostPresenter presenter = new FakeViewPostPresenter();

        AddCommentInteractor interactor =
                new AddCommentInteractor(dao, commentFactory, presenter);

        // 501 characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 501; i++) {
            sb.append('x');
        }
        AddCommentInputData input =
                new AddCommentInputData("E1", 42, sb.toString());

        interactor.execute(input);

        // No new comment added
        assertEquals(2, store.post.getComments().size());

        // Error should be set, no success output
        assertNull(presenter.lastSuccess);
        assertNotNull(presenter.lastError);
    }

    @Test
    public void testAddCommentInteractorPostNotFound() {
        InMemoryStore store = new InMemoryStore(); // post == null
        CommentFactory commentFactory = new CommentFactory();
        FakeAddCommentDAO dao = new FakeAddCommentDAO(store);
        FakeViewPostPresenter presenter = new FakeViewPostPresenter();

        AddCommentInteractor interactor =
                new AddCommentInteractor(dao, commentFactory, presenter);

        AddCommentInputData input =
                new AddCommentInputData("E1", 42, "Hello");

        interactor.execute(input);

        // No post → no new comment possible
        assertNull(store.post);

        // Should go to fail view
        assertNull(presenter.lastSuccess);
        assertNotNull(presenter.lastError);
    }
}
