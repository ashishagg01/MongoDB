
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
public class MongoDotNotationTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("ashishdb");
        DBCollection lines = db.getCollection("DotNotationTest");
        lines.drop();
        Random rand = new Random();

        // insert 10 lines with random start and end points
        for (int i = 0; i < 10; i++) {
            lines.insert(
                    new BasicDBObject("_id", i)
                            .append("start",
                                    new BasicDBObject("x", rand.nextInt(90) + 10)
                                              .append("y", rand.nextInt(90) + 10)
                            )
                            .append("end",
                                    new BasicDBObject("x", rand.nextInt(90) + 10)
                                              .append("y", rand.nextInt(90) + 10)
                            )
            );
        }

        QueryBuilder builder = QueryBuilder.start("start.x").greaterThan(50);

        DBCursor cursor = lines.find(builder.get(),
                new BasicDBObject("start.y", true).append("_id", false));

        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        } finally {
            cursor.close();
        }
    }
}
