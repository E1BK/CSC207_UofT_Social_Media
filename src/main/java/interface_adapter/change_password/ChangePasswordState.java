package interface_adapter.change_password;

/**
 * The State information for the Change Password functionality.
 */
public class ChangePasswordState {
    private String username = "";
    private String currentPassword = "";
    private String newPassword = "";
    private String confirmPassword = "";
    private String error = null;
    private boolean passwordChanged = false;

    // Copy constructor
    public ChangePasswordState(ChangePasswordState copy) {
        username = copy.username;
        currentPassword = copy.currentPassword;
        newPassword = copy.newPassword;
        confirmPassword = copy.confirmPassword;
        error = copy.error;
        passwordChanged = copy.passwordChanged;
    }

    // Default constructor
    public ChangePasswordState() {
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    // Helper method to clear all password fields
    public void clearPasswordFields() {
        this.currentPassword = "";
        this.newPassword = "";
        this.confirmPassword = "";
    }

    // Helper method to clear errors
    public void clearErrors() {
        this.error = null;
    }
}