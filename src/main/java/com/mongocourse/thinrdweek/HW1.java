package com.mongocourse.thinrdweek;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: draileanu
 * Date: 21/01/14
 * Time: 13:28
 *
 */
public class HW1 {

    public static void main(String[] args) {
        try {
            MongoClient client = new MongoClient();
            DB db = client.getDB("school");
           /* DBObject object = new BasicDBObject();
            object.put("name", "Diana");
            object.put("course", "mongo for java developers");
            object.put("date", "14t of January 2014");

            collection.insert(object);     */
            DBCollection collection = db.getCollection("students");

           /* DBCursor result =  collection.find();
            for (DBObject student : result) {
                System.out.println("result: " + student);
                 DBObject scores = (DBObject)student.get("scores");


            }   */

            DBCursor f=collection.find();
            while(f.hasNext())
            {
                BasicDBObject result1 = (BasicDBObject) f.next();
                BasicDBList scoresList = (BasicDBList) result1.get("scores");
                BasicDBObject[] scores = scoresList.toArray(new BasicDBObject[0]);
                BasicDBObject minScore = null;
                for(BasicDBObject score : scores) {
                    if(score.get("type").equals("homework")){
                        if(minScore == null) {
                            minScore = score;
                        } else if((Double)score.get("score") < (Double) minScore.get("score")){
                            minScore = score;
                        }
                    }
                    // shows each item from the lights array
                    System.out.println(score);
                }
                scoresList.remove(minScore);
                BasicDBObject findToUpdateQuery = new BasicDBObject("_id",
                        result1.get("_id"));

                BasicDBObject updateQuery = new BasicDBObject("$set",
                        new BasicDBObject("scores", scoresList));

                System.out.println(collection.update(findToUpdateQuery, updateQuery));
            }
            //System.out.println("result: " + result);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
