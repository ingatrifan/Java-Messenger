package org.proiect.controllers;

import org.proiect.DAO.ConversationDAO;
import org.proiect.models.Conversation;
import org.proiect.models.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class ConversationController {
    public static void create(Conversation conversation){
        conversation.setObjectId(ConversationDAO.create(conversation));
    }
    public static Conversation find(User user, String email){
        return ConversationDAO.findByPerson(user.getEmail(),email);
    }
    public static List<Conversation> getAll(User user){
        return ConversationDAO.getAll(user.getEmail());
    }
    public static void removeConversation(Conversation conversation){
        ConversationDAO.remove(conversation.getObjectId());
    }
    public static void update(Conversation conversation){
        ConversationDAO.update(conversation);
    }
}
