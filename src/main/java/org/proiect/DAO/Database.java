package org.proiect.DAO;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static DB database;
    private static MongoClient mongoClient;
    public static void connect(){
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://project:project@infoblog-yusiw.mongodb.net/project?retryWrites=true&w=majority");

        mongoClient = new MongoClient(uri);
        database = mongoClient.getDB("project");
    }

    public static ObjectId createDocument(String collectionName, DBObject document) throws MongoWriteException, DuplicateKeyException{
        DBCollection collection = database.getCollection(collectionName);
        collection.insert(document);
        ObjectId id = (ObjectId)document.get( "_id" );
        System.out.println(id);
        return id;
    }
    public static DBObject findDocument(String collectionName,DBObject query){
        DBCollection collection =  database.getCollection(collectionName);
        return collection.findOne(query);
    }
    public static List<DBObject> findDocuments(String collectionName,DBObject query){
        List<DBObject> objects = new ArrayList<>();
        DBCollection collection = database.getCollection(collectionName);
        DBCursor cursor =collection.find(query);
        while(cursor.hasNext()){
            objects.add(cursor.next());
        }
        return objects;
    }

    public static void updateDocument(String collectionName,DBObject query, DBObject document){
        DBCollection collection =  database.getCollection(collectionName);
        collection.update(query,document);
    }
    public static void removeDocument(String collectionName, DBObject query){
        DBCollection collection = database.getCollection(collectionName);
        collection.remove(query);
    }
    public static List<DBObject> getAllDocuments(String collectionName){
        List<DBObject> objects = new ArrayList<>();
        DBCollection collection = database.getCollection(collectionName);
        DBCursor cursor =collection.find();
        while(cursor.hasNext()){
            objects.add(cursor.next());
        }
        return objects;
    }
    public static void createUniqueIndex() {
        connect();
        Document index = new Document("email", 1);
        MongoDatabase md = mongoClient.getDatabase("project");
        MongoCollection<Document> collection = md.getCollection("User");
        collection.createIndex(index, new IndexOptions().unique(true));
    }
}
