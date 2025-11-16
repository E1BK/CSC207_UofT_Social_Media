package View;

import interface_adapter.searchUser.SearchUserController;
import interface_adapter.searchUser.SearchUserViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchUserView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "searchUser";
    private SearchUserViewModel searchUserViewModel;
    private SearchUserController searchUserController = null;

    public SearchUserView(SearchUserViewModel searchUserViewModel) {
        this.searchUserViewModel = searchUserViewModel;
        this.searchUserViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search for your friends at UofT:");
        this.add(title);
    }


    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setSearchUserController(SearchUserController searchUserController) {
        this.searchUserController = searchUserController;
    }
}
