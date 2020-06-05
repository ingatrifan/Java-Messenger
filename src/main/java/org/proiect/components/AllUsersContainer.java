package org.proiect.components;

import jdk.nashorn.internal.objects.NativeUint8Array;
import org.proiect.controllers.ConversationController;
import org.proiect.controllers.UserController;
import org.proiect.models.Conversation;
import org.proiect.models.LoggedInUser;
import org.proiect.models.User;

import javax.swing.*;
import java.awt.*;

public class AllUsersContainer extends JPanel {
    JScrollPane scrollFrame;
    JPanel rowHolder;
    JPanel container;
    Container parentContainer;
    public AllUsersContainer(Container container){
        this.parentContainer = container;
        init();
    }
    private void init(){
        rowHolder = new JPanel(new GridLayout(0, 1, 1, 1));
        container = new JPanel(new BorderLayout());
        container.add(rowHolder, BorderLayout.PAGE_START);
        scrollFrame = new JScrollPane(container);
        scrollFrame.setPreferredSize(new Dimension(700,800));
        setLayout(new BorderLayout());
        setBackground(Color.RED);
        rowHolder.setBackground(Color.RED);
        scrollFrame.setBackground(Color.RED);
        container.setBackground(Color.RED);
        container.setBorder(null);
        scrollFrame.setBorder(null);
        rowHolder.setBorder(null);
        add(scrollFrame, BorderLayout.CENTER);
        setAutoscrolls(true);
    }
    public void getAllUsers(){
        UserController.getAll().forEach(user -> {
            System.out.println(user.getEmail());
            System.out.println(LoggedInUser.getUser());
            if (!user.getEmail().equals(LoggedInUser.getUser().getEmail()))
                rowHolder.add(new UserContainer(user,parentContainer));
        });
    }
    public void getUsersFriends(User user){
        ConversationController.getAll(user).forEach(conversation -> {
            User friend;
            int numberOfMessages;
            Boolean removed = false;
            if (conversation.getPerson1().equals(user.getEmail())){
                friend = UserController.findByEmail(conversation.getPerson2());
                numberOfMessages = conversation.getUnreadMessages1();
                removed = conversation.getRemovedByPerson1();
            } else {
                friend = UserController.findByEmail(conversation.getPerson1());
                numberOfMessages = conversation.getUnreadMessages2();
                removed = conversation.getRemovedByPerson2();
            }
            if (!removed) {
                UserContainer uc = new UserContainer(friend, parentContainer);
                uc.setConversationFeatures();
                uc.setConversation(conversation);
                if (numberOfMessages > 0) uc.setUnreadMessages(numberOfMessages);
                rowHolder.add(uc);
            }
        });
    }
}
