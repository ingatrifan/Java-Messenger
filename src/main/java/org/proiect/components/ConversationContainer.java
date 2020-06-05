package org.proiect.components;

import org.proiect.models.Message;
import org.proiect.models.User;
import org.proiect.utils.MessageChecker;

import javax.swing.*;
import java.awt.*;

public class ConversationContainer extends JPanel {
    String friend;
    public ConversationContainer(String friend){
        this.friend = friend;
        init();
    }
    private void init(){
        MessagesViewer messagesViewer = new MessagesViewer(friend);
        MessageWriter messageWriter = new MessageWriter(messagesViewer);
        MessageChecker messageChecker = new MessageChecker(messagesViewer);
        Thread threadChecker = new Thread(messageChecker);
        threadChecker.start();
        setLayout(new BorderLayout());
        add(messagesViewer,BorderLayout.PAGE_START);
        add(messageWriter,BorderLayout.PAGE_END);
    }

}
