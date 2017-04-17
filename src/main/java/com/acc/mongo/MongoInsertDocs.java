package com.acc.mongo;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * @author ashish.g.agarwal
 * 
 */
public class MongoInsertDocs {
	public static void main(String[] args) {

		try {
			
			/*Test data in JSON format.


			{
				"database" : "ashishdb",
				"table" : "JSON",
				"detail" : 
					{
						NAME : "ASHISH",
						AGE : "26",
						SEX : "MALE"
					}
				}
			}*/

			MongoClient mongo = new MongoClient();
			DB db = mongo.getDB("ashishdb");

			DBCollection collection = db.getCollection("INSERT_JSON");

			// 1. BasicDBObject example
			System.out.println("BasicDBObject example...");
			BasicDBObject document = new BasicDBObject();
			document.put("database", "ashishdb");
			document.put("table", "JSON");

			BasicDBObject documentDetail = new BasicDBObject();
			documentDetail.put("NAME", "ASHISH");
			documentDetail.put("AGE", 26);
			documentDetail.put("SEX", "MALE");
			document.put("detail", documentDetail);

			collection.insert(document);

			DBCursor cursorDoc = collection.find();
			while (cursorDoc.hasNext()) {
				System.out.println(cursorDoc.next());
			}

			collection.remove(new BasicDBObject());

			// 2. BasicDBObjectBuilder example
			System.out.println("BasicDBObjectBuilder example...");
			BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
					.add("database", "ashishdb").add("table", "JSON");

			BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder
					.start().add("NAME", "ASHISH").add("AGE", "26")
					.add("SEX", "MALE");

			documentBuilder.add("detail", documentBuilderDetail.get());

			collection.insert(documentBuilder.get());

			DBCursor cursorDocBuilder = collection.find();
			while (cursorDocBuilder.hasNext()) {
				System.out.println(cursorDocBuilder.next());
			}

			collection.remove(new BasicDBObject());

			// 3. Map example
			System.out.println("Map example...");
			Map<String, Object> documentMap = new HashMap<String, Object>();
			documentMap.put("database", "ashishdb");
			documentMap.put("table", "JSON");

			Map<String, Object> documentMapDetail = new HashMap<String, Object>();
			documentMapDetail.put("NAME", "ASHISH");
			documentMapDetail.put("AGE", "26");
			documentMapDetail.put("SEX", "MALE");

			documentMap.put("detail", documentMapDetail);

			collection.insert(new BasicDBObject(documentMap));

			DBCursor cursorDocMap = collection.find();
			while (cursorDocMap.hasNext()) {
				System.out.println(cursorDocMap.next());
			}

			collection.remove(new BasicDBObject());

			// 4. JSON parse example
			System.out.println("JSON parse example...");

			String json = "{'database' : 'ashishdb','table' : 'JSON',"
					+ "'detail' : {'NAME' : 'ASHISH', 'AGE' : '26', 'SEX' : 'MALE'}}}";

			DBObject dbObject = (DBObject) JSON.parse(json);

			collection.insert(dbObject);

			DBCursor cursorDocJSON = collection.find();
			while (cursorDocJSON.hasNext()) {
				System.out.println(cursorDocJSON.next());
			}

			// collection.remove(new BasicDBObject());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}