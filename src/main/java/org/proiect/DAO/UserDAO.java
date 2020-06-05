package org.proiect.DAO;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.proiect.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    static String collectionName = "User";
    static {
        Database.createUniqueIndex();
    }
    public static ObjectId create(User user) throws MongoWriteException, DuplicateKeyException {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        DBObject dbObject = (DBObject) JSON.parse(json);
        return Database.createDocument(collectionName,dbObject);
    }
    public static User findByEmail(String email){
        Gson gson = new Gson();
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        DBObject user = Database.findDocument(collectionName,query);
        if (user == null) return null;
        return gson.fromJson(user.toString(),User.class);
    }
    public static void remove(String email){
        Gson gson = new Gson();
        BasicDBObject query = new BasicDBObject();
        query.put("email", email);
        Database.removeDocument(collectionName,query);
    }
    public static List<User> getAll(){
        Gson gson = new Gson();
        List<DBObject> objects= Database.getAllDocuments(collectionName);
        List<User> users = new ArrayList<>();
        for(DBObject obj : objects){
            users.add(gson.fromJson(obj.toString(),User.class));
        }
        return users;
    }

}
