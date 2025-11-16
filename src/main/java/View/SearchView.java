package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    // Home按钮, Home button
    private final JButton homeButton;
    private final JLabel titleLabel;

    // 底部按钮, Bottom buttons
    private final JButton navHomeButton;
    private final JButton navSearchButton;
    private final JButton navProfileButton;

    // 输入区, Search input
    private final JTextField searchField;
    private final JButton searchButton;

    // 搜索结果列表, Search result list
    private final DefaultListModel<String> listModel;
    private final JList<String> resultList;

    // private final SearchUserController controller;
    // private final SearchViewModel viewModel;

    public SearchView() {
        //Home+标题, Home+title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        homeButton = new JButton("Home");
        titleLabel = new JLabel("Search Users");
        titlePanel.add(homeButton);
        titlePanel.add(titleLabel);

        //搜索输入区, Search input
        final JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JLabel searchLabel = new JLabel("Search: ");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        //结果列表, Result list
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        final JScrollPane resultScroll = new JScrollPane(resultList);
        resultScroll.setPreferredSize(new Dimension(300, 200));

        final JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(new JLabel("Results:"), BorderLayout.NORTH);
        resultPanel.add(resultScroll, BorderLayout.CENTER);

        //底部导航栏, Bottom navigation
        final JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navHomeButton = new JButton("Home");
        navSearchButton = new JButton("Search");
        navProfileButton = new JButton("Profile");

        //从上到下,vertical
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titlePanel);
        add(searchPanel);
        add(resultPanel);
        add(navPanel);

        // 监听,listeners
        homeButton.addActionListener(this);
        searchButton.addActionListener(this);
        navHomeButton.addActionListener(this);
        navSearchButton.addActionListener(this);
        navProfileButton.addActionListener(this);

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Search View");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new SearchView());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //调用use_case ; Search button: later call use_case
        if (source == searchButton) {
            String query = searchField.getText();
            // TODO: controller.search(query);
            System.out.println("Search for: " + query + " (TODO call controller)");
        }

        //顶部Home按钮 ; Home button (top or bottom)
        if (source == homeButton || source == navHomeButton) {
            // TODO: switch to HomeView through main view/controller
            System.out.println("Go to HomeView (TODO)");
        }

        //底部Profile按钮; Bottom Profile button
        if (source == navProfileButton) {
            // TODO: switch to ProfileView via controller
            System.out.println("Go to ProfileView (TODO)");
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        //结果更新时刷新列表; When results change, update the list
        if ("results".equals(propertyName)) {
            Object newValue = evt.getNewValue();
            // 这里先假设是 String[], 以后可以换成从viewModel拿; assume String[]
            if (newValue instanceof String[]) {
                String[] results = (String[]) newValue;
                listModel.clear();
                for (String username : results) {
                    listModel.addElement(username);
                }
            }
        }
    }
}