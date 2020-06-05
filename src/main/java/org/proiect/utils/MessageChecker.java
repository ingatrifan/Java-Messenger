package org.proiect.utils;

import org.proiect.components.MessagesViewer;
import org.proiect.controllers.ConversationController;
import org.proiect.models.Conversation;
import org.proiect.models.LoggedInUser;

import java.util.concurrent.TimeUnit;

public class MessageChecker implements Runnable{
    MessagesViewer messagesViewer;
    static Boolean stop;
    public MessageChecker(MessagesViewer messagesViewer){
        this.messagesViewer = messagesViewer;
        this.stop = false;
    }
    @Override
    public void run() {
        while(!stop){
            System.out.println("Checking messages...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Conversation conversation = ConversationController.find(LoggedInUser.getUser(),messagesViewer.friend);
            if (conversation.getPerson1().equals(LoggedInUser.getUser().getEmail())){
                if (conversation.getUnreadMessages1()!=null && conversation.getUnreadMessages1()>0)messagesViewer.fillConversation();
            } else {
                if (conversation.getUnreadMessages1()!=null && conversation.getUnreadMessages2()>0)messagesViewer.fillConversation();
            }
        }
    }
    public static void stopMessageChecker(){
        stop = true;
    }
}
