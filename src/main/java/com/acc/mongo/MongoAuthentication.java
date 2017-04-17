package com.acc.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * @author ashish.g.agarwal
 * 
 */
public class MongoAuthentication {
	public static void main(String[] args) {

		try {

			MongoClient mongo = new MongoClient();
			DB db = mongo.getDB("ashishdb");

			boolean auth = db.authenticate("ashish", "password".toCharArray());
			if (auth) {

				System.out.println("Login is successful!");
				
			} else {
				System.out.println("Login is failed!");
			}
			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}
