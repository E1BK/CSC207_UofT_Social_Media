package use_case.sign_up;

import entity.User;
import entity.UserFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.login_signup.signup.*;

import static org.junit.Assert.*;

public class SignupTest {
    private SignupInteractor interactor;
    private String lastError;
    private boolean successCalled;
    private boolean switchToLoginCalled;
    private boolean userExists;
    private User savedUser;

    @Before
    public void setUp() {
        // Reset test state
        lastError = null;
        successCalled = false;
        switchToLoginCalled = false;
        userExists = false;
        savedUser = null;

        // Create anonymous implementations
        SignupUserDataAccessInterface userDataAccess = new SignupUserDataAccessInterface() {
            @Override
            public boolean existsByName(String identifier) {
                return userExists;
            }

            @Override
            public void createUser(User user) {
                savedUser = user;
            }

            @Override
            public void save(User user) {
                // Implementation for save method
                savedUser = user;
            }
        };

        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData response) {
                successCalled = true;
            }

            @Override
            public void prepareFailView(String error) {
                lastError = error;
            }

            @Override
            public void switchToLoginView() {
                switchToLoginCalled = true;
            }
        };

        UserFactory userFactory = new UserFactory() {
            @Override
            public User create(String username, String password, String email, String name) {
                // Create User with the correct constructor based on your actual User class
                // Adjust parameters based on your actual User constructor
                User user = new User(username, password, email, name, "", new java.util.ArrayList<>());
                return user;
            }
        };

        interactor = new SignupInteractor(userDataAccess, presenter, userFactory);
    }

    @Test
    public void testSuccessfulSignup() {
        // Setup
        userExists = false;
        SignupInputData input = new SignupInputData(
                "testuser", "password123", "password123",
                "test@mail.utoronto.ca", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertTrue("Success should be called", successCalled);
        assertNull("No error should be set", lastError);
        assertNotNull("User should be saved", savedUser);
        assertEquals("Username should match", "testuser", savedUser.getUsername());
    }


    @Test
    public void testUserAlreadyExists() {
        // Setup
        userExists = true;
        SignupInputData input = new SignupInputData(
                "existinguser", "password123", "password123",
                "test@mail.utoronto.ca", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertEquals("Should get user exists error", "User already exists", lastError);
        assertNull("User should not be saved", savedUser);
    }

    @Test
    public void testNonUofTEmail() {
        // Setup
        userExists = false;
        SignupInputData input = new SignupInputData(
                "testuser", "password123", "password123",
                "test@gmail.com", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertEquals("Should get UofT email error",
                "Must use UofT email (@mail.utoronto.ca)",
                lastError);
    }

    @Test
    public void testPasswordsDontMatch() {
        // Setup
        userExists = false;
        SignupInputData input = new SignupInputData(
                "testuser", "password123", "different",
                "test@mail.utoronto.ca", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertEquals("Should get password mismatch error",
                "Passwords don't match.",
                lastError);
    }

    @Test
    public void testEmptyPassword() {
        // Setup
        userExists = false;
        SignupInputData input = new SignupInputData(
                "testuser", "", "",
                "test@mail.utoronto.ca", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertEquals("Should get empty password error",
                "New password cannot be empty",
                lastError);
    }

    @Test
    public void testEmptyUsername() {
        // Setup
        SignupInputData input = new SignupInputData(
                "", "password123", "password123",
                "test@mail.utoronto.ca", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertEquals("Should get empty username error",
                "Username cannot be empty",
                lastError);
    }

    @Test
    public void testEmptyEmail() {
        // Setup
        userExists = false;
        SignupInputData input = new SignupInputData(
                "testuser", "password123", "password123",
                "", "Test User"
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertEquals("Should get empty email error",
                "Email cannot be empty",
                lastError);
    }

    @Test
    public void testEmptyName() {
        // Setup
        userExists = false;
        SignupInputData input = new SignupInputData(
                "testuser", "password123", "password123",
                "test@mail.utoronto.ca", ""
        );

        // Execute
        interactor.execute(input);

        // Verify
        assertEquals("Should get empty name error",
                "Name cannot be empty",
                lastError);
    }

    @Test
    public void testSwitchToLoginView() {
        // Execute
        interactor.switchToLoginView();

        // Verify
        assertTrue("Switch to login should be called", switchToLoginCalled);
    }
}
