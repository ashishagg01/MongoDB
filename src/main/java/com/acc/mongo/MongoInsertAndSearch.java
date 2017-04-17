package com.acc.mongo;

/**
 * @author ashish.g.agarwal
 *
 */
import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoInsertAndSearch {
	
public static void main(String[] args) {
	 
    try {
 
	/**** Connect to MongoDB ****/
	// Since 2.10.0, uses MongoClient
	//MongoClient mongo = new MongoClient("localhost", 27017);
    MongoClient mongo = new MongoClient();
 
	/**** Get database ****/
	// if database doesn't exists, MongoDB will create it for you
	DB db = mongo.getDB("ashishdb");
 
	/**** Get collection / table from 'ashishdb' ****/
	// if collection doesn't exists, MongoDB will create it for you
	DBCollection table = db.getCollection("CURD");
 
	/**** Insert ****/
	// create a document to store key and value
	BasicDBObject document = new BasicDBObject();
	document.put("name", "ashish");
	document.put("age", 30);
	document.put("createdDate", new Date());
	System.out.println("insert the document :");
	table.insert(document);
 
	/**** Find and display ****/
	BasicDBObject searchQuery = new BasicDBObject();
	searchQuery.put("name", "ashish");
 
	DBCursor cursor = table.find(searchQuery);
 
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
	}
 
	/**** Update ****/
	// search document where name="ashish" and update it with new values
	BasicDBObject query = new BasicDBObject();
	query.put("name", "ashish");
 
	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "ashish-updated");
 
	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);
	
	System.out.println("update the document :");
	table.update(query, updateObj);
 
	/**** Find and display ****/
	BasicDBObject searchQuery2 
	    = new BasicDBObject().append("name", "ashish-updated");
 
	DBCursor cursor2 = table.find(searchQuery2);
 
	while (cursor2.hasNext()) {
		System.out.println(cursor2.next());
	}
 
	/*System.out.println("delete the document :");
	BasicDBObject searchQuery3 = new BasicDBObject();
	searchQuery3.put("name", "ashish-updated");
 
	table.remove(searchQuery3);*/
	
	/**** Done ****/
	System.out.println("Done");
 
    } catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }
 
  }
}