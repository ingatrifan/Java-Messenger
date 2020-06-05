package org.proiect.models;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private String person1;
    private String person2;
    private List<Message> messages;
    private ObjectId objectId;
    private Boolean removedByPerson1 = false;
    private Boolean removedByPerson2 = false;
    private Integer unreadMessages1 = 0;
    private Integer unreadMessages2 = 0;

    public Conversation(String person1, String person2) {
        this.person1 = person1;
        this.person2 = person2;
        this.unreadMessages1 = 0;
        this.unreadMessages2 = 0;
        this.removedByPerson1 = false;
        this.removedByPerson2 = false;
    }
    public Integer getUnreadMessages1() {
        return unreadMessages1;
    }

    public Integer getUnreadMessages2() {
        return unreadMessages2;
    }

    public void setUnreadMessages2(Integer unreadMessages2) {
        this.unreadMessages2 = unreadMessages2;
    }

    public void setUnreadMessages1(Integer unreadMessages) {
        this.unreadMessages1 = unreadMessages;
    }

    public Boolean getRemovedByPerson1() {
        messages = new ArrayList<Message>();
        return removedByPerson1;
    }
    public void addMessage(Message message){
        if (messages == null) this.messages = new ArrayList<Message>();
        this.messages.add(message);
    }

    public void setRemovedByPerson1(Boolean removedByPerson1) {
        this.removedByPerson1 = removedByPerson1;
    }

    public Boolean getRemovedByPerson2() {
        return removedByPerson2;
    }

    public void setRemovedByPerson2(Boolean removedByPerson2) {
        this.removedByPerson2 = removedByPerson2;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getPerson1() {
        return person1;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
