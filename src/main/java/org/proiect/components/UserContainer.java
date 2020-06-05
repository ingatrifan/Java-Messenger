package org.proiect.components;

import org.proiect.controllers.ConversationController;
import org.proiect.models.Conversation;
import org.proiect.models.LoggedInUser;
import org.proiect.models.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserContainer extends JPanel {
    JButton sendMessage;
    JLabel fullName;
    JLabel unreadMessages;
    JButton deleteBtn;
    User user;
    Container parentContainer;
    Conversation conversation;
    public UserContainer(User user, Container container){
        this.parentContainer = container;
        this.user = user;
        init();
    }
    private void init(){
        sendMessage  = new JButton("Send message");
        fullName = new JLabel(user.getFullName());
        fullName.setFont(new Font("Serif", Font.PLAIN, 24));
        setPreferredSize(new Dimension(700,60));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(fullName,BorderLayout.WEST);
        add(sendMessage,BorderLayout.EAST);
        sendMessage.addActionListener(this::openSendMessage);
    }

    private void deleteConversation(ActionEvent actionEvent) {
        if (LoggedInUser.getUser().getEmail()
                .equals(conversation.getPerson1())){
            conversation.setRemovedByPerson1(true);
        } else {
            conversation.setRemovedByPerson2(true);
        }
        if (conversation.getRemovedByPerson1() && conversation.getRemovedByPerson2())
            ConversationController.removeConversation(conversation);
        else
            ConversationController.update(conversation);

    }
    public void setConversationFeatures(){
        unreadMessages = new JLabel();
        unreadMessages.setFont(new Font("Serif", Font.PLAIN, 16));
        unreadMessages.setForeground(Color.RED);
        unreadMessages.setHorizontalAlignment(SwingConstants.RIGHT);
        deleteBtn = new JButton();
        deleteBtn.setPreferredSize(new Dimension(40,40));
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResource("trash.png"));
            deleteBtn.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        JPanel featureContainer = new JPanel();
        featureContainer.setLayout(new BorderLayout());
        featureContainer.add(unreadMessages,BorderLayout.CENTER);
        featureContainer.add(deleteBtn,BorderLayout.LINE_END);
        add(featureContainer,BorderLayout.CENTER);
        deleteBtn.addActionListener(this::deleteConversation);

    }
    public void openSendMessage(ActionEvent actionEvent){
        ConversationContainer conversationContainer = new ConversationContainer(user.getEmail());
        parentContainer.setComponent(conversationContainer);
    }
    public void setConversation(Conversation conversation){
        this.conversation = conversation;
    }

    public void setUnreadMessages(int msNumber){
        this.unreadMessages.setText("Unread messages: "+msNumber);
    }
}
