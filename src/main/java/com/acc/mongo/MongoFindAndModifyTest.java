
package com.acc.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * @author ashish.g.agarwal
 *
 */
public class MongoFindAndModifyTest {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = createCollection();
        collection.drop();

        final String counterId = "abc";
        int first;
        int numNeeded;

        numNeeded = 2;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

        numNeeded = 3;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + (first + numNeeded - 1));

        numNeeded = 10;
        first = getRange(counterId, numNeeded, collection);
       

        System.out.println();

        printCollection(collection);
    }

    private static int getRange(String id, int range, DBCollection collection) {
        DBObject doc = collection.findAndModify(
                new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
            System.out.println("findAndModify : "+doc.toString());
        return (Integer) doc.get("counter") - range + 1;
   }

    private static DBCollection createCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("ashishdb");
        return db.getCollection("FIND_MODIFY");
    }

    private static void printCollection(final DBCollection collection) {
        DBCursor cursor = collection.find().sort(new BasicDBObject("_id", 1));
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }

    }
}
