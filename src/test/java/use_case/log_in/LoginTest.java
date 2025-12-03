package use_case.log_in;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.login_signup.login.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoginTest {
    private LoginInteractor interactor;
    private String lastError;
    private boolean successCalled;
    private String currentUsername;
    private User mockUser;
    private boolean userExists;
    private LoginOutputData capturedOutputData;

    @Before
    public void setUp() {
        // Reset test state
        lastError = null;
        successCalled = false;
        currentUsername = null;
        userExists = false;
        capturedOutputData = null;

        // Create a mock user - Using the correct User constructor from your User.java
        mockUser = new User("testuser", "password123", "test@mail.utoronto.ca", "Test User");

        // Create anonymous implementations that properly implement the interface
        LoginUserDataAccessInterface userDataAccess = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String identifier) {
                return userExists;
            }

            @Override
            public User get(String username) {
                // Return the mock user if it matches
                if (username.equals(mockUser.getUsername())) {
                    return mockUser;
                }
                return null;
            }

            @Override
            public User getUserInfo(String username) {
                if (username.equals(mockUser.getUsername())) {
                    return mockUser;
                }
                return null;
            }

            @Override
            public void setCurrentUsername(String username) {
                currentUsername = username;
            }

            @Override
            public String getCurrentUsername() {
                return currentUsername != null ? currentUsername : "";
            }

            @Override
            public void save(User user) {
                // Not used by LoginInteractor, but required by interface
            }
        };

        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData response) {
                successCalled = true;
                capturedOutputData = response;
            }

            @Override
            public void prepareFailView(String error) {
                lastError = error;
            }
        };

        interactor = new LoginInteractor(userDataAccess, presenter);
    }

    @Test
    public void testSuccessfulLogin() {
        // Setup
        userExists = true;
        LoginInputData input = new LoginInputData("testuser", "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertTrue("Success should be called", successCalled);
        assertNull("No error should be set", lastError);
        assertEquals("Current username should be set", "testuser", currentUsername);
        assertNotNull("Output data should be created", capturedOutputData);
        assertEquals("Username in output should match", "testuser", capturedOutputData.getUsername());
    }

    @Test
    public void testUserDoesNotExist() {
        // Setup
        userExists = false;
        LoginInputData input = new LoginInputData("nonexistent", "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertNotNull("Error should be set", lastError);
        assertEquals("Should get user doesn't exist error",
                "nonexistent: Account does not exist.",
                lastError);
    }

    @Test
    public void testIncorrectPassword() {
        // Setup
        userExists = true;
        LoginInputData input = new LoginInputData("testuser", "wrongpassword");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertNotNull("Error should be set", lastError);
        assertEquals("Should get incorrect password error",
                "Incorrect password for \"testuser\".",
                lastError);
        assertNull("Current username should not be set", currentUsername);
    }

    @Test
    public void testEmptyUsername() {
        // Setup
        userExists = false;
        LoginInputData input = new LoginInputData("", "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertNotNull("Error should be set", lastError);
        assertEquals("Should get account doesn't exist error",
                ": Account does not exist.",
                lastError);
    }

    @Test
    public void testEmptyPassword() {
        // Setup
        userExists = true;
        LoginInputData input = new LoginInputData("testuser", "");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertNotNull("Error should be set", lastError);
        assertEquals("Should get incorrect password error",
                "Incorrect password for \"testuser\".",
                lastError);
    }

    @Test
    public void testNullUserReturnedByGet() {
        // Setup - user exists but get() returns null
        userExists = true;
        LoginUserDataAccessInterface customDataAccess = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String id) {
                return true;
            }

            @Override
            public User get(String username) {
                return null;  // Returns null
            }

            @Override
            public User getUserInfo(String username) {
                return null;
            }

            @Override
            public void setCurrentUsername(String username) {
                currentUsername = username;
            }

            @Override
            public String getCurrentUsername() {
                return currentUsername != null ? currentUsername : "";
            }

            @Override
            public void save(User user) { }
        };

        LoginOutputBoundary customPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData r) {
                successCalled = true;
            }

            @Override
            public void prepareFailView(String e) {
                lastError = e;
            }
        };

        interactor = new LoginInteractor(customDataAccess, customPresenter);
        LoginInputData input = new LoginInputData("testuser", "password123");

        // Execute - This should throw NullPointerException when trying to get password from null user
        try {
            interactor.execute(input);
            // If we get here, either the code handles null or test should fail
            fail("Should have thrown NullPointerException when get() returns null");
        } catch (NullPointerException e) {
            // Expected behavior if code doesn't handle null user
            assertTrue("Expected NullPointerException", true);
        }
    }

    @Test
    public void testCaseSensitiveUsername() {
        // Setup
        userExists = false;  // "TestUser" != "testuser"
        LoginInputData input = new LoginInputData("TestUser", "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertNotNull("Error should be set", lastError);
        assertEquals("Should get account doesn't exist error",
                "TestUser: Account does not exist.",
                lastError);
    }

    @Test
    public void testCaseSensitivePassword() {
        // Setup
        userExists = true;
        LoginInputData input = new LoginInputData("testuser", "PASSWORD123");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertNotNull("Error should be set", lastError);
        assertEquals("Should get incorrect password error",
                "Incorrect password for \"testuser\".",
                lastError);
    }

    @Test
    public void testLoginOutputDataContainsCorrectUserInfo() {
        // Setup
        userExists = true;

        // Create a user with all fields for testing
        User fullUser = new User("fulluser", "password123", "Bio information",
                "full@mail.utoronto.ca", "Full Name", new ArrayList<>());

        LoginUserDataAccessInterface customDataAccess = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String id) {
                return true;
            }

            @Override
            public User get(String username) {
                return fullUser;
            }

            @Override
            public User getUserInfo(String username) {
                return fullUser;
            }

            @Override
            public void setCurrentUsername(String username) {
                currentUsername = username;
            }

            @Override
            public String getCurrentUsername() {
                return currentUsername != null ? currentUsername : "";
            }

            @Override
            public void save(User user) { }
        };

        LoginOutputBoundary customPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData response) {
                successCalled = true;
                capturedOutputData = response;
            }

            @Override
            public void prepareFailView(String error) {
                lastError = error;
            }
        };

        interactor = new LoginInteractor(customDataAccess, customPresenter);
        LoginInputData input = new LoginInputData("fulluser", "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertTrue("Success should be called", successCalled);
        assertNull("No error should be set", lastError);
        assertEquals("Username in output should match", "fulluser", capturedOutputData.getUsername());
        assertEquals("Email in output should match", "full@mail.utoronto.ca", capturedOutputData.getEmail());
        assertEquals("Bio in output should match", "Bio information", capturedOutputData.getBio());
        assertEquals("Posts should be empty", 0, capturedOutputData.getPosts().size());
    }

    @Test
    public void testUsernameWithSpaces() {
        // Setup
        userExists = false;  // User with spaces doesn't exist
        LoginInputData input = new LoginInputData("test user", "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertEquals("Should get account doesn't exist error",
                "test user: Account does not exist.",
                lastError);
    }

    @Test
    public void testVeryLongUsername() {
        // Setup
        userExists = false;
        String longUsername = "a".repeat(100);
        LoginInputData input = new LoginInputData(longUsername, "password123");

        // Execute
        interactor.execute(input);

        // Verify
        assertFalse("Success should not be called", successCalled);
        assertEquals("Should get account doesn't exist error",
                longUsername + ": Account does not exist.",
                lastError);
    }

    @Test
    public void testMultipleLoginAttempts() {
        // Setup - First attempt with wrong password
        userExists = true;
        LoginInputData wrongPassInput = new LoginInputData("testuser", "wrong");

        // Execute first attempt
        interactor.execute(wrongPassInput);

        // Verify first attempt failed
        assertFalse("First attempt should fail", successCalled);
        assertEquals("Should get incorrect password error",
                "Incorrect password for \"testuser\".",
                lastError);

        // Reset success flag
        successCalled = false;
        lastError = null;

        // Second attempt with correct password
        LoginInputData correctPassInput = new LoginInputData("testuser", "password123");

        // Execute second attempt
        interactor.execute(correctPassInput);

        // Verify second attempt succeeded
        assertTrue("Second attempt should succeed", successCalled);
        assertNull("No error should be set", lastError);
    }
}
