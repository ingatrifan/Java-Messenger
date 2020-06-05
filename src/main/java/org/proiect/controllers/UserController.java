package org.proiect.controllers;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import org.proiect.DAO.UserDAO;
import org.proiect.models.User;

import java.util.List;

public class UserController {
    public static String create(User user){
        try {
            UserDAO.create(user);
            return "Successfully created";
        }catch (MongoWriteException |DuplicateKeyException e) {
            return "An account with this email already exists";
        }
    }
    public static String remove(User user){
        UserDAO.remove(user.getEmail());
        return "Successfully deleted!";
    }
    public static User findByEmail(String email){
        User user = UserDAO.findByEmail(email);
        return user;
    }
    public static List<User> getAll(){
        return UserDAO.getAll();
    }
}
