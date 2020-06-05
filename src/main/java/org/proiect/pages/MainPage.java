package org.proiect.pages;

import org.proiect.components.ActionController;
import org.proiect.components.AllUsersContainer;
import org.proiect.components.Container;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {
    public MainFrame mainFrame;
    public Container container;
    MainPage(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        init();
    }
    private void init(){
        setLayout(new BorderLayout());
        container = new Container();
        AllUsersContainer allUsers = new AllUsersContainer(container);
        allUsers.getAllUsers();
        container.setComponent(allUsers);
        ActionController actionController = new ActionController(this);
        add(actionController,BorderLayout.WEST);
        add(container,BorderLayout.EAST);
    }

}
