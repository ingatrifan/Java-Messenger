package org.proiect.components;

import org.proiect.models.LoggedInUser;
import org.proiect.pages.LoginPage;
import org.proiect.pages.MainPage;
import org.proiect.utils.MessageChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController extends JPanel {
    MainPage mainPage;
    public ActionController(MainPage mainPage){
        this.mainPage = mainPage;
        init();
    }
    private void init(){
        setPreferredSize(new Dimension(50,800));
        setBackground(Color.BLACK);
        JButton allUsers = new JButton();
        allUsers.setPreferredSize(new Dimension(40,40));
        JButton conversations = new JButton();
        conversations.setPreferredSize(new Dimension(40,40));
        JButton logoutBtn = new JButton();
        logoutBtn.setPreferredSize(new Dimension(40,40));
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResource("users2.png"));
            Image img2 = ImageIO.read(getClass().getClassLoader().getResource("conversation.png"));
            Image img3 = ImageIO.read(getClass().getClassLoader().getResource("logout.png"));
            allUsers.setIcon(new ImageIcon(img));
            conversations.setIcon(new ImageIcon(img2));
            logoutBtn.setIcon(new ImageIcon(img3));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        add(allUsers);
        add(conversations);
        add(logoutBtn);
        allUsers.addActionListener(this::changeViewToUsers);
        conversations.addActionListener(this::changeViewToConversations);
        logoutBtn.addActionListener(this::changePageToLogin);
    }

    private void changePageToLogin(ActionEvent actionEvent) {
        MessageChecker.stopMessageChecker();
        mainPage.container.removeAll();
        LoggedInUser.logout();
        LoginPage loginPage = new LoginPage(mainPage.mainFrame);
        mainPage.mainFrame.loadPage(loginPage);
    }

    private void changeViewToUsers(ActionEvent actionEvent){
        MessageChecker.stopMessageChecker();
        mainPage.container.removeAll();
        AllUsersContainer allUsers = new AllUsersContainer(mainPage.container);
        allUsers.getAllUsers();
        mainPage.container.setComponent(allUsers);
    }
    private void changeViewToConversations(ActionEvent actionEvent){
        mainPage.container.removeAll();
        AllUsersContainer allUsers = new AllUsersContainer(mainPage.container);
        if (LoggedInUser.getUser() != null)
            allUsers.getUsersFriends(LoggedInUser.getUser());
        mainPage.container.setComponent(allUsers);
    }
}
