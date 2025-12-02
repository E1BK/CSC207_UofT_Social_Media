package use_case.PostViewTest;

import interface_adapter.view_post.ViewPostController;
import interface_adapter.view_post.ViewPostState;
import interface_adapter.view_post.ViewPostViewModel;
import org.junit.Test;
import view.PostView;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Basic tests for PostView: checks that
 * - state updates the UI
 * - Home button calls controller.goHome()
 * - Like button increments like count
 * - Post Comment obeys validation and calls controller.addComment(...) only on valid input
 */
public class PostViewTest {

    /**
     * Simple fake controller so we can see what PostView calls.
     */
    private static class FakeViewPostController extends ViewPostController {

        boolean goHomeCalled = false;
        boolean addCommentCalled = false;
        String lastAddCommentUsername;
        int lastAddCommentPostId;
        String lastAddCommentBody;

        public FakeViewPostController() {
            // super needs 4 args, but we won't use them in this fake
            super(null, null, null, null);
        }

        @Override
        public void goHome() {
            goHomeCalled = true;
        }

        @Override
        public void addComment(String username, int postId, String commentBody) {
            addCommentCalled = true;
            lastAddCommentUsername = username;
            lastAddCommentPostId = postId;
            lastAddCommentBody = commentBody;
        }
    }

    /**
     * Helper to build a PostView + ViewModel + FakeController,
     * and preload some state with 3 comments.
     */
    private static class PostViewFixture {
        final ViewPostViewModel viewModel;
        final PostView postView;
        final FakeViewPostController controller;

        PostViewFixture() {
            this.viewModel = new ViewPostViewModel();
            this.postView = new PostView(viewModel);
            this.controller = new FakeViewPostController();
            this.postView.setViewPostController(controller);

            // Prepare an initial state with 3 comments
            ViewPostState state = new ViewPostState();
            state.setUsername("E1");
            state.setPostId(42);
            state.setPostTitle("Demo Title");
            state.setPostBody("Demo body text.");

            state.setCommentIds(new int[]{1, 2, 3});
            state.setCommentBodies(new String[]{
                    "First comment",
                    "Second comment",
                    "Third comment"
            });
            state.setCommentLikes(new int[]{0, 5, 10});

            // Push state through the ViewModel → PostView via propertyChange
            viewModel.setState(state);
            viewModel.firePropertyChange();
        }
    }

    // --- Helpers to access private UI fields via reflection ---

    private JLabel getTitleLabel(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("titleLabel");
        f.setAccessible(true);
        return (JLabel) f.get(view);
    }

    private JTextArea getBodyArea(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("bodyArea");
        f.setAccessible(true);
        return (JTextArea) f.get(view);
    }

    private JButton getHomeButton(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("homeButton");
        f.setAccessible(true);
        return (JButton) f.get(view);
    }

    private JButton[] getLikeButtons(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("likeButtons");
        f.setAccessible(true);
        return (JButton[]) f.get(view);
    }

    private JLabel[] getLikeCountLabels(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("likeCountLabels");
        f.setAccessible(true);
        return (JLabel[]) f.get(view);
    }

    private JTextArea getNewCommentArea(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("newCommentArea");
        f.setAccessible(true);
        return (JTextArea) f.get(view);
    }

    private JButton getPostCommentButton(PostView view) throws Exception {
        Field f = PostView.class.getDeclaredField("postCommentButton");
        f.setAccessible(true);
        return (JButton) f.get(view);
    }

    // --- Tests ---

    @Test
    public void testStateUpdatesUI() throws Exception {
        PostViewFixture fx = new PostViewFixture();

        JLabel titleLabel = getTitleLabel(fx.postView);
        JTextArea bodyArea = getBodyArea(fx.postView);
        JLabel[] likeCounts = getLikeCountLabels(fx.postView);

        assertEquals("Demo Title", titleLabel.getText());
        assertEquals("Demo body text.", bodyArea.getText());

        // From our fixture, likes were {0,5,10}
        assertEquals("0", likeCounts[0].getText());
        assertEquals("5", likeCounts[1].getText());
        assertEquals("10", likeCounts[2].getText());
    }

    @Test
    public void testHomeButtonCallsGoHome() throws Exception {
        PostViewFixture fx = new PostViewFixture();
        JButton home = getHomeButton(fx.postView);

        // Simulate a click
        home.doClick();

        assertTrue("goHome should have been called", fx.controller.goHomeCalled);
    }

    @Test
    public void testLikeButtonIncrementsLikeCount() throws Exception {
        PostViewFixture fx = new PostViewFixture();
        JButton[] likeButtons = getLikeButtons(fx.postView);
        JLabel[] likeCounts = getLikeCountLabels(fx.postView);

        // Before click
        assertEquals("5", likeCounts[1].getText());

        // Click like on the second comment
        likeButtons[1].doClick();

        // After click: UI-only increment
        assertEquals("6", likeCounts[1].getText());
    }

    @Test
    public void testPostCommentValidCallsAddComment() throws Exception {
        PostViewFixture fx = new PostViewFixture();
        JTextArea newCommentArea = getNewCommentArea(fx.postView);
        JButton postCommentButton = getPostCommentButton(fx.postView);

        newCommentArea.setText("Nice post!");

        // Simulate button click
        postCommentButton.doClick();

        assertTrue("addComment should have been called", fx.controller.addCommentCalled);
        assertEquals("E1", fx.controller.lastAddCommentUsername);
        assertEquals(42, fx.controller.lastAddCommentPostId);
        assertEquals("Nice post!", fx.controller.lastAddCommentBody);
    }

    @Test
    public void testPostCommentEmptyDoesNotCallAddComment() throws Exception {
        PostViewFixture fx = new PostViewFixture();
        JTextArea newCommentArea = getNewCommentArea(fx.postView);
        JButton postCommentButton = getPostCommentButton(fx.postView);

        // empty / whitespace only
        newCommentArea.setText("   ");

        postCommentButton.doClick();

        assertFalse("addComment should NOT be called for empty comment",
                fx.controller.addCommentCalled);
    }

    @Test
    public void testPostCommentTooLongDoesNotCallAddComment() throws Exception {
        PostViewFixture fx = new PostViewFixture();
        JTextArea newCommentArea = getNewCommentArea(fx.postView);
        JButton postCommentButton = getPostCommentButton(fx.postView);

        // create a 501-character string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 501; i++) {
            sb.append('x');
        }
        newCommentArea.setText(sb.toString());

        postCommentButton.doClick();

        assertFalse("addComment should NOT be called for >500 characters",
                fx.controller.addCommentCalled);
    }
}
