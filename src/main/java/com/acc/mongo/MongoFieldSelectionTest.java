
package com.acc.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author ashish.g.agarwal
 *
 */
public class MongoFieldSelectionTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("ashishdb");
        DBCollection collection = db.getCollection("FIELD_SELECTION");
        collection.drop();
        Random rand = new Random();

        // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insert(
                    new BasicDBObject("x", rand.nextInt(2))
                            .append("y", rand.nextInt(100))
                            .append("z", rand.nextInt(1000)));
        }

        DBObject query = QueryBuilder.start("x").is(0)
                .and("y").greaterThan(10).lessThan(70).get();

        DBCursor cursor = collection.find(query);
        
        DBCursor cursor1 = collection.find(query,
                new BasicDBObject("y", true));
        
        DBCursor cursor2 = collection.find(query,
                new BasicDBObject("y", false).append("_id", false));
        try {
        	System.out.println("collection.find(query)");
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
            System.out.println("collection.find(query,new BasicDBObject('y', true))");
            while (cursor1.hasNext()) {
                DBObject cur = cursor1.next();
                System.out.println(cur);
            }
            System.out.println("collection.find(query,new BasicDBObject('y', false).append('_id', false))");
            while (cursor2.hasNext()) {
                DBObject cur = cursor2.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }
    }
}
