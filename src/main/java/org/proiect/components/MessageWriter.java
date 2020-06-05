package org.proiect.components;

import org.proiect.controllers.ConversationController;
import org.proiect.models.Conversation;
import org.proiect.models.LoggedInUser;
import org.proiect.models.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MessageWriter extends JPanel {
    JButton send;
    JTextArea textInput;
    MessagesViewer messagesViewer;
    public MessageWriter(MessagesViewer messagesViewer){
        this.messagesViewer = messagesViewer;
        init();
    }
    private void init(){
        setPreferredSize(new Dimension(700,200));
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        send = new JButton("Send");
        send.setVerticalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        textInput = new JTextArea(7,42);
        textInput.setFont(new Font("Serif", Font.PLAIN, 16));
        add(textInput, BorderLayout.WEST);
        add(send,BorderLayout.EAST);
        send.addActionListener(this::sendMessage);
    }
    private String getText(){
        return this.textInput.getText();
    }
    private void sendMessage(ActionEvent e){
        System.out.println(getText());
        Message message = new Message(LoggedInUser.getUser().getEmail(),getText());
        this.messagesViewer.conversation.addMessage(message);
        if (this.messagesViewer.conversation.getPerson1()
                .equals(LoggedInUser.getUser().getEmail()))
            this.messagesViewer.conversation
                    .setUnreadMessages2(this.messagesViewer.conversation.getUnreadMessages2()+1);
        else
            this.messagesViewer.conversation
                    .setUnreadMessages1(this.messagesViewer.conversation.getUnreadMessages1()+1);

        ConversationController.update(this.messagesViewer.conversation);
        this.messagesViewer.updateConversation();
        this.textInput.setText("");
    }
}
