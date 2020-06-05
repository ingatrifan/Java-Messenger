package org.proiect.components;

import org.proiect.controllers.ConversationController;
import org.proiect.models.Conversation;
import org.proiect.models.LoggedInUser;
import org.proiect.models.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MessagesViewer extends JPanel {
    Conversation conversation;
    public String friend;
    JScrollPane scrollFrame;
    JPanel rowHolder;
    JPanel container;
    MessagesViewer(String friend){
        this.friend = friend;
        init();
    }
    private void init(){
        rowHolder = new JPanel(new GridLayout(0, 1, 1, 1));
        container = new JPanel(new BorderLayout());
        container.add(rowHolder, BorderLayout.PAGE_START);
        scrollFrame = new JScrollPane(container);
        scrollFrame.setPreferredSize(new Dimension(700,500));
        setLayout(new BorderLayout());
        container.setBorder(null);
        scrollFrame.setBorder(null);
        rowHolder.setBorder(null);
        add(scrollFrame, BorderLayout.CENTER);
        setAutoscrolls(true);
        fillConversation();

    }
    public void fillConversation(){
        System.out.println("Filling");
        rowHolder.removeAll();
        conversation =
                ConversationController.find(LoggedInUser.getUser(),friend);
        if(conversation != null) {
            if (conversation.getMessages() != null)
                conversation.getMessages().forEach(message -> {
                    message.setRead(true);
                    JLabel messageText = new JLabel(message.getText());
                    messageText.setFont(new Font("Serif", Font.PLAIN, 16));
                    if (message.getEmail().equals(LoggedInUser.getUser().getEmail()))
                        messageText.setHorizontalAlignment(SwingConstants.RIGHT);
                    LineBorder line = new LineBorder(Color.RED, 2, true);
                    messageText.setBorder(line);
                    rowHolder.add(messageText);
                });
        }
        else {
            conversation = new Conversation(LoggedInUser.getUser().getEmail(),friend);
            ConversationController.create(conversation);
        }
        if (this.conversation.getPerson1().equals(LoggedInUser.getUser().getEmail()))
            this.conversation.setUnreadMessages1(0);
        else
            this.conversation.setUnreadMessages2(0);
        ConversationController.update(conversation);
    }
    public void updateConversation(){
        System.out.println("Updating");
        conversation.getMessages().forEach(message -> {
            if (message.getRead() == false){
                message.setRead(true);
                JLabel messageText = new JLabel(message.getText());
                messageText.setFont(new Font("Serif", Font.PLAIN, 16));
                if (message.getEmail().equals(LoggedInUser.getUser().getEmail()))
                    messageText.setHorizontalAlignment(SwingConstants.RIGHT);
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                messageText.setBorder(border);
                rowHolder.add(messageText);
            }
        });
    }
}
