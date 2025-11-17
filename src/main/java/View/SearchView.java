package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    //Home按钮+标题, Homebutton+title
    private final JButton homeButton;
    private final JLabel titleLabel;

    // 输入区, Search input
    private final JTextField searchField;
    private final JButton searchButton;

    // 搜索结果列表, Search result list
    private final DefaultListModel<String> listModel;
    private final JList<String> resultList;

    // private final SearchUserController controller;
    // private final SearchViewModel viewModel;

    public SearchView() {
        // Home + 标题, Home + title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titleLabel = new JLabel("Search Users");
        homeButton = new JButton("Home");
        titlePanel.add(titleLabel);
        titlePanel.add(homeButton);

        // 搜索输入区, Search input
        final JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JLabel searchLabel = new JLabel("Account ID: ");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // 结果列表 , Result list
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        final JScrollPane resultScroll = new JScrollPane(resultList);
        resultScroll.setPreferredSize(new Dimension(300, 200));

        final JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(new JLabel("Results:"), BorderLayout.NORTH);
        resultPanel.add(resultScroll, BorderLayout.CENTER);

        //从上到下, vertical
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titlePanel);
        add(searchPanel);
        add(resultPanel);

        // 监听, listeners
        homeButton.addActionListener(this);
        searchButton.addActionListener(this);
    }

    // 方便单独测试界面, for quick UI test
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

        // Search 按钮, Search button
        if (source == searchButton) {
            String query = searchField.getText();
            // TODO: controller.search(query);
            System.out.println("Search for: " + query + " (TODO call controller)");
        }

        // 顶部 Home 按钮, Top Home button
        if (source == homeButton) {
            // TODO: switch to HomeView through main view/controller
            System.out.println("Go to HomeView (TODO)");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();

        // 结果更新时刷新列表, When results change, update list
        if ("results".equals(propertyName)) {
            Object newValue = evt.getNewValue();
            // 先假设是 String[]; assume String[]
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