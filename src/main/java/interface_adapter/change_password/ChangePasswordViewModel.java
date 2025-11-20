package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the Change Password View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    // Add these constants for button labels
    public static final String CHANGE_PASSWORD_BUTTON_LABEL = "Change Password";
    public static final String BACK_BUTTON_LABEL = "Back";

    public ChangePasswordViewModel() {
        super("change password"); // Changed from "logged in" to "change password"
        setState(new ChangePasswordState());
    }
}