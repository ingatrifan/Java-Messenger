package org.proiect.DAO;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.proiect.models.Conversation;

import java.util.ArrayList;
import java.util.List;

public class ConversationDAO {
    static String collectionName = "Conversation";
    public static ObjectId create(Conversation conversation){
        Gson gson = new Gson();
        String json = gson.toJson(conversation);
        DBObject dbObject = (DBObject) JSON.parse(json);
        return Database.createDocument(collectionName,dbObject);
    }
    public static void update(Conversation conversation){
        Gson gson = new Gson();
        String json = gson.toJson(conversation);
        DBObject dbObject = (DBObject) JSON.parse(json);
        BasicDBObject query = new BasicDBObject();
        query.put("_id",conversation.getObjectId());
        Database.updateDocument(collectionName,query,dbObject);

    }
    public static Conversation findByPerson(String email1, String email2){
        Gson gson = new Gson();
        BasicDBObject query = new BasicDBObject();
        query.put("person1", email1);
        query.put("person2", email2);
        DBObject conversation = Database.findDocument(collectionName,query);
        if (conversation == null){
            BasicDBObject query2 = new BasicDBObject();
            query.put("person2", email1);
            query.put("person1", email2);
            conversation = Database.findDocument(collectionName,query);
            if (conversation == null) return null;
        };
        return gson.fromJson(conversation.toString(),Conversation.class);
    }
    public static void remove(ObjectId id){
        Gson gson = new Gson();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        Database.removeDocument(collectionName,query);
    }
    public static List<Conversation> getAll(String email){
        Gson gson = new Gson();
        BasicDBObject query = new BasicDBObject();
        query.put("person1", email);
        List<DBObject> objects= Database.findDocuments(collectionName,query);
        List<Conversation> conversations = new ArrayList<>();
        for(DBObject obj : objects){
            conversations.add(gson.fromJson(obj.toString(),Conversation.class));
        }
        query.removeField("person1");
        query.put("person2",email);
        objects= Database.findDocuments(collectionName,query);
        for(DBObject obj : objects){
            conversations.add(gson.fromJson(obj.toString(),Conversation.class));
        }
        return conversations;
    }
}
