package gr.auth.csd.sudoku.gui;

import gr.auth.csd.sudoku.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserWindow extends JFrame {
    private JPanel panel;
    private JList<String> list;
    private JPanel buttonpanel;
    private JButton viewStatsButton;
    private JButton selectButton;
    private JLabel title;
    private JButton addUserButton;
    private JButton noUserButton;
    private String currentUser = "";
    private InputUsername inputUsername;

    private DefaultListModel<String> model;

    public UserWindow(){
        super("Select User");
        inputUsername = new InputUsername(this);

        addUserButton.addActionListener(click -> inputUsername.showWindow());
        viewStatsButton.addActionListener(click -> {
            JFrame stats = new JFrame("stats");
            JLabel label = new JLabel();

            if(!currentUser.equals("")) {
                User a = User.loadUser(currentUser);
                label.setText("User " + currentUser + " has " + a.getWins() + " wins and " + a.getLosses() + " loses");
            }
            else{
                label.setText("Please select a user first");
            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            stats.add(label);
            stats.setLocationRelativeTo(null);
            stats.setResizable(false);
            stats.setSize(300,100);
            stats.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            stats.setVisible(true);
        });

        selectButton.addActionListener(click -> {
            if(!currentUser.equals("")) {
                User user = User.loadUser(currentUser);
                setVisible(false);
            }
        });

        noUserButton.addActionListener(click -> {
            currentUser = "";
            setVisible(false);
        });

        add(panel);

        setSize(350,350);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        model = new DefaultListModel<>();
        list = new JList<>(model);

        ArrayList<User> users = User.loadAll();
        if (users == null)
            return;

        for(User user: users){
            addUserToList(user);
        }

        list.getSelectionModel().addListSelectionListener(e -> {
            //Stops event from happening many times
            if (e.getValueIsAdjusting())
                return;

            currentUser = list.getSelectedValue();
            System.out.println(currentUser);
        });
    }

    public void addUserToList(User user) {
        model.addElement(user.getUsername());
        list.setSelectedIndex(list.getLastVisibleIndex());
    }
}
