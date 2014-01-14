package com.mongocourse.firstweek;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: draileanu
 * Date: 14/01/14
 * Time: 13:19
 */
public class MongoStyleRun {

    public static void main(String[] args) {
        try {
            MongoClient client = new MongoClient();
            DB db = client.getDB("course");
            DBObject object = new BasicDBObject();
            object.put("name", "Diana");
            object.put("course", "mongo for java developers");
            object.put("date", "14t of January 2014");
            DBCollection collection = db.getCollection("hello");
            collection.insert(object);


            DBObject result =  collection.findOne();
            System.out.println("result: " + result);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
