package org.proiect.models;

public class LoggedInUser {
    static User user = null;
    public static void login(User user1){
        user = user1;
    }
    public static User getUser(){
        return user;
    }
    public static void logout(){
        user = null;
    }
}
